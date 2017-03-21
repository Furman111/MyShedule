package ru.furman.shedule.shedule;

import java.io.IOException;
import java.text.Format;
import java.util.Formatter;
import java.util.HashMap;

/**
 * Created by Furman on 17.03.2017.
 */

public class DoublePeriod extends HashMap<String, Object> {

    public static final String LECTURE = "Lecture";
    public static final String PRACTICE = "Practice";
    public static final String LAB = "Lab";

    public static final String NAME = "NameOfDoublePeiod";
    public static final String TEACHER = "TeacherOfDoublePeriod";
    public static final String TYPE = "TypeOfDoublePeiod";
    public static final String PLACE = "PlaceOfDoublePeiod";
    public static final String BEGINNING_TIME = "TimeOfBegginingOfDoublePeiod";
    public static final String ENDING_TIME = "TimeOfEndingOfDoublePeiod";

    public DoublePeriod(String name, String nameOfTeacher, String place, long beginningTime, long endingTime, String type) {
        super();
        put(NAME, name);
        put(TEACHER, nameOfTeacher);
        put(PLACE, place);
        put(BEGINNING_TIME, beginningTime);
        put(ENDING_TIME, endingTime);
        put(TYPE, type);
    }

    public static String timeToString(long time) {
        long hours = time / ((long) 3600000);
        time = time % ((long) 3600000);
        long minutes = time / ((long) 60000);
        Formatter formatter = new Formatter();
        return formatter.format("%02d:%02d",hours,minutes).toString();
    }

    public String beginAndEndTimesToString() {
        StringBuilder sb = new StringBuilder("");

        sb.append(timeToString((long) get(DoublePeriod.BEGINNING_TIME)));
        sb.append(" -\n");
        sb.append(timeToString((long) get(DoublePeriod.ENDING_TIME)));

        return sb.toString();
    }

    public static long getTimeInMillis(int hours, int minutes){
        long res=0;
        res+=hours*(long) 3600000;
        res+=minutes*(long) 60000;
        return res;
    }

}
