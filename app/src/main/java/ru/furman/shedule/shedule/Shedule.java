package ru.furman.shedule.shedule;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import ru.furman.shedule.R;

/**
 * Created by Furman on 19.03.2017.
 */

public class Shedule extends ArrayList<SheduleWeek> {

    public final static String LOGS_TAG = "SheduleLogs";

    private Calendar dateOfStudyingStart;

    public void setDateOfStudyingStart(Calendar dateOfStudyingStart) {
        this.dateOfStudyingStart = dateOfStudyingStart;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public void setUniversityGroup(String universityGroup) {
        this.universityGroup = universityGroup;
    }

    public String getUniversityName() {
        return universityName;

    }

    public String getUniversityGroup() {
        return universityGroup;
    }

    private String universityName;
    private String universityGroup;

    public Shedule(Calendar dateOfStudyingStart) {
        super();
        this.dateOfStudyingStart = dateOfStudyingStart;
    }

    public Shedule(Calendar dateOfStudyingStart, String universityName, String universityGroup, SheduleWeek... sheduleWeeks) {
        super();
        this.dateOfStudyingStart = dateOfStudyingStart;
        this.universityName = universityName;
        this.universityGroup = universityGroup;

        for (int i = 0; i < sheduleWeeks.length; i++) {
            add(sheduleWeeks[i]);
        }
    }

    public Shedule(){
        super();
    }

    public Calendar getDateOfStudyingStart() {
        return dateOfStudyingStart;
    }

    public void logShedule() {
        Log.d(LOGS_TAG, "Shedule for group " + universityGroup + " of " + universityName + ". Shedule consists from " + size() + " weeks:");
        if (!isEmpty()) {
            for (int i = 0; i < this.size(); i++) {
                Log.d(LOGS_TAG, "    Week № " + (i + 1) + ":");
                for (int j = 0; j < 7; j++) {
                    Log.d(LOGS_TAG, "        " + SheduleWeek.getDayOfWeekInString(j + 1) + ":");
                    SheduleDay sheduleDay = get(i).get(SheduleWeek.getDayOfWeekInString(j + 1));
                    if (sheduleDay != null) {
                        for (int k = 0; k < sheduleDay.size(); k++) {
                            DoublePeriod dp = sheduleDay.get(k);
                            Log.d(LOGS_TAG, "            Double period №" + (k + 1));
                            Log.d(LOGS_TAG, "                Time of double period: " + DoublePeriod.timeToString((long) dp.get(DoublePeriod.BEGINNING_TIME)) + " - " + DoublePeriod.timeToString((long) dp.get(DoublePeriod.ENDING_TIME)));
                            Log.d(LOGS_TAG, "                Name of double period: " + dp.get(DoublePeriod.NAME));
                            Log.d(LOGS_TAG, "                Teacher's name of double period: " + dp.get(DoublePeriod.TEACHER));
                            Log.d(LOGS_TAG, "                Place of of double period: " + dp.get(DoublePeriod.PLACE));
                            Log.d(LOGS_TAG, "                Type of of double period: " + dp.get(DoublePeriod.TYPE));
                        }
                    } else
                        Log.d(LOGS_TAG, "            There are no double periods!");
                }
            }
        } else
            Log.d(LOGS_TAG, "    Shedule is empty!");
    }

}
