package voice.trans.me;

import voice.trans.me.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DBadapter dba=null;
	private SQLiteDatabase db=null;
	private Cursor dbcursor;
	protected ListAdapter  adapter;
	
	private final int REQ_CODE_SPEECH_INPUT = 100;
	private ArrayList<String> resultvoice;
	
	// Within which the entire activity is enclosed
    private DrawerLayout mDrawerLayout;
 
    // ListView represents Navigation Drawer
    private ListView mDrawerList;
    
    private View v_voice;
    private Spinner cb_modes_voice;
    private ListView list_voice;
    
    // ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	    private ActionBarDrawerToggle mDrawerToggle;
	    
	    
	    // Title of the action bar
	    private String mTitle = "Translate Menu";
	    private String langu;
	    
	    String nopengajuan;
	    
	  //  @SuppressLint("NewApi")
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	 
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        dba = new DBadapter(this);
	        
	        
	        db = dba.getWritableDatabase();
	        
	        String sqlcek="select tjml from ms_kunci";
			
	        Cursor dbcursor = dba.SelectData(db,sqlcek);
	        dbcursor.moveToFirst();
	        
	        Integer jmlcur = dbcursor.getCount();
	        
	        if (jmlcur > 0){
	        	
	        	Integer isinya = Integer.valueOf(dbcursor.getString(0));
	        	
	        	isinya = isinya+1;
				
				Toast.makeText(getBaseContext(), isinya.toString(), Toast.LENGTH_LONG).show();
				
				String sql_ins3="update ms_kunci set tjml="+ isinya;
				db.execSQL(sql_ins3);
				
				if (isinya > 2) {
					finish();
				}
	        	
	        }
	        
	        dbcursor.close();
			
			
	        mTitle = "Translate Menu";
	        getActionBar().setTitle(mTitle);
	 
	        // Getting reference to the DrawerLayout
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	 
	        mDrawerList = (ListView) findViewById(R.id.drawer_list);
	 
	        // Getting reference to the ActionBarDrawerToggle
	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close) {
	        	
	            //R.drawable.ic_drawer, R.string.drawer_open) {
	        	
	            /** Called when drawer is closed */
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu();
	 
	            }
	 
	            /** Called when a drawer is opened */
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle("Translate Menu");
	                invalidateOptionsMenu();
	            }
	 
	        };
	 
	        // Setting DrawerToggle on DrawerLayout
	        mDrawerLayout.setDrawerListener(mDrawerToggle); 
	        
	        // Creating an ArrayAdapter to add items to the listview mDrawerList
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
	                R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));
	 
	        // Setting the adapter on mDrawerList
	        mDrawerList.setAdapter(adapter);
	 
	        // Enabling Home button
	        getActionBar().setHomeButtonEnabled(true);
	 
	        // Enabling Up navigation
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	 
	        // Setting item click listener for the listview mDrawerList
	        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
	 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	 
	                // Getting an array of rivers
	                String[] menuItems = getResources().getStringArray(R.array.menus);
	 
	                // Currently selected river
	                mTitle = menuItems[position];
	                if (mTitle.equals("Translate By Voice")){
	                	FragmentHome fragmenthome = new FragmentHome();
	                    FragmentManager fragmentManager = getFragmentManager();
	 
	                    // Creating a fragment transaction
	                    FragmentTransaction ft = fragmentManager.beginTransaction();
	 
	                    // Adding a fragment to the fragment transaction
	                    ft.replace(R.id.content_frame, fragmenthome);
	 
	                    // Committing the transaction
	                    ft.commit();
	                }
	                else if (mTitle.equals("List Kamus")){
	                   // Fragmentbytext fragmentone = new Fragmentbytext();
	                    //FragmentManager fragmentManager = getFragmentManager();
	 
	                    // Creating a fragment transaction
	                    //FragmentTransaction ft = fragmentManager.beginTransaction();
	 
	                    // Adding a fragment to the fragment transaction
	                    //ft.replace(R.id.content_frame, fragmentone);
	 
	                    // Committing the transaction
	                   // ft.commit();
	                }
	            	else if (mTitle.equals("Translate By Text")){
	                    FragmentListKamus fragmenttwo = new FragmentListKamus();
	                    FragmentManager fragmentManager = getFragmentManager();
	 
//			                // Passing selected item information to fragment
//			                Bundle data = new Bundle();
//			                data.putInt("position", position);
//			                //data.putString("url", getUrl(position));
//			                rFragment.setArguments(data);
	                    
	                    // Creating a fragment transaction
	                    FragmentTransaction ft = fragmentManager.beginTransaction();
	 
	                    // Adding a fragment to the fragment transaction
	                    ft.replace(R.id.content_frame, fragmenttwo);
	 
	                    // Committing the transaction
	                    ft.commit();
	                }
	            	else if (mTitle.equals("Aksara")){
	                    FragmentAksara fragmenttwo = new FragmentAksara();
	                    FragmentManager fragmentManager = getFragmentManager();
	 
//			                // Passing selected item information to fragment
//			                Bundle data = new Bundle();
//			                data.putInt("position", position);
//			                //data.putString("url", getUrl(position));
//			                rFragment.setArguments(data);
	                    
	                    // Creating a fragment transaction
	                    FragmentTransaction ft = fragmentManager.beginTransaction();
	 
	                    // Adding a fragment to the fragment transaction
	                    ft.replace(R.id.content_frame, fragmenttwo);
	 
	                    // Committing the transaction
	                    ft.commit();
	                }
	            	else if (mTitle.equals("About")){
	                    FragmentAbout fragmenttwo = new FragmentAbout();
	                    FragmentManager fragmentManager = getFragmentManager();
	                    
	                    // Creating a fragment transaction
	                    FragmentTransaction ft = fragmentManager.beginTransaction();
	 
	                    // Adding a fragment to the fragment transaction
	                    ft.replace(R.id.content_frame, fragmenttwo);
	 
	                    // Committing the transaction
	                    ft.commit();
	                }else if (mTitle.equals("Info")){
	                    
	                	String messg ="Voice text translate" +"\n"+ "Dibuat oleh Suci Septia Mulia" +"\n"+ "Sebagai TA (Tugas Akhir) / Skripsi Jurusan Sistem Informasi Universitas Bandar Lampung" ;
	        			
	        			final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	                	builder
	                	.setTitle("Tentang")
	                	.setMessage(messg)
	                	.setIcon(android.R.drawable.ic_dialog_info);
    					
    		        	builder.show();
	                	
	                	
	                }
	            	else if (mTitle.equals("Exit")){
	            		finish();
	            	}
	                // Closing the drawer
	                mDrawerLayout.closeDrawer(mDrawerList);
	 
	            }
	        });
	        
	        //Menampilkan Fragment Home yang Tampil Pertama Kali
	        
	        FragmentHome fragmenthome = new FragmentHome();
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.content_frame, fragmenthome);

            ft.commit();
            
            //getActionBar().setTitle("Home");
	    }
	 
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        mDrawerToggle.syncState();
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	 
	    /** Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	 
	        menu.findItem(R.id.menu_settings).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public class FragmentHome extends Fragment {
		 
	    @Override
	    public View onCreateView(LayoutInflater inflater,
	    		ViewGroup container,Bundle savedInstanceState) {
	    	
	    	String[] jenis_translate= {"INDO - JPN","JPN - INDO"};
	    	
			v_voice = inflater.inflate(R.layout.activity_trans_voice, container, false);
			
			cb_modes_voice = (Spinner)v_voice.findViewById(R.id.cb_mode1);
			ArrayAdapter<String> jenis_trans2=new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item,jenis_translate);
	        cb_modes_voice.setAdapter(jenis_trans2);
	        
	        cb_modes_voice.setOnItemSelectedListener(new OnItemSelectedListener() {

	            @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
	                String hasil= cb_modes_voice.getSelectedItem().toString();
	                
	                if (hasil.equals("INDO - JPN")) {
	                	langu="id";
	                }else{
	                	langu="ja";
	                }
	                
	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub

	            }
	        });
	        
	        
			Button btnspeak = (Button)v_voice.findViewById(R.id.bt_speak);
	        btnspeak.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					promptSpeechInput();
					
				//	resultvoice = new ArrayList<String>();
					
				//	resultvoice.add("makan");
				//	resultvoice.add("makanan");
				//	resultvoice.add("saya");
						
					
				}		 
			});
			 
			return v_voice;
			
	    }
	}
	
	public class Fragmentbytext extends Fragment {
		 
	    @Override
	    public View onCreateView(LayoutInflater inflater,
	    		ViewGroup container,Bundle savedInstanceState) {
	    	
	    	
	    	String[] jenis_translate= {"INDO - JPN","JPN - INDO"};
	    	
	        View v = inflater.inflate(R.layout.activity_trans_text, container, false);
	        
	        final Spinner cb_modes = (Spinner)v.findViewById(R.id.cb_mode2);
			ArrayAdapter<String> jenis_trans2=new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item,jenis_translate);
	        cb_modes.setAdapter(jenis_trans2);
	        
	        final TextView txt_cari = (TextView)v.findViewById(R.id.txt_trans); 
	        final ListView list1 = (ListView)v.findViewById(R.id.lv_hasil2);	       
	        
	        Button btnNext = (Button)v.findViewById(R.id.bt_speak2);
	        btnNext.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					String hasil_jenis_translate = (String)cb_modes.getSelectedItem();
					String sql="";
					
					if (hasil_jenis_translate.equals("INDO - JPN")) {
						sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil  from ms_kamus where t_indo like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%'";
					}else{
						sql ="select _id,(t_hira || '(H) | ' || t_kata || '(K)') as asal,t_indo as hasil  from ms_kamus where t_hira like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or t_kata like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%'";
					}
					
					db = dba.getReadableDatabase(); 
					try {
						
						dbcursor = dba.SelectData(db,sql);
						
						
						String[] columns = new String[] {"asal", "hasil"};
				        int[] to = new int[] {R.id.txt_source0,R.id.txt_hasil};
						
						SimpleCursorAdapter adp = new SimpleCursorAdapter(getActivity(), R.layout.act_row_hasil, dbcursor, columns, to,0);
						list1.setAdapter(adp);
						
						
					}catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
					}
					
					
				}
			});
	        
	        return v;
	    }
	}
	
	public class FragmentListKamus extends Fragment {
		 
	    @Override
	    public View onCreateView(LayoutInflater inflater,
	    		ViewGroup container,Bundle savedInstanceState) {
	    	
	        View v = inflater.inflate(R.layout.activity_listkamus, container, false);
	        
	        final TextView txt_cari = (TextView)v.findViewById(R.id.txt_carikamus); 
	        final ListView list1 = (ListView)v.findViewById(R.id.lv_kamus);	       
	        
	        Button btncari = (Button)v.findViewById(R.id.bt_searchkamus);
	        Button btnadd = (Button)v.findViewById(R.id.bt_addkamus);
	        Button btndell = (Button)v.findViewById(R.id.bt_delkamus);
	        
	        list1.setOnItemClickListener(new OnItemClickListener() {
        		
        		public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
        			
        			nopengajuan = dbcursor.getString(dbcursor.getColumnIndex("asal"));
        			
        		}
        		
			}); // akhir dari event Onitemclick 
	        
	        btncari.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					String sql="";
					
					if (txt_cari.getText().toString().length()==0){
						sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus";
					}else{
						sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus where t_indo like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or t_hira like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or t_kata like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or a_hira like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or a_kata like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%'";
					}
					
					
					db = dba.getReadableDatabase(); 
					try {
						
						dbcursor = dba.SelectData(db,sql);
						
						
						String[] columns = new String[] {"asal", "hasil","hasil2"};
				        int[] to = new int[] {R.id.txt_source0,R.id.txt_hasil,R.id.txt_hasil_aks};
						
						SimpleCursorAdapter adp = new SimpleCursorAdapter(getActivity(), R.layout.act_row_hasil, dbcursor, columns, to,0);
						list1.setAdapter(adp);
						
					}catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
					}
					
				}
			});
	        // akhir dari cari
	        
	        
	        btnadd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myintent=new Intent(getBaseContext(), AddKamusActivity.class);
					startActivity(myintent);
					
				}
			});
	        // akhir dari add
	        
	        btndell.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					db = dba.getWritableDatabase();
					 
					 try {
						
						 String sql = "delete from ms_kamus where t_indo='"+ nopengajuan +"'";
						 db.execSQL(sql);
						 
						 Toast.makeText(getBaseContext(), "Data telah dihapus...", Toast.LENGTH_LONG).show();
						 
						 // mulai refresh lagi
							
							if (txt_cari.getText().toString().length()==0){
								sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus";
							}else{
								sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus where t_indo like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or t_hira like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or t_kata like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or a_hira like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%' or a_kata like '%"+ txt_cari.getText().toString().toUpperCase(Locale.getDefault()) +"%'";
							}
							
							
							db = dba.getReadableDatabase(); 
							try {
								
								dbcursor = dba.SelectData(db,sql);
								
								
								String[] columns = new String[] {"asal", "hasil","hasil2"};
						        int[] to = new int[] {R.id.txt_source0,R.id.txt_hasil,R.id.txt_hasil_aks};
								
								SimpleCursorAdapter adp = new SimpleCursorAdapter(getActivity(), R.layout.act_row_hasil, dbcursor, columns, to,0);
								list1.setAdapter(adp);
								
							}catch (Exception e) {
								// TODO: handle exception
								Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
							}
						 
						 // akhir dari refresh
						 
					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
					}
					
				}
				
			});
	        
	        
	        
	        
	        return v;
	    }
	}
	
	
	public class FragmentAksara extends Fragment {
		
		@Override
	    public View onCreateView(LayoutInflater inflater,
	    		ViewGroup container,Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.activity_aksara, container, false);
			
			final TextView txt_about0 = (TextView)v.findViewById(R.id.txt_aksara);
			
			try {
	            InputStream is = getAssets().open("materi.txt");
	            
	            // We guarantee that the available method returns the total
	            // size of the asset...  of course, this does mean that a single
	            // asset can't be more than 2 gigs.
	            int size = is.available();
	            
	            // Read the entire asset into a local byte buffer.
	            byte[] buffer = new byte[size];
	            is.read(buffer);
	            is.close();
	            
	            // Convert the buffer into a string.
	            String text = new String(buffer);
	            
	            txt_about0.setText(text);
	            
	        } catch (IOException e) {
	            // Should never happen!
	            throw new RuntimeException(e);
	        }
			
			return v;
			
			
		}
		
	}
	
	public class FragmentAbout extends Fragment {
	
		@Override
	    public View onCreateView(LayoutInflater inflater,
	    		ViewGroup container,Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_page_1, container, false);
			
			final TextView txt_about0 = (TextView)v.findViewById(R.id.txt_about);
			
			try {
	            InputStream is = getAssets().open("about_info.txt");
	            
	            // We guarantee that the available method returns the total
	            // size of the asset...  of course, this does mean that a single
	            // asset can't be more than 2 gigs.
	            int size = is.available();
	            
	            // Read the entire asset into a local byte buffer.
	            byte[] buffer = new byte[size];
	            is.read(buffer);
	            is.close();
	            
	            // Convert the buffer into a string.
	            String text = new String(buffer);
	            
	            txt_about0.setText(text);
	            
	        } catch (IOException e) {
	            // Should never happen!
	            throw new RuntimeException(e);
	        }
			
			return v;
			
			
		}
		
	}
	
	private void promptSpeechInput() {
		
		resultvoice = new ArrayList<String>();
		
	/*	PackageManager mPackageManager = this.getPackageManager();
	    List<ResolveInfo> activities = mPackageManager.queryIntentActivities(
	            new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
	    if (activities.size() == 0) {
	        Toast.makeText(this, "No audio recorder present.",
	                Toast.LENGTH_LONG).show();
	    } else { */
		
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,langu);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        
        	try {
        		startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        	} catch (ActivityNotFoundException a) {
        		Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        	}
        
	    //}
        
        
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        switch (requestCode) {
        case REQ_CODE_SPEECH_INPUT: {
            if (resultCode == RESULT_OK && null != data) {
            	
                resultvoice = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                
                
                Toast.makeText(getBaseContext(), resultvoice.toString(), Toast.LENGTH_LONG).show();
                
                list_voice = (ListView)v_voice.findViewById(R.id.lv_hasil1);
				
				String katadicari="";
				for (int i=0;i < resultvoice.size(); i++ ){
					katadicari= katadicari +"'"+ resultvoice.get(i).toUpperCase(Locale.getDefault()) +"',";
                	//Log.i("hasil array",resultvoice.get(i));
                }
				
				katadicari = katadicari.substring(0,katadicari.length()-1 );
				
				//Toast.makeText(getBaseContext(),"Kata :"+ katadicari, Toast.LENGTH_LONG).show();
				
				String hasil_jenis_translate = (String)cb_modes_voice.getSelectedItem();
				String sql="";
				
				if (hasil_jenis_translate.equals("INDO - JPN")) {
					sql = "select _id,t_indo as asal,(t_hira || '(H) | ' || t_kata || '(K)') as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus where t_indo in ("+ katadicari +")";
				}else{
					sql ="select _id,(t_hira || '(H) | ' || t_kata || '(K)') as asal,t_indo as hasil,(a_hira || '(H) | ' || a_kata || '(K)') as hasil2  from ms_kamus where t_hira in ("+ katadicari +") or t_kata in ("+ katadicari +") or a_hira in ("+ katadicari +") or a_kata in ("+ katadicari +")";
				}
				
				db = dba.getReadableDatabase(); 
				try {
					
					dbcursor = dba.SelectData(db,sql);
					
					
					String[] columns = new String[] {"asal", "hasil","hasil2"};
			        int[] to = new int[] {R.id.txt_source0,R.id.txt_hasil,R.id.txt_hasil_aks};
					
					SimpleCursorAdapter adp = new SimpleCursorAdapter(this, R.layout.act_row_hasil, dbcursor, columns, to,0);
					list_voice.setAdapter(adp);
					
				}catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
				
				
                
                
            }
            break;
        }
 
        }
    }
	
	
	
	
	@Override
    protected void onResume(){
    	super.onResume();
    }
    
	
    @Override
    protected void onPause(){
    	super.onPause();
    }
	
    
	 @Override
	    protected void onDestroy(){
	    	super.onDestroy();
	    	//dbcursor.close();
	    	//db.close();
	 }
	 
	 
	 

}
