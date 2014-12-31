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


    public static final String TABLE_POLITICS = "politics";
    public static final String TABLE_SOCIETY = "society";
    public static final String TABLE_CULTURE = "culture";
    public static final String TABLE_FILMS = "films";
    public static final String TABLE_PHOTOES = "photoes";
    public static final String TABLE_TEAM = "team";

    public static final String SUPER_ID = "_id";
    public static final String _ID = "id";
    public static final String _TITLE = "title";
    public static final String _INTROTEXT = "introtext";
    public static final String _FULLTEXT = "fulltext";
    public static final String _CREATED = "created";
    public static final String _VIDEO = "video";
    public static final String _IMAGE = "image";

    static String createTableScript(String tableName){
        return "create table " + tableName + "("
                + SUPER_ID + " integer primary key, "
                + _ID + " integer, "
                + _TITLE + " text, "
                + _INTROTEXT + " text, "
                + _FULLTEXT + " text, "
                + _CREATED + " integer, "
                + _VIDEO + " text, "
                + _IMAGE + " text" + ");";
    }

    static final String AUTHORITY = "com.hromadske.tv.ck.db.Hromadske";

    static final String POLITICS_PATH = "politics";
    static final String SOCIETY_PATH = "society";
    static final String CULTURE_PATH = "culture";
    static final String FILMS_PATH = "films";
    static final String PHOTOES_PATH = "photoes";
    static final String TEAM_PATH = "team";


    public static final Uri POLITICS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + POLITICS_PATH);
    public static final Uri SOCIETY_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + SOCIETY_PATH);
    public static final Uri CULTURE_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CULTURE_PATH);
    public static final Uri FILMS_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + FILMS_PATH);
    public static final Uri PHOTOES_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + PHOTOES_PATH);
    public static final Uri TEAM_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TEAM_PATH);



    static final String POLITICS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + POLITICS_PATH;
    static final String POLITICS_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + POLITICS_PATH;

    static final String SOCIETY_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + SOCIETY_PATH;
    static final String SOCIETY_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + SOCIETY_PATH;

    static final String CULTURE_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CULTURE_PATH;
    static final String CULTURE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CULTURE_PATH;

    static final String FILMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + FILMS_PATH;
    static final String FILMS_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + FILMS_PATH;

    static final String PHOTOES_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PHOTOES_PATH;
    static final String PHOTOES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PHOTOES_PATH;

        static final String TEAM_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + TEAM_PATH;
    static final String TEAM_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + TEAM_PATH;


    static final int URI_POLITICS_LIST = 1;
    static final int URI_POLITICS_ID = 2;
    static final int URI_SOCIETY_LIST = 3;
    static final int URI_SOCIETY_ID = 4;
    static final int URI_CULTURE_LIST = 5;
    static final int URI_CULTURE_ID = 6;
    static final int URI_FILMS_LIST = 7;
    static final int URI_FILMS_ID = 8;
    static final int URI_PHOTOES_LIST = 9;
    static final int URI_PHOTOES_ID = 10;
    static final int URI_TEAM_LIST = 11;
    static final int URI_TEAM_ID = 12;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, POLITICS_PATH, URI_POLITICS_LIST);
        uriMatcher.addURI(AUTHORITY, POLITICS_PATH + "/#", URI_POLITICS_ID);

        uriMatcher.addURI(AUTHORITY, SOCIETY_PATH, URI_SOCIETY_LIST);
        uriMatcher.addURI(AUTHORITY, SOCIETY_PATH + "/#", URI_SOCIETY_ID);

        uriMatcher.addURI(AUTHORITY, CULTURE_PATH, URI_CULTURE_LIST);
        uriMatcher.addURI(AUTHORITY, CULTURE_PATH + "/#", URI_CULTURE_ID);

        uriMatcher.addURI(AUTHORITY, FILMS_PATH, URI_FILMS_LIST);
        uriMatcher.addURI(AUTHORITY, FILMS_PATH + "/#", URI_FILMS_ID);

        uriMatcher.addURI(AUTHORITY, PHOTOES_PATH, URI_PHOTOES_LIST);
        uriMatcher.addURI(AUTHORITY, PHOTOES_PATH + "/#", URI_PHOTOES_ID);

        uriMatcher.addURI(AUTHORITY, TEAM_PATH, URI_TEAM_LIST);
        uriMatcher.addURI(AUTHORITY, TEAM_PATH + "/#", URI_TEAM_ID);
    }

    @Override
    public boolean onCreate() {
        //Log.d(TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {

            case URI_POLITICS_LIST:
                qb.setTables(TABLE_POLITICS);
                break;
            case URI_POLITICS_ID:
                qb.setTables(TABLE_POLITICS);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
                break;

            case URI_SOCIETY_LIST:
                qb.setTables(TABLE_SOCIETY);
                break;
            case URI_SOCIETY_ID:
                qb.setTables(TABLE_SOCIETY);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
                break;

            case URI_CULTURE_LIST:
                qb.setTables(TABLE_CULTURE);
                break;
            case URI_CULTURE_ID:
                qb.setTables(TABLE_CULTURE);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
                break;

            case URI_FILMS_LIST:
                qb.setTables(TABLE_FILMS);
                break;
            case URI_FILMS_ID:
                qb.setTables(TABLE_FILMS);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
                break;

            case URI_PHOTOES_LIST:
                qb.setTables(TABLE_PHOTOES);
                break;
            case URI_PHOTOES_ID:
                qb.setTables(TABLE_PHOTOES);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
                break;

            case URI_TEAM_LIST:
                qb.setTables(TABLE_TEAM);
                break;
            case URI_TEAM_ID:
                qb.setTables(TABLE_TEAM);
                qb.appendWhere(SUPER_ID + "=" + uri.getLastPathSegment());
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
            case URI_POLITICS_LIST:
                id = db.insertWithOnConflict(TABLE_POLITICS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;

            case URI_SOCIETY_LIST:
                id = db.insertWithOnConflict(TABLE_SOCIETY, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;

            case URI_CULTURE_LIST:
                id = db.insertWithOnConflict(TABLE_CULTURE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;

            case URI_FILMS_LIST:
                id = db.insertWithOnConflict(TABLE_FILMS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;

            case URI_PHOTOES_LIST:
                id = db.insertWithOnConflict(TABLE_PHOTOES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;

            case URI_TEAM_LIST:
                id = db.insertWithOnConflict(TABLE_TEAM, null, values, SQLiteDatabase.CONFLICT_REPLACE);
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
            case URI_POLITICS_LIST:
                return POLITICS_CONTENT_TYPE;
            case URI_POLITICS_ID:
                return POLITICS_CONTENT_ITEM_TYPE;

            case URI_SOCIETY_LIST:
                return SOCIETY_CONTENT_TYPE;
            case URI_SOCIETY_ID:
                return SOCIETY_CONTENT_ITEM_TYPE;

            case URI_CULTURE_LIST:
                return CULTURE_CONTENT_TYPE;
            case URI_CULTURE_ID:
                return CULTURE_CONTENT_ITEM_TYPE;

            case URI_FILMS_LIST:
                return FILMS_CONTENT_TYPE;
            case URI_FILMS_ID:
                return FILMS_CONTENT_ITEM_TYPE;

            case URI_PHOTOES_LIST:
                return PHOTOES_CONTENT_TYPE;
            case URI_PHOTOES_ID:
                return PHOTOES_CONTENT_ITEM_TYPE;

            case URI_TEAM_LIST:
                return TEAM_CONTENT_TYPE;
            case URI_TEAM_ID:
                return TEAM_CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createTableScript(TABLE_POLITICS));
            db.execSQL(createTableScript(TABLE_SOCIETY));
            db.execSQL(createTableScript(TABLE_CULTURE));
            db.execSQL(createTableScript(TABLE_FILMS));
            db.execSQL(createTableScript(TABLE_PHOTOES));
            db.execSQL(createTableScript(TABLE_TEAM));
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
            onCreate(db);
        }
    }

}


           /* ContentValues cv = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                cv.put(_TITLE, "title " + i);
                cv.put(_INTROTEXT, "introtext " + i);
                cv.put(_FULLTEXT, "ifulltext " + i);
                cv.put(_CREATED, "created " + i);
                cv.put(_VIDEO, "video " + i);
                cv.put(_IMAGE, "image " + i);
                db.insert(TABLE_TEAM, null, cv);
            }*/

    /*static final String DB_CREATE = "create table " + TABLE_TEAM + "("
            + _ID + " integer primary key, "
            + _TITLE + " text, "
            + _INTROTEXT + " text, "
            + _FULLTEXT + " text, "
            + _CREATED + " integer, "
            + _VIDEO + " text, "
            + _IMAGE + " text" + ");";*/
