package com.app.videodownloaderapp.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.videodownloaderapp.Models.CategoryDb;
import com.app.videodownloaderapp.Models.QuoteModel;
import com.app.videodownloaderapp.Models.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class QuoteHelper extends SQLiteOpenHelper {

    private static final String QUOTES = "quotes";
    private static final String CATEGORY = "category";
    private Context context;
    private static String DATABASE_NAME = "DataQuotes.db";

    public QuoteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;

        if (!isDatabaseExists()) {
            try {
                copyDatabases();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private boolean isDatabaseExists() {
        return context.getDatabasePath(DATABASE_NAME).exists();
    }

    public void copyDatabases() throws IOException {
        File databasePath = context.getDatabasePath(DATABASE_NAME);
        if (!databasePath.exists()) {
            databasePath.getParentFile().mkdirs();
            InputStream stream = context.getAssets().open("Database/" + DATABASE_NAME);
            OutputStream fileOutputStream = new FileOutputStream(databasePath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = stream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            stream.close();
        }
    }

    public ArrayList getCategory() {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String table_name = "SELECT *FROM " + CATEGORY;
        Cursor cursor = db.rawQuery(table_name, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(new CategoryDb(cursor.getString(cursor.getColumnIndex("_id")),
                            cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getString(cursor.getColumnIndex("status"))));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList getQuoteId(String str) {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String table_name = "SELECT *FROM " + QUOTES + " where category_id='" + str + "'";
        Cursor cursor = db.rawQuery(table_name, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(new QuoteModel(cursor.getInt(cursor.getColumnIndex("_id")),
                            cursor.getInt(cursor.getColumnIndex("category_id")),
                            cursor.getInt(cursor.getColumnIndex("liked")),
                            cursor.getString(cursor.getColumnIndex("quote"))));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }

    public void UpdateQuoteFav(QuoteModel quoteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", quoteModel.getId());
        contentValues.put("category_id", quoteModel.getCategoryId());
        contentValues.put("quote", quoteModel.getQuote());
        contentValues.put("liked", quoteModel.getLikeQuote());

        db.update(QUOTES, contentValues, "_id = ?", new String[]{String.valueOf(quoteModel.getId())});
    }
}
