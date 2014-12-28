package com.hromadske.tv.ck.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.fragments.DetailFragment;
import com.hromadske.tv.ck.utils.SystemUtils;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(getIntent().hasExtra(SystemUtils.EXTRA_ENTITY)) {
            if (savedInstanceState == null) {
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, detailFragment)
                        .commit();
            }
        }else{
            //TODO about
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
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
