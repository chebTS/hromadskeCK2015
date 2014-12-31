package com.hromadske.tv.ck.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.fragments.DetailFragment;
import com.hromadske.tv.ck.fragments.FinReportFragment;
import com.hromadske.tv.ck.utils.SystemUtils;

import static com.hromadske.tv.ck.utils.SystemUtils.EXTRA_ENTITY;

public class DetailActivity extends ActionBarActivity {
    private ShareActionProvider mShareActionProvider;
    private BaseEntity entity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(getIntent().hasExtra(SystemUtils.EXTRA_ENTITY)) {
            if (savedInstanceState == null) {
                entity = (BaseEntity)getIntent().getExtras().getSerializable(EXTRA_ENTITY);
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, detailFragment)
                        .commit();
            }
        }else{
            FinReportFragment finReportFragment = new FinReportFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, finReportFragment)
                    .commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.financial_reports));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);
        mShareActionProvider.setShareIntent(buildShareIntent());
        return true;
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
}
