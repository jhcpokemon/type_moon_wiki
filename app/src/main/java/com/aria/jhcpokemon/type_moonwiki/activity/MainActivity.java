package com.aria.jhcpokemon.type_moonwiki.activity;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aria.jhcpokemon.type_moonwiki.NavigationDrawerCallbacks;
import com.aria.jhcpokemon.type_moonwiki.fragment.NavigationDrawerFragment;
import com.aria.jhcpokemon.type_moonwiki.R;
import com.aria.jhcpokemon.type_moonwiki.fragment.AboutFragment;
import com.aria.jhcpokemon.type_moonwiki.fragment.ReleasesListFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("jhcpokemon", "jhc851267@gmail.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        queryReleases();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        if (position == 0) {
            queryReleases();
        } else {
            showChronology();
        }
    }


    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            fragment = getFragmentManager().findFragmentByTag(AboutFragment.TAG);
            if (fragment == null) {
                fragment = new AboutFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.container, fragment, AboutFragment.TAG).addToBackStack(AboutFragment.TAG).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void queryReleases() {
        Fragment fragment = getFragmentManager().findFragmentByTag(ReleasesListFragment.TAG);
        if (fragment == null) {
            fragment = new ReleasesListFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.container, fragment, ReleasesListFragment.TAG).commit();
    }

    public void showChronology(){}
}
