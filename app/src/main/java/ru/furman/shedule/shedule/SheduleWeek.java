package ru.furman.shedule.shedule;

import java.util.HashMap;

/**
 * Created by Furman on 19.03.2017.
 */

public class SheduleWeek extends HashMap<String, SheduleDay> {

    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";


    public SheduleWeek(SheduleDay monday, SheduleDay tuesday, SheduleDay wednesday, SheduleDay thursday, SheduleDay friday, SheduleDay saturday, SheduleDay sunday) {
        super();
        put(MONDAY,monday);
        put(TUESDAY,tuesday);
        put(WEDNESDAY,wednesday);
        put(THURSDAY,thursday);
        put(FRIDAY,friday);
        put(SATURDAY,saturday);
        put(SUNDAY,sunday);
    }

    public static String getDayOfWeekInString(int dayOfWeek) {
        String day = "";
        switch (dayOfWeek) {
            case 1:
                day = SheduleWeek.MONDAY;
                break;
            case 2:
                day = SheduleWeek.TUESDAY;
                break;
            case 3:
                day = SheduleWeek.WEDNESDAY;
                break;
            case 4:
                day = SheduleWeek.THURSDAY;
                break;
            case 5:
                day = SheduleWeek.FRIDAY;
                break;
            case 6:
                day = SheduleWeek.SATURDAY;
                break;
            case 7:
                day = SheduleWeek.SUNDAY;
                break;
        }
        return day;
    }

}
