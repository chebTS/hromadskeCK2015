package com.hromadske.tv.ck.tasks;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.view.View;

import com.hromadske.tv.ck.entities.BaseEntity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import static com.hromadske.tv.ck.utils.SystemUtils.MAPPER;

/**
 * Created by cheb on 12/30/14.
 */
public class EntitiesLoader extends Loader<RightList<BaseEntity>> {
    private static final String TAG = EntitiesLoader.class.getSimpleName();
    private String url;
    private View progressBar;
    private HromTask hromTask;

    public EntitiesLoader(Context context, String url, View progressBar) {
        super(context);
        this.url = url;
        this.progressBar = progressBar;
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        if (hromTask != null)
            hromTask.cancel(true);
        hromTask = new HromTask();
        hromTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    void getResultFromTask(RightList<BaseEntity> result) {
        deliverResult(result);
    }

    public class HromTask extends AsyncTask<String, Void, RightList<BaseEntity>> {

        @Override
        protected RightList<BaseEntity> doInBackground(String... params) {
            try {
                HttpGet get = new HttpGet(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpResponse response = httpClient.execute(get);
                int status = response.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
                    return MAPPER.readValue(response.getEntity().getContent(), DataContent.class).result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(RightList<BaseEntity> baseEntities) {
            super.onPostExecute(baseEntities);
            getResultFromTask(baseEntities);
        }
    }

    static class DataContent {
        public RightList<BaseEntity> result;
    }
}