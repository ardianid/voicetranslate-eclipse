package voice.trans.me;

import java.util.Locale;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AddKamusActivity extends Activity {

	private DBadapter dba=null;
	private SQLiteDatabase db=null;
	
	TextView txt_add_id;
	TextView txt_add_hira;
	TextView txt_add_kata;
	TextView txt_add_hira2;
	TextView txt_add_kata2;
	Button bt_add_simpan;
	Button bt_add_selesai;
	
	Locale myLocale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_kamus);
		
		txt_add_id = (TextView)findViewById(R.id.txt_add_id);
		
		txt_add_hira = (TextView)findViewById(R.id.txt_add_hira);
		txt_add_kata = (TextView)findViewById(R.id.txt_add_kata);
		
		txt_add_hira2 = (TextView)findViewById(R.id.txt_add_hira2);
		txt_add_kata2 = (TextView)findViewById(R.id.txt_add_kata2);
		
		bt_add_simpan= (Button)findViewById(R.id.bt_addsave);
		bt_add_selesai= (Button)findViewById(R.id.bt_addselesai);
		
		dba = new DBadapter(this);
		
		bt_add_simpan.setOnClickListener(act_simpan);
		bt_add_selesai.setOnClickListener(act_finish);
		
	//	txt_add_hira.setOnFocusChangeListener(focusjpan);
	//	txt_add_kata.setOnFocusChangeListener(focusjpan);
		
	}

	
/*	private OnFocusChangeListener focusjpan = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (hasFocus){
				setLocale("ja");
			}else{
				setLocale("id");
			}
			
		}
	};
	
	
	public void setLocale(String lang) {
		 
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm); 
        Intent refresh = new Intent(this, AddKamusActivity.class);
        startActivity(refresh);
       
    } */
	
	View.OnClickListener act_finish = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	};
	
	View.OnClickListener act_simpan = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if (txt_add_id.getText().toString().length()==0) {
				Toast.makeText(AddKamusActivity.this,"ID tidak boleh kosong", Toast.LENGTH_LONG).show();
				txt_add_id.requestFocus();
				return;
			}
			
			if (txt_add_hira.getText().toString().length()==0) {
				Toast.makeText(AddKamusActivity.this,"Terjemahan JPN (H) tidak boleh kosong", Toast.LENGTH_LONG).show();
				txt_add_hira.requestFocus();
				return;
			}
			
			if (txt_add_kata.getText().toString().length()==0) {
				Toast.makeText(AddKamusActivity.this,"Terjemahan JPN (K) tidak boleh kosong", Toast.LENGTH_LONG).show();
				txt_add_kata.requestFocus();
				return;
			}
			
			if (txt_add_hira2.getText().toString().length()==0) {
				Toast.makeText(AddKamusActivity.this,"Aksara JPN (H) tidak boleh kosong", Toast.LENGTH_LONG).show();
				txt_add_hira2.requestFocus();
				return;
			}
			
			if (txt_add_kata2.getText().toString().length()==0) {
				Toast.makeText(AddKamusActivity.this,"Aksara JPN (K) tidak boleh kosong", Toast.LENGTH_LONG).show();
				txt_add_kata2.requestFocus();
				return;
			}
			
			db = dba.getWritableDatabase();
			try {
				
				
				String sql="insert into ms_kamus (t_indo,t_hira,t_kata,a_hira,a_kata) values('"+ txt_add_id.getText().toString().toUpperCase(Locale.getDefault())  +"','"+ txt_add_hira.getText().toString().toUpperCase(Locale.getDefault()) +"','"+ txt_add_kata.getText().toString().toUpperCase(Locale.getDefault()) +"','"+ txt_add_hira2.getText().toString().toUpperCase(Locale.getDefault()) +"','"+ txt_add_kata2.getText().toString().toUpperCase(Locale.getDefault()) +"')"; 
				db.execSQL(sql);
				
				Toast.makeText( getApplicationContext(), "Data disimpan... ", Toast.LENGTH_SHORT).show();
				
				txt_add_id.setText("");
				txt_add_hira.setText("");
				txt_add_kata.setText("");
				txt_add_hira2.setText("");
				txt_add_kata2.setText("");
				
				txt_add_id.requestFocus();
				
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
			}
			
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_kamus, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
