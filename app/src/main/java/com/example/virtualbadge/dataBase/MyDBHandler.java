package com.example.virtualbadge.dataBase;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeDB.db";
    private static final String TABLE_NAME = "employees";
    private static final String LOGIN = "login";
    private static final String PIN = "pin";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PHOTO_PATH = "photoPath";


    //initialize the database
    public MyDBHandler(Activity activity, String name, SQLiteDatabase.CursorFactory factory) {
        super(activity, DATABASE_NAME, factory, DATABASE_VERSION);
        String photo = "drawable/admin_picture.PNG";

//        Employee employee = new Employee("admin", "admin", "admin", "admin");


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String photo = "drawable/admin_picture.PNG";
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +LOGIN + " VARCHAR(6) PRIMARY KEY, "+ PIN + " VARCHAR(8), " + NAME + " VARCHAR(12), " + SURNAME + " VARCHAR(16))");

        Employee admin = new Employee("admin", "admin", "admin", "admin");

        Employee employee = new Employee("e", "e", "employee", "employee");

//        addHandler(employee, db);
        ContentValues values = new ContentValues();
        values.put(LOGIN, admin.getLogin());
        values.put(PIN, admin.getPin());
        values.put(NAME, admin.getName());
        values.put(SURNAME, admin.getSurname());
        db.insert(TABLE_NAME, null, values);


        ContentValues values1 = new ContentValues();
        values1.put(LOGIN, employee.getLogin());
        values1.put(PIN, employee.getPin());
        values1.put(NAME, employee.getName());
        values1.put(SURNAME, employee.getSurname());
        db.insert(TABLE_NAME, null, values1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        StringBuilder result = new StringBuilder();
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            int result_1 = cursor.getInt(1);
            String result_2 = cursor.getString(0);
            String result_3 = cursor.getString(0);
            String result_4 = cursor.getString(0);

            result.append(String.valueOf(result_0)).append(" ").append(result_1)
                    .append(result_2).append(result_3)
                    .append(result_4).append(System.getProperty("line.separator"));
        }
        cursor.close();
        db.close();
        return result.toString();
    }

    public Boolean addHandler(Employee employee, SQLiteDatabase db) {
//        if(checkUniqueHandler(employee.getLogin(), db)) {
        boolean result = false;
            ContentValues values = new ContentValues();
            values.put(LOGIN, employee.getLogin());
            values.put(PIN, employee.getPin());
            values.put(NAME, employee.getName());
            values.put(SURNAME, employee.getSurname());
            if(!employee.getLogin().equals("") && !employee.getPin().equals("")
                    && !employee.getName().equals("") && !employee.getSurname().equals("")) {
                try {
                    System.out.println("puszczam");
                    db.insert(TABLE_NAME, null, values);
                    result = true;
                }catch (Exception ex){
                    ex.getMessage();
                    System.out.println(ex.getMessage());
                    result = false;
                }
                finally {
                    db.close();
                    System.out.println("koncze");
                    return result;
                }
            }
            else {
                db.close();
                return false;
            }
//        }
    }



    public Boolean deleteHandler(String loginT, SQLiteDatabase db) {
        String login = loginT.toLowerCase();
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + LOGIN + " = '" + login + "'";
        Cursor cursor = db.rawQuery(query, null);
        Employee employee = new Employee();
        if (cursor.moveToFirst()) {
            employee.setLogin(cursor.getString(0));
            db.delete(TABLE_NAME, LOGIN + "=?",
                    new String[] {
                String.valueOf(employee.getLogin())
            });
            cursor.close();
            return true;
        }
        db.close();
        return false;
    }
    public Employee tryLogin(String login, String pin, SQLiteDatabase db){
        login = login.toLowerCase();
        Employee employee = null;


            String query = "Select * FROM " + TABLE_NAME + " WHERE " + LOGIN + " = " + "'" + login + "'";

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()) {
                if (pin.equals(cursor.getString(1))) {
                    employee = new Employee(cursor.getString(0),
                            cursor.getString(1), cursor.getString(2),
                            cursor.getString(3));
                }
            }
        return employee;
    }
}