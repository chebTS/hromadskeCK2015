package com.hromadske.tv.ck.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Created by cheb on 29.12.2014.
 */
public class HromContentProvider extends ContentProvider {
    private static final String TAG = HromContentProvider.class.getSimpleName();

    static final String DB_NAME = "hromdb";
    static final int DB_VERSION = 1;

    DBHelper dbHelper;
    SQLiteDatabase db;

    static final String TEAM_TABLE = "team";

    static final String TEAM_ID = "_id";
    static final String TEAM_TITLE = "title";
    static final String TEAM_INTROTEXT = "introtext";
    static final String TEAM_FULLTEXT = "fulltext";
    static final String TEAM_CREATED = "created";
    static final String TEAM_VIDEO = "video";
    static final String TEAM_IMAGE = "image";

    static final String DB_CREATE = "create table " + TEAM_TABLE + "("
            + TEAM_ID + " integer primary key autoincrement, "
            + TEAM_TITLE + " text, "
            + TEAM_INTROTEXT + " text, "
            + TEAM_FULLTEXT + " text, "
            + TEAM_CREATED + " integer, "
            + TEAM_VIDEO + " text, "
            + TEAM_IMAGE + " text" + ");";

    static final String AUTHORITY = "com.hromadske.tv.ck.db.Hromadske";
    static final String TEAM_PATH = "team";

    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TEAM_PATH);

    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + TEAM_PATH;

    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + TEAM_PATH;

    static final int URI_TEAM = 1;
    static final int URI_TEAM_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TEAM_PATH, URI_TEAM);
        uriMatcher.addURI(AUTHORITY, TEAM_PATH + "/#", URI_TEAM_ID);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case URI_TEAM:

                break;
            case URI_TEAM_ID:

                break;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                cv.put(TEAM_TITLE, "title " + i);
                cv.put(TEAM_INTROTEXT, "introtext " + i);
                cv.put(TEAM_FULLTEXT, "ifulltext " + i);
                cv.put(TEAM_CREATED, "created " + i);
                cv.put(TEAM_VIDEO, "video " + i);
                cv.put(TEAM_IMAGE, "image " + i);
                db.insert(TEAM_TABLE, null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
