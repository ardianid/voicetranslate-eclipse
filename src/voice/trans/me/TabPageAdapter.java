package voice.trans.me;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabPageAdapter extends FragmentPagerAdapter  {

	public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
        case 0:
            return new TransVoiceActivity();
        case 1:
            return new TransVoiceActivity();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    
}
