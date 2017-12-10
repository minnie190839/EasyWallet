package com.example.win8.easywallet.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Win8 on 10/12/2560.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plan.db";
    private static final int DATABASE_VERSION = 8;

    public static final String TABLE_NAME = "budget_plan";
    public static final String COL_ID = "_id";
    public static final String COL_TYPE = "_type";
    public static final String COL_TITLE = "title";
    public static final String COL_MONEY = "money";
    public static final String COL_PICTURE = "picture";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TYPE + " TEXT, "
            + COL_TITLE + " TEXT, "
            + COL_MONEY + " TEXT, "
            + COL_PICTURE + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "คุณพ่อให้เงิน");
        cv.put(COL_TYPE, "1");
        cv.put(COL_MONEY, "8000");
        cv.put(COL_PICTURE, "ic_income.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "จ่ายค่าหอ");
        cv.put(COL_TYPE, "2");
        cv.put(COL_MONEY, "3500");
        cv.put(COL_PICTURE, "ic_expense.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TITLE, "ซื้อของเข้าหอ");
        cv.put(COL_TYPE, "2");
        cv.put(COL_MONEY, "800");
        cv.put(COL_PICTURE, "ic_expense.png");
        db.insert(TABLE_NAME, null, cv);
    }
        @Override
        public void onUpgrade (SQLiteDatabase db,int i, int i1){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

