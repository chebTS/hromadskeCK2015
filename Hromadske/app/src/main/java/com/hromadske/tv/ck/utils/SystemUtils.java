package com.hromadske.tv.ck.utils;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.hromadske.tv.ck.db.HromContentProvider;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rightutils.rightutils.collections.RightList;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Vector;

import static org.codehaus.jackson.map.DeserializationConfig.*;

/**
 * Created by cheb on 12/27/14.
 */
public class SystemUtils {
        private static final String BASE_URL = "http://hromadske.cherkasy.ua/?option=com_hromadskeapi";
        public static final String NEWS_URL = BASE_URL + "&category=news";
        public static final String POLITICS_URL = BASE_URL + "&category=polityka";
        public static final String SOCIETY_URL = BASE_URL + "&category=suspilstvo";
        public static final String CULTURE_URL = BASE_URL + "&category=kultura";
        public static final String FILMS_URL = BASE_URL + "&category=kinodoc";
        public static final String PHOTOES_URL = BASE_URL + "&category=fotodoc";
        public static final String TEAM_URL = BASE_URL + "&category=team";

    public static final String EXTRA_ENTITY = "entity";
    public static ImageLoader IMAGELOADER = null;
    public final static ObjectMapper MAPPER = new ObjectMapper().configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static void watchYoutubeVideo(Context context, String id){
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            context.startActivity(intent);
        }catch (ActivityNotFoundException ex){
            Intent intent=new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v="+id));
            context.startActivity(intent);
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static void saveData(Context context, String url, RightList<BaseEntity> entities){
        Vector<ContentValues> cVVector = new Vector<ContentValues>(entities.size());
        ContentValues contentValues ;
        for (BaseEntity entity : entities) {
            contentValues = new ContentValues();
            contentValues.put(HromContentProvider._ID, entity.getId());
            contentValues.put(HromContentProvider._TITLE, entity.getTitle());
            contentValues.put(HromContentProvider._INTROTEXT, entity.getTitle());
            contentValues.put(HromContentProvider._FULLTEXT, entity.getTitle());
            contentValues.put(HromContentProvider._CREATED, entity.getTitle());
            contentValues.put(HromContentProvider._VIDEO, entity.getTitle());
            contentValues.put(HromContentProvider._IMAGE, entity.getTitle());
            cVVector.add(contentValues);
        }
        ContentValues[] cvArray = new ContentValues[cVVector.size()];
        cVVector.toArray(cvArray);
        if (url.equals(SystemUtils.POLITICS_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.POLITICS_CONTENT_URI, cvArray);
        }else if (url.equals(SystemUtils.SOCIETY_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.SOCIETY_CONTENT_URI, cvArray);
        }else if (url.equals(SystemUtils.CULTURE_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.CULTURE_CONTENT_URI, cvArray);
        }else if (url.equals(SystemUtils.FILMS_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.FILMS_CONTENT_URI, cvArray);
        }else if (url.equals(SystemUtils.PHOTOES_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.PHOTOES_CONTENT_URI, cvArray);
        }else if (url.equals(SystemUtils.TEAM_URL)){
            context.getContentResolver().bulkInsert(HromContentProvider.TEAM_CONTENT_URI, cvArray);
        }
    }

}