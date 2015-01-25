package com.hromadske.tv.ck.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.fragments.AboutFragment;
import com.hromadske.tv.ck.fragments.CultureListFragment;
import com.hromadske.tv.ck.fragments.EmptyFragment;
import com.hromadske.tv.ck.fragments.FilmsListFragment;
import com.hromadske.tv.ck.fragments.NavigationDrawerFragment;
import com.hromadske.tv.ck.fragments.PhotoesListFragment;
import com.hromadske.tv.ck.fragments.PoliticsListFragment;
import com.hromadske.tv.ck.fragments.SocietyListFragment;
import com.hromadske.tv.ck.fragments.TeamListFragment;

//http://blog.sqisland.com/2014/06/navigationdrawer-creates-fragment-twice.html
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private ShareActionProvider mShareActionProvider;
    private BaseEntity entity = null;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private FrameLayout tabletContainer;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabletContainer = (FrameLayout)findViewById(R.id.tablet_container);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position, boolean fromSavedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!fromSavedInstanceState) {
            switch (position) {
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
            ft.commit();
        }
        if (tabletContainer != null) {
            FragmentTransaction ft1 = fragmentManager.beginTransaction();
            ft1.replace(R.id.tablet_container, EmptyFragment.newInstance(1));
            ft1.commit();
        }
    }

/*    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }*/

    public void setEntity(BaseEntity entity) {
        this.entity = entity;
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(buildShareIntent());
        }
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
            if (tabletContainer!= null) {
                getMenuInflater().inflate(R.menu.menu_detail, menu);
                MenuItem menuItem = menu.findItem(R.id.action_share);
                mShareActionProvider = (ShareActionProvider)
                        MenuItemCompat.getActionProvider(menuItem);
                mShareActionProvider.setShareIntent(buildShareIntent());
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    private Intent buildShareIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.share_title));
        if (entity == null){
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_title));
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text_default));
            return  shareIntent;
        }else{
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, entity.getTitle());
            if(entity.getVideo() == null || entity.getVideo().length() == 0) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text_default));
            }else{
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "http://www.youtube.com/watch?v=" + entity.getVideo());
            }
            return shareIntent;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public FrameLayout getTabletContainer() {
        return tabletContainer;
    }
}