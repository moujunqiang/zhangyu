package com.android.zhangyu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class HistoryDao {
    Context context;
    HistoryDBHelper dbHelper;

    public HistoryDao(Context context) {
        this.context = context;
        dbHelper = new HistoryDBHelper(context, "history.db", null, 1);
    }

    public void insertHistory(HistoryBean bean) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("history_content", bean.getContent());
        cv.put("history_pinpai", bean.getPinpai());
        cv.put("history_image", bean.getIamge());
        cv.put("history_price", bean.getPrice());
        cv.put("history_type", bean.getType());
        cv.put("history_year", bean.getYear());
        sqLiteDatabase.insert("history_data", null, cv);
    }


    public List<HistoryBean> queryll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<HistoryBean> noteList = new ArrayList<>();
        HistoryBean note;
        String sql = "select * from history_data ";
        Cursor cursor = null;

        cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new HistoryBean();

            note.setContent(cursor.getString(cursor.getColumnIndex("history_content")));
            note.setIamge(cursor.getString(cursor.getColumnIndex("history_image")));
            note.setPrice(cursor.getString(cursor.getColumnIndex("history_price")));
            note.setType(cursor.getString(cursor.getColumnIndex("history_type")));
            note.setYear(cursor.getString(cursor.getColumnIndex("history_year")));
            note.setPinpai(cursor.getString(cursor.getColumnIndex("history_pinpai")));

            noteList.add(note);
        }

        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }

        return noteList;
    }


}
