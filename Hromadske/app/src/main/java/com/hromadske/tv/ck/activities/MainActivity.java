package com.hromadske.tv.ck.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.fragments.AboutFragment;
import com.hromadske.tv.ck.fragments.BaseMenuFragment;
import com.hromadske.tv.ck.fragments.CultureListFragment;
import com.hromadske.tv.ck.fragments.FilmsListFragment;
import com.hromadske.tv.ck.fragments.NavigationDrawerFragment;
import com.hromadske.tv.ck.fragments.PhotoesListFragment;
import com.hromadske.tv.ck.fragments.PlaceholderFragment;
import com.hromadske.tv.ck.fragments.PoliticsListFragment;
import com.hromadske.tv.ck.fragments.SocietyListFragment;
import com.hromadske.tv.ck.fragments.TeamListFragment;

import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private List<BaseMenuFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (position){
            case 0:
                ft.replace(R.id.container, PoliticsListFragment.newInstance(position + 1));
                break;
            case 1:
                ft.replace(R.id.container, SocietyListFragment.newInstance(position + 1));
                break;
            case 2:
                ft.replace(R.id.container, CultureListFragment.newInstance(position + 1));
                break;
            case 3:
                ft.replace(R.id.container, FilmsListFragment.newInstance(position + 1));
                break;
            case 4:
                ft.replace(R.id.container, PhotoesListFragment.newInstance(position + 1));
                break;
            case 5:
                ft.replace(R.id.container, TeamListFragment.newInstance(position + 1));
                break;
            case 6:
                ft.replace(R.id.container, AboutFragment.newInstance(position + 1));
                break;
        }
        //ft.replace(R.id.container, PlaceholderFragment.newInstance(position + 1));
        ft.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
