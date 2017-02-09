package com.example.wen.mylife.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wen.mylife.bean.Diary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class DiaryDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_Title = "title";
    public static final String KEY_Account = "account";
    public static final String KEY_CONTENT= "content";
    public static final String KEY_TIME = "time";


    private static final String TAG = "DiaryDbAdapter";
    private DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;


    static final String SQLITE_TABLE = "DiaryTable";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {

            super(context, CommDB.DATABASE_NAME, null, CommDB.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Log.w(TAG, DATABASE_CREATE);
            // db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DiaryDB(Context ctx) {
        this.mCtx = ctx;
    }

    public DiaryDB open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public static  long createUser(String title, String account, String content,String time) {
        long createResult = 0;
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_Title, title);
        initialValues.put(KEY_Account, account);
        initialValues.put(KEY_CONTENT, content);
        initialValues.put(KEY_TIME, time);
        try {
            createResult = mDb.insert(SQLITE_TABLE, null, initialValues);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return createResult;
    }

    public boolean deleteAllUsers() {
        int doneDelete = 0;
        try {
            doneDelete = mDb.delete(SQLITE_TABLE, null, null);
            Log.w(TAG, Integer.toString(doneDelete));
            Log.e("doneDelete", doneDelete + "");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return doneDelete > 0;
    }

    // 扫描时进行判断本地数据库是否有此
    public static ArrayList<Diary> fetchAll() {

        ArrayList<Diary> allDiaryList = new ArrayList<Diary>();
        Cursor mCursor = null;
        mCursor = mDb.query(SQLITE_TABLE, new String[] { KEY_ROWID, KEY_Title,
                KEY_Account, KEY_CONTENT,KEY_TIME }, null, null, null, null, null);
        if (mCursor.moveToFirst()) {
            do {
                Diary diary = new Diary();
                diary.setTitle(mCursor.getString(mCursor
                        .getColumnIndexOrThrow(KEY_Title)));
                diary.setAccount(mCursor.getString(mCursor
                        .getColumnIndexOrThrow(KEY_Account)));
                diary.setContent(mCursor.getString(mCursor
                        .getColumnIndexOrThrow(KEY_CONTENT)));
                diary.setTime(mCursor.getString(mCursor
                        .getColumnIndexOrThrow(KEY_TIME)));
                allDiaryList.add(diary);
            } while (mCursor.moveToNext());
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        return allDiaryList;
    }

    public Diary queryDiaryByName(String name){
        Diary diary = new Diary();
        List<Diary> diaryList = fetchAll();
        diaryList =fetchAll();
        for (int i = 0; i < diaryList.size(); i++) {
            if(diaryList.get(i).getTitle().equals(name)){
                diary.setTitle(diaryList.get(i).getTitle());
                diary.setAccount(diaryList.get(i).getAccount());
                diary.setContent(diaryList.get(i).getContent());
                diary.setTime(diaryList.get(i).getTime());
            }
        }
        return diary;
    }
    public void updatePassword(String name,String password){
        mDb.execSQL("update UserTable set password=? where name=?", new Object[]{password, name});
    }
    //删除一条数据
    public void deleteWaiter(String name) {

        mDb.execSQL("delete from UserTable where name=?", new Object[]{name});

    }

}
