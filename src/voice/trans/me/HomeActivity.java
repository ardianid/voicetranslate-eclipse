package voice.trans.me;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class HomeActivity extends  FragmentActivity implements
ActionBar.TabListener {
	
	private ViewPager viewPager;
	private TabPageAdapter mAdapter;
    //private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Translate By Voice", "Translate By Text"};
    
 //   @TargetApi(Build.VERSION_CODES.HONEYCOMB)
   // @SuppressLint("NewApi")
    
@Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     
     setContentView(R.layout.fragment_home);

     viewPager = (ViewPager) findViewById(R.id.pager);
     actionBar = getActionBar();
     mAdapter = new TabPageAdapter(getSupportFragmentManager());

     viewPager.setAdapter(mAdapter);
     actionBar.setHomeButtonEnabled(false);
     actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);       

     // Adding Tabs
     for (String tab_name : tabs) {
         actionBar.addTab(actionBar.newTab().setText(tab_name)
                 .setTabListener(this));
     } 
     
     viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

         @Override
         public void onPageSelected(int position) {
             // on changing the page
             // make respected tab selected
             actionBar.setSelectedNavigationItem(position);
         }

         @Override
         public void onPageScrolled(int arg0, float arg1, int arg2) {
         }

         @Override
         public void onPageScrollStateChanged(int arg0) {
         }
     });
 } 

    

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    
    	viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }



	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
}
