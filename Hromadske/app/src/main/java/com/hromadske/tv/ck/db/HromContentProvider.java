package com.hromadske.tv.ck.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
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

    public static final String TABLE_TEAM = "team";

    public static final String _ID = "id";
    public static final String _TITLE = "title";
    public static final String _INTROTEXT = "introtext";
    public static final String _FULLTEXT = "fulltext";
    public static final String _CREATED = "created";
    public static final String _VIDEO = "video";
    public static final String _IMAGE = "image";

    static final String DB_CREATE = "create table " + TABLE_TEAM + "("
            + _ID + " integer primary key, "
            + _TITLE + " text, "
            + _INTROTEXT + " text, "
            + _FULLTEXT + " text, "
            + _CREATED + " integer, "
            + _VIDEO + " text, "
            + _IMAGE + " text" + ");";

    static final String AUTHORITY = "com.hromadske.tv.ck.db.Hromadske";
    static final String TEAM_PATH = "team";

    public static final Uri TEAM_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TEAM_PATH);

    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + TEAM_PATH;

    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + TEAM_PATH;

    static final int URI_TEAM_LIST = 1;
    static final int URI_TEAM_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TEAM_PATH, URI_TEAM_LIST);
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
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case URI_TEAM_LIST:
                qb.setTables(TABLE_TEAM);
                break;
            case URI_TEAM_ID:
                qb.setTables(TABLE_TEAM);
                qb.appendWhere(_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = qb.query(db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id;
        switch (uriMatcher.match(uri)){
            case URI_TEAM_LIST:
                id = db.insertWithOnConflict(TABLE_TEAM, null,  values, SQLiteDatabase.CONFLICT_REPLACE);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        }
        Uri itemUri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(itemUri, null);
        return itemUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (int i = 0; i < values.length; i++) {
            this.insert(uri, values[i]);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return values.length;
    }

    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case URI_TEAM_LIST:
                return CONTACT_CONTENT_TYPE;
            case URI_TEAM_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                cv.put(_TITLE, "title " + i);
                cv.put(_INTROTEXT, "introtext " + i);
                cv.put(_FULLTEXT, "ifulltext " + i);
                cv.put(_CREATED, "created " + i);
                cv.put(_VIDEO, "video " + i);
                cv.put(_IMAGE, "image " + i);
                db.insert(TABLE_TEAM, null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
            onCreate(db);
        }
    }

}
