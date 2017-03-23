package ru.furman.shedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ru.furman.shedule.shedule.DoublePeriod;
import ru.furman.shedule.shedule.Shedule;
import ru.furman.shedule.shedule.SheduleDay;
import ru.furman.shedule.shedule.SheduleWeek;

/**
 * Created by Furman on 22.03.2017.
 */

public class DBShedule {

    private static final String DB_NAME = "myShedule";
    private static final int DB_VERSION = 1;

    private static final String SHEDULE_TABLE = "shedules";
    public static final String SHEDULE_COLUMN_DATE_OF_STUDYING_START = "studying_start";
    public static final String SHEDULE_COLUMN_UNIVERSITY = "university";
    public static final String SHEDULE_COLUMN_GROUP = "groups";
    public static final String SHEDULE_COLUMN_ID = "_id";
    private static final String SHEDULE_TABLE_CREATE = "create table " + SHEDULE_TABLE + "(" + SHEDULE_COLUMN_ID + " integer primary key, " + SHEDULE_COLUMN_DATE_OF_STUDYING_START + " integer, " + SHEDULE_COLUMN_UNIVERSITY + " text, " + SHEDULE_COLUMN_GROUP + " text);";

    private static final String WEEK_TABLE = "weeks";
    public static final String WEEK_COLUMN_ID = "_id";
    public static final String WEEK_COLUMN_NUMBER = "number";
    public static final String WEEK_COLUMN_SHEDULE_ID = "shedule_id";
    private static final String WEEK_TABLE_CREATE = "create table " + WEEK_TABLE + "(" + WEEK_COLUMN_NUMBER + " integer, " + WEEK_COLUMN_ID + " integer primary key, " + WEEK_COLUMN_SHEDULE_ID + " integer);";


    private static final String DAY_TABLE = "days";
    public static final String DAY_COLUMN_DAY_OF_WEEK = "day_of_week";
    public static final String DAY_COLUMN_WEEK_ID = "week_id";
    public static final String DAY_COLUMN_ID = "_id";
    private static final String DAY_TABLE_CREATE = "create table " + DAY_TABLE + "(" + DAY_COLUMN_DAY_OF_WEEK + " integer, " + DAY_COLUMN_WEEK_ID + " integer, " + DAY_COLUMN_ID + " integer primare key);";

    private static final String DP_TABLE = "dp";
    public static final String DP_COLUMN_ID = "_id";
    public static final String DP_COLUMN_NAME = "name";
    public static final String DP_COLUMN_TEACHER_NAME = "teacher_name";
    public static final String DP_COLUMN_PLACE = "place";
    public static final String DP_COLUMN_BEGIN_TIME = "begin_time";
    public static final String DP_COLUMN_END_TIME = "end_time";
    public static final String DP_COLUMN_TYPE = "type";
    public static final String DP_COLUMN_DAY_ID = "day_id";
    private static final String DP_TABLE_CREATE = "create table " + DP_TABLE + "(" + DP_COLUMN_ID + " integer primary key autoincrement, " + DP_COLUMN_NAME + " text, " + DP_COLUMN_TEACHER_NAME + " text, " + DP_COLUMN_PLACE + " text, " + DP_COLUMN_BEGIN_TIME + " integer, " + DP_COLUMN_END_TIME + " integer, " + DP_COLUMN_TYPE + " text, " + DP_COLUMN_DAY_ID + " integer);";

    private DBSheduleHelper dbHelper;
    private SQLiteDatabase db;
    private Context ctx;

    public DBShedule(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        dbHelper = new DBSheduleHelper(ctx, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null)
            dbHelper.close();
    }

    public int getShedulesCount() {
        return getMaxId(SHEDULE_TABLE);
    }


    //заменить insert на update
    public void setSheduleInDatabase(int id, Shedule shedule) {
        if ((id >= 0) && (id <= getMaxId(SHEDULE_TABLE))) {
            int sheduleId = id;

            ContentValues cv = new ContentValues();

            cv.clear();
            cv.put(SHEDULE_COLUMN_DATE_OF_STUDYING_START, shedule.getDateOfStudyingStart().getTimeInMillis());
            cv.put(SHEDULE_COLUMN_GROUP, shedule.getUniversityGroup());
            cv.put(SHEDULE_COLUMN_UNIVERSITY, shedule.getUniversityName());
            cv.put(SHEDULE_COLUMN_ID, sheduleId);
            db.update(SHEDULE_TABLE,cv,SHEDULE_COLUMN_ID+" = "+id,null);

            for (int i = 0; i < shedule.size(); i++) {
                int weekId = getMaxId(WEEK_TABLE) + 1;

                cv.clear();
                cv.put(WEEK_COLUMN_ID, weekId);
                cv.put(WEEK_COLUMN_NUMBER, i + 1);
                cv.put(WEEK_COLUMN_SHEDULE_ID, sheduleId);
                db.insert(WEEK_TABLE, null, cv);

                for (int j = 1; j <= 7; j++) {
                    int dayId = getMaxId(DAY_TABLE) + 1;

                    cv.clear();
                    cv.put(DAY_COLUMN_DAY_OF_WEEK, j);
                    cv.put(DAY_COLUMN_WEEK_ID, weekId);
                    cv.put(DAY_COLUMN_ID, dayId);
                    db.insert(DAY_TABLE, null, cv);

                    for (int k = 0; k < shedule.get(i).get(SheduleWeek.getDayOfWeekInString(j)).size(); k++) {
                        DoublePeriod dp = shedule.get(i).get(SheduleWeek.getDayOfWeekInString(j)).get(k);

                        cv.clear();
                        cv.put(DP_COLUMN_BEGIN_TIME, (int) dp.get(DoublePeriod.BEGINNING_TIME));
                        cv.put(DP_COLUMN_END_TIME, (int) dp.get(DoublePeriod.ENDING_TIME));
                        cv.put(DP_COLUMN_DAY_ID, dayId);
                        cv.put(DP_COLUMN_NAME, (String) dp.get(DoublePeriod.NAME));
                        cv.put(DP_COLUMN_PLACE, (String) dp.get(DoublePeriod.PLACE));
                        cv.put(DP_COLUMN_TEACHER_NAME, (String) dp.get(DoublePeriod.TEACHER));
                        cv.put(DP_COLUMN_TYPE, (String) dp.get(DoublePeriod.TYPE));
                        db.insert(DP_TABLE, null, cv);
                    }
                }
            }
        }
    }

    public void addSheduleToDatabase(Shedule shedule) {
        int sheduleId = getMaxId(SHEDULE_TABLE) + 1;

        ContentValues cv = new ContentValues();

        cv.clear();
        cv.put(SHEDULE_COLUMN_DATE_OF_STUDYING_START, shedule.getDateOfStudyingStart().getTimeInMillis());
        cv.put(SHEDULE_COLUMN_GROUP, shedule.getUniversityGroup());
        cv.put(SHEDULE_COLUMN_UNIVERSITY, shedule.getUniversityName());
        cv.put(SHEDULE_COLUMN_ID, sheduleId);
        db.insert(SHEDULE_TABLE, null, cv);

        for (int i = 0; i < shedule.size(); i++) {
            int weekId = getMaxId(WEEK_TABLE) + 1;

            cv.clear();
            cv.put(WEEK_COLUMN_ID, weekId);
            cv.put(WEEK_COLUMN_NUMBER, i + 1);
            cv.put(WEEK_COLUMN_SHEDULE_ID, sheduleId);
            db.insert(WEEK_TABLE, null, cv);

            for (int j = 1; j <= 7; j++) {
                int dayId = getMaxId(DAY_TABLE) + 1;

                cv.clear();
                cv.put(DAY_COLUMN_DAY_OF_WEEK, j);
                cv.put(DAY_COLUMN_WEEK_ID, weekId);
                cv.put(DAY_COLUMN_ID, dayId);
                db.insert(DAY_TABLE, null, cv);

                for (int k = 0; k < shedule.get(i).get(SheduleWeek.getDayOfWeekInString(j)).size(); k++) {
                    DoublePeriod dp = shedule.get(i).get(SheduleWeek.getDayOfWeekInString(j)).get(k);

                    cv.clear();
                    cv.put(DP_COLUMN_BEGIN_TIME, (int) dp.get(DoublePeriod.BEGINNING_TIME));
                    cv.put(DP_COLUMN_END_TIME, (int) dp.get(DoublePeriod.ENDING_TIME));
                    cv.put(DP_COLUMN_DAY_ID, dayId);
                    cv.put(DP_COLUMN_NAME, (String) dp.get(DoublePeriod.NAME));
                    cv.put(DP_COLUMN_PLACE, (String) dp.get(DoublePeriod.PLACE));
                    cv.put(DP_COLUMN_TEACHER_NAME, (String) dp.get(DoublePeriod.TEACHER));
                    cv.put(DP_COLUMN_TYPE, (String) dp.get(DoublePeriod.TYPE));
                    db.insert(DP_TABLE, null, cv);
                }
            }
        }
    }

    public Shedule getShedule(int sheduleId) {
        Shedule shedule = new Shedule();

        Cursor cursor;
        cursor = db.query(SHEDULE_TABLE, null, SHEDULE_COLUMN_ID + " = " + sheduleId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(cursor.getInt(cursor.getColumnIndex(SHEDULE_COLUMN_DATE_OF_STUDYING_START)));
            shedule.setDateOfStudyingStart(calendar);
            shedule.setUniversityGroup(cursor.getString(cursor.getColumnIndex(SHEDULE_COLUMN_GROUP)));
            shedule.setUniversityName(cursor.getString(cursor.getColumnIndex(SHEDULE_COLUMN_UNIVERSITY)));
            cursor.close();

            cursor = db.query(WEEK_TABLE, null, WEEK_COLUMN_SHEDULE_ID + " = " + sheduleId, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    int weekId = cursor.getInt(cursor.getColumnIndex(WEEK_COLUMN_ID));
                    SheduleWeek sheduleWeek = new SheduleWeek();
                    Cursor cursorDay = db.query(DAY_TABLE, null, DAY_COLUMN_WEEK_ID + " = " + weekId, null, null, null, null);
                    if (cursorDay != null) {
                        cursorDay.moveToFirst();
                        do {
                            int dayId = cursorDay.getInt(cursorDay.getColumnIndex(DAY_COLUMN_ID));
                            SheduleDay sheduleDay = new SheduleDay();
                            Cursor cursorDP = db.query(DP_TABLE, null, DP_COLUMN_DAY_ID + " = " + dayId, null, null, null, null);
                            if (cursorDP != null) {
                                cursorDP.moveToFirst();
                                do {
                                    DoublePeriod dp = new DoublePeriod(
                                            cursorDP.getString(cursorDP.getColumnIndex(DoublePeriod.NAME)),
                                            cursorDP.getString(cursorDP.getColumnIndex(DoublePeriod.TEACHER)),
                                            cursorDP.getString(cursorDP.getColumnIndex(DoublePeriod.PLACE)),
                                            cursorDP.getInt(cursorDP.getColumnIndex(DoublePeriod.BEGINNING_TIME)),
                                            cursorDP.getInt(cursorDP.getColumnIndex(DoublePeriod.ENDING_TIME)),
                                            cursorDP.getString(cursorDP.getColumnIndex(DoublePeriod.TYPE))
                                    );
                                    sheduleDay.add(dp);
                                } while (cursorDP.moveToNext());
                            }
                            sheduleWeek.put(SheduleWeek.getDayOfWeekInString(cursorDay.getInt(cursorDay.getColumnIndex(DAY_COLUMN_DAY_OF_WEEK))), sheduleDay);
                        } while (cursorDay.moveToNext());
                    }
                    shedule.add(cursor.getInt(cursor.getColumnIndex(WEEK_COLUMN_NUMBER)), sheduleWeek);
                } while (cursor.moveToNext());
            }
            return shedule;
        } else
            return null;
    }

    public List<Shedule> getShedulesInfo(){
        List<Shedule> sheduleList = new ArrayList<>();
        Cursor cursor = db.query(SHEDULE_TABLE,null,null,null,null,null,null);
        if(cursor!=null){
            Shedule shedule = new Shedule();
            shedule.setUniversityName(cursor.getString(cursor.getColumnIndex(SHEDULE_COLUMN_UNIVERSITY)));
            shedule.setUniversityGroup(cursor.getString(cursor.getColumnIndex(SHEDULE_COLUMN_GROUP)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(cursor.getInt(cursor.getColumnIndex(SHEDULE_COLUMN_DATE_OF_STUDYING_START)));
            shedule.setDateOfStudyingStart(calendar);
            sheduleList.add(shedule);
        }
        return sheduleList;
    }

    private int getMaxId(String tableName) {
        Cursor cursor = db.rawQuery("select max(_id) from " + tableName, null);
        int res = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                res++;
                while (cursor.moveToNext()) {
                    res++;
                }
            }
        }
        cursor.close();
        return res;
    }

    public int getSheduleId(Shedule shedule){
        Cursor cursor = db.query(SHEDULE_TABLE,null,SHEDULE_COLUMN_UNIVERSITY+" = "+shedule.getUniversityName()+", "+SHEDULE_COLUMN_GROUP+" = "+shedule.getUniversityGroup()+", "+SHEDULE_COLUMN_DATE_OF_STUDYING_START+" = "+String.valueOf(shedule.getDateOfStudyingStart().getTimeInMillis()),null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex(SHEDULE_COLUMN_ID));
        }
        else
            return -1;
    }


    private class DBSheduleHelper extends SQLiteOpenHelper {

        public DBSheduleHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SHEDULE_TABLE_CREATE);
            db.execSQL(WEEK_TABLE_CREATE);
            db.execSQL(DAY_TABLE_CREATE);
            db.execSQL(DP_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


}
