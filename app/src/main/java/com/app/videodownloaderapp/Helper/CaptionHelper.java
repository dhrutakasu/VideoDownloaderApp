package com.app.videodownloaderapp.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.videodownloaderapp.Models.Status;
import com.app.videodownloaderapp.Models.StatusType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CaptionHelper extends SQLiteOpenHelper {

    private static final String STATUS = "status";
    private static final String STATUS_TYPE = "status_type";
    private Context context;
    private static String DATABASE_NAME = "DataCaption.db";

    public CaptionHelper(Context context) {
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

    public ArrayList getStatusType() {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String table_name = "SELECT *FROM " + STATUS_TYPE;
        Cursor cursor = db.rawQuery(table_name, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(new StatusType(cursor.getString(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("title")),
                            cursor.getString(cursor.getColumnIndex("number"))));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }

    public ArrayList getStatus(String str) {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String table_name = "SELECT *FROM " + STATUS + " where type='" + str + "'";
        Cursor cursor = db.rawQuery(table_name, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(new Status(cursor.getString(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("type")),
                            cursor.getString(cursor.getColumnIndex("string"))));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }
}
