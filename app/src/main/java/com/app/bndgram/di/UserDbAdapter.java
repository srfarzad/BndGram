package com.app.bndgram.di;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserDbAdapter extends DatabaseHelper implements IUserDb {


    public static final String KEY_TABLE = "tbl_users";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";


    @Inject
    public UserDbAdapter(Context context) {
        super(context);
    }

    @Override
    public long insert(UserModel user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, user.getUsername());
        contentValues.put(KEY_PASSWORD, user.getPassword());
        contentValues.put(KEY_EMAIL, user.getEmail());
        return db.insert(KEY_TABLE, null, contentValues);
    }

    @Override
    public long update(UserModel user) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PASSWORD, user.getPassword());
        return db.update(KEY_TABLE, contentValues, "id=" + user.getId(), null);
    }

    @Override
    public long delete(int id) {
        SQLiteDatabase db = getWritableDatabase();


        return db.delete(KEY_TABLE, "id=" + id, null);
    }

    @Override
    public long deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(KEY_TABLE, null, null);
    }

    @Override
    public int login(UserModel user) {

        int userId=0;

        SQLiteDatabase db = getReadableDatabase();

        String query_temp= "select * from " + KEY_TABLE + "  where "+ KEY_USERNAME +" = '"+ user.getUsername()
                +"' and  "+KEY_PASSWORD +" = '"+user.getPassword()+"' ";


        String[] params = {user.getUsername() , user.getPassword()};

        String query= "select * from " + KEY_TABLE + "  where "+ KEY_USERNAME +" =  ? and  "+KEY_PASSWORD +" = ? ";



        Cursor cursor= db.rawQuery(query,params);

      if(cursor.moveToNext()) {

          userId= cursor.getInt(cursor.getColumnIndex(KEY_ID));
          int id= cursor.getInt(0);
          String name = cursor.getString(cursor.getColumnIndex(KEY_USERNAME));

      }


        return userId;
    }

    @Override
    public List<UserModel> userslist() {

        SQLiteDatabase db = getReadableDatabase();

        List<UserModel> userList = new ArrayList<>();

        String query= "select * from "+KEY_TABLE;

        Cursor cursor = db.rawQuery(query, null);


        while(cursor.moveToNext()) {

            UserModel user = new UserModel();

            user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));

            userList.add(user);
        }



        return userList;
    }
}
