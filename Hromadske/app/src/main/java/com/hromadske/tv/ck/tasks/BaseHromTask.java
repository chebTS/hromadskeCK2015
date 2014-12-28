package com.hromadske.tv.ck.tasks;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.type.TypeReference;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.adapters.BaseEntitiesAdapter;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.tasks.BaseTask;

import static com.hromadske.tv.ck.utils.SystemUtils.*;

/**
 * Created by cheb on 28.12.2014.
 */
public class BaseHromTask extends BaseTask {
    private static final String TAG = BaseHromTask.class.getSimpleName();
    private String url;
    private RightList<BaseEntity> entities;
    private ListView listView;

    public BaseHromTask(Context context, View progressBar, String url, ListView listView) {
        super(context, progressBar);
        this.url = url;
        this.listView = listView;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            HttpGet get = new HttpGet(url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(get);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                entities = MAPPER.readValue(response.getEntity().getContent(), DataContent.class).result;

                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "doInBackgroud", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result){
            /*Log.i(TAG, "==============================");
            for (BaseEntity entity: entities){
                Log.i(TAG, entity.getTitle());
            }
            Log.i(TAG, "==============================");*/
            listView.setAdapter(new BaseEntitiesAdapter(context, R.layout.item_entity, entities));
        }
    }

    private static class DataContent {
        public RightList<BaseEntity> result;
    }
}
