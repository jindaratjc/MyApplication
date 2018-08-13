package com.project.app.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("Instantiatable")
public class DatabaseActivity extends SQLiteOpenHelper {
    private static String DB_NAME = "storedb.sqlite";
    private static String TABLE_POINT = "point";
    private static String TABLE_STORE = "store";
    private static String TABLE_SUB_STORE = "substore";
    private static String TABLE_SUBPOINT = "subpoint";
    private static String TABLE_MAP = "map";
    private static String TABLE_CATEGORY_STORE = "category_store";
    private static Integer BUFFER_SIZE = 128;
    private SQLiteDatabase myDataBase;
    private final Context context;
    private DecimalFormat df = new DecimalFormat("#,###,###.##");
    String message = "";

    public DatabaseActivity(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public void openDatabase() {
        File dbFolder = context.getDatabasePath(DB_NAME).getParentFile();
        if (!dbFolder.exists()) {
            dbFolder.mkdir();
        }

        File dbFile = context.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
    }

    private void copyDatabase(File targetDbFile) throws IOException {
        // ***********************************************************************************
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(targetDbFile);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

        // ***********************************************************************************
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

  /*  public void InsertLogin(String user_id, String username, String password, String user_image, String type) {
        SQLiteDatabase db;
        try {
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT * FROM " + TABLE_USER + " ";
            Cursor cursor = db.rawQuery(strSQL, null);
            Integer count = cursor.getCount();
            cursor.close();
            db = this.getWritableDatabase(); // Update or Insert to database
            if (count > 0) {
                String strSQLInsert = "DELETE FROM " + TABLE_USER + " ";
                db.execSQL(strSQLInsert);
            }
            String strSQLInsert = "INSERT INTO " + TABLE_USER
                    + "(user_id, username, password, user_image, type) VALUES(" + user_id + ", '" + username + "', '" + password
                    + "', '" + user_image + "', '" + type + "') ";
            db.execSQL(strSQLInsert);
            db.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void DeleleLogin() {
        SQLiteDatabase db;
        try {
            db = this.getWritableDatabase(); // Update Delete or Insert to database
            String strSQLInsert = "DELETE FROM " + TABLE_USER + " ";
            db.execSQL(strSQLInsert);
            db.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public ArrayList<HashMap<String, String>> CheckLogin() {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT user_id, username, password, user_image, type FROM "
                    + TABLE_USER + " LIMIT 1 ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("user_id", cursor.getString(0));
                        map.put("username", cursor.getString(1));
                        map.put("password", cursor.getString(2));
                        map.put("user_image", cursor.getString(3));
                        map.put("type", cursor.getString(4));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SettingModel GetSetting() {
        try {
            SettingModel ret = new SettingModel();
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT font_size, bg_color FROM "
                    + TABLE_SETTING + " LIMIT 1 ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ret.setFontSize(Integer.parseInt(cursor.getString(0)));
                        ret.setBgColor(cursor.getString(1));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void AddSetting(SettingModel set) {
        SQLiteDatabase db;
        try {
            if(set != null){
                db = this.getReadableDatabase(); // Read Data
                String strSQL = "SELECT * FROM " + TABLE_SETTING + " ";
                Cursor cursor = db.rawQuery(strSQL, null);
                Integer count = cursor.getCount();
                cursor.close();
                db = this.getWritableDatabase(); // Update or Insert to database
                if (count > 0) {
                    String strSQLInsert = "DELETE FROM " + TABLE_SETTING + " ";
                    db.execSQL(strSQLInsert);
                }
                String strSQLInsert = "INSERT INTO " + TABLE_SETTING
                        + "(font_size, bg_color) VALUES('" + set.getFontSize() + "', '" + set.getBgColor() + "') ";
                db.execSQL(strSQLInsert);
                db.close();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }*/

    public ArrayList<HashMap<String, String>> GetStoreByName(String name) {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT store_id, store_name, store_image FROM "
                    + TABLE_STORE + " WHERE store_name LIKE '%" + name + "%' ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("store_id", cursor.getString(0));
                        map.put("store_name", cursor.getString(1));
                        map.put("store_image", cursor.getString(2));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> GetSubStoreById(String store_id) {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT " +
                    "ss.substore_id, " +
                    "ss.substore_name, " +
                    "ss.subpoint_id, " +
                    "ss.store_id, " +
                    "ss.substore_image," +
                    "ss.top_left," +
                    "ss.top_right," +
                    "ss.bottom_left," +
                    "ss.bottom_right, " +
                    "p.point_name " +
                    "FROM "
                    + TABLE_SUB_STORE + " ss INNER JOIN " + TABLE_SUBPOINT + " sp ON ss.subpoint_id = sp.id inner join "
                    + TABLE_POINT + " p ON sp.point_id = p.id"
                    +" WHERE ss.store_id = " + store_id + " ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("substore_id", cursor.getString(0));
                        map.put("substore_name", cursor.getString(1));
                        map.put("subpoint_id", cursor.getString(2));
                        map.put("store_id", cursor.getString(3));
                        map.put("substore_image", cursor.getString(4));
                        map.put("top_left", cursor.getString(5));
                        map.put("top_right", cursor.getString(6));
                        map.put("bottom_left", cursor.getString(7));
                        map.put("bottom_right", cursor.getString(8));
                        map.put("point_name", cursor.getString(9));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> GetSubStoreAll() {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT " +
                    "ss.substore_id, " +
                    "ss.substore_name, " +
                    "ss.subpoint_id, " +
                    "ss.store_id, " +
                    "ss.substore_image," +
                    "ss.top_left," +
                    "ss.top_right," +
                    "ss.bottom_left," +
                    "ss.bottom_right, " +
                    "p.point_name " +
                    "FROM "
                    + TABLE_SUB_STORE + " ss INNER JOIN " + TABLE_SUBPOINT + " sp ON ss.subpoint_id = sp.id inner join "
                    + TABLE_POINT + " p ON sp.point_id = p.id";
                    //+" WHERE ss.store_id = " + store_id + " ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("substore_id", cursor.getString(0));
                        map.put("substore_name", cursor.getString(1));
                        map.put("subpoint_id", cursor.getString(2));
                        map.put("store_id", cursor.getString(3));
                        map.put("substore_image", cursor.getString(4));
                        map.put("top_left", cursor.getString(5));
                        map.put("top_right", cursor.getString(6));
                        map.put("bottom_left", cursor.getString(7));
                        map.put("bottom_right", cursor.getString(8));
                        map.put("point_name", cursor.getString(9));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> GetStoreByCategoryId(String category_store_id) {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT store_id, store_name, store_image  FROM "
                    + TABLE_STORE + " WHERE category_store_id = " + category_store_id + " ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("store_id", cursor.getString(0));
                        map.put("store_name", cursor.getString(1));
                        map.put("store_image", cursor.getString(2));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> GetMapByMapID(String map_id) {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT map_id, map_img FROM "
                    + TABLE_MAP + " WHERE map_id = '" + map_id + "' ";
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("map_id", cursor.getString(0));
                        map.put("map_img", cursor.getString(1));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
