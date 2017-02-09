package com.example.wen.mylife.DB;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wen.mylife.bean.Diary;

public class CommDB {
	public static final String DATABASE_NAME = "myLife"; //数据库名称
	 
    public static final int DATABASE_VERSION = 1;
    
 //创建该数据库下User的语句
   private static final String CREATE_TABLE_Diarys =
         "CREATE TABLE if not exists " + DiaryDB.SQLITE_TABLE + " (" +
                 DiaryDB.KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                 DiaryDB.KEY_Title + "," +
                 DiaryDB.KEY_Account + "," +
                 DiaryDB.KEY_CONTENT +
        		 "," + DiaryDB.KEY_TIME + ")";

   private final Context context; 
   private DatabaseHelper DBHelper;
   private SQLiteDatabase db;
   /**
    * Constructor
    * @param ctx
    */
   public CommDB(Context ctx)
   {
       this.context = ctx;
       this.DBHelper = new DatabaseHelper(this.context);
   }

   private static class DatabaseHelper extends SQLiteOpenHelper 
   {
       DatabaseHelper(Context context) 
       {
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
       }

       @Override
       public void onCreate(SQLiteDatabase db) 
       {
           
           db.execSQL(CREATE_TABLE_Diarys);//创建diary表

       }

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, 
       int newVersion) 
       {               
           // Adding any table mods to this guy here
       }
   } 

  /**
    * open the db
    * @return this
   * @throws SQLException
    * return type: DBAdapter
*/
   public CommDB open() throws SQLException 
   {
       this.db = this.DBHelper.getWritableDatabase();
       return this;
   }
 
   /**
    * close the db 
    * return type: void
    */
   public void close() 
   {
       this.DBHelper.close();
   }
}
