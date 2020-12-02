package com.android.zhangyu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HistoryDBHelper extends SQLiteOpenHelper {
    public HistoryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists history_data(" +
                "history_id integer primary key autoincrement," +
                "history_content varchar," +
                "history_pinpai varchar," +
                "history_type varchar," +
                "history_image varchar," +
                "history_price varchar," +
                "history_year varchar)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
