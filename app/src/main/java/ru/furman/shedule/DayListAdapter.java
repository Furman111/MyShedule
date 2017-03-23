package ru.furman.shedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;


import ru.furman.shedule.shedule.DoublePeriod;
import ru.furman.shedule.shedule.Shedule;
import ru.furman.shedule.shedule.SheduleDay;
import ru.furman.shedule.shedule.SheduleWeek;

/**
 * Created by Furman on 17.03.2017.
 */

public class DayListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Shedule shedule;


    public DayListAdapter(Context ctx, Shedule shedule) {
        super();
        this.context = ctx;
        this.layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shedule = shedule;
    }

    @Override
    public int getCount() {
        return 7*shedule.size();
    }

    @Override
    public Object getItem(int position) {

        Calendar currentDate = Calendar.getInstance();
        Calendar dateOfStudyingStart = shedule.getDateOfStudyingStart();

        if (currentDate.get(Calendar.HOUR_OF_DAY) >= 18)
            currentDate.add(Calendar.DAY_OF_YEAR, 1);
        currentDate.add(Calendar.DAY_OF_YEAR, position);


        int weekNumber = currentDate.get(GregorianCalendar.WEEK_OF_YEAR) - dateOfStudyingStart.get(Calendar.WEEK_OF_YEAR) + 1;
        weekNumber = weekNumber % shedule.size();
        if (weekNumber == 0)
            weekNumber = shedule.size();

        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK) - 1;
        return shedule.get(weekNumber - 1).get(SheduleWeek.getDayOfWeekInString(dayOfWeek));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        SheduleDay sheduleDay = (SheduleDay) getItem(position);

        Calendar currentDate = Calendar.getInstance();
        if (currentDate.get(Calendar.HOUR_OF_DAY) >= 18)
            currentDate.add(Calendar.DAY_OF_YEAR, 1);
        currentDate.add(Calendar.DAY_OF_YEAR, position);


        if (sheduleDay == null || sheduleDay.isEmpty() || isDayOff()) {
            view = layoutInflater.inflate(R.layout.day_off, parent, false);

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            dateTv.setText(dayAndMonthToString(currentDate));

            TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
            dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)));
        } else {
            view = layoutInflater.inflate(R.layout.day, parent, false);

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            dateTv.setText(dayAndMonthToString(currentDate));

            TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
            dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)));

            LinearLayout dayLinearLayout = (LinearLayout) view.findViewById(R.id.day_linear_layout);
            dayLinearLayout.removeAllViews();

            for (DoublePeriod dp : sheduleDay) {
                LinearLayout doublePeriodLinearLayout = (LinearLayout) layoutInflater.inflate(R.layout.double_period, dayLinearLayout, false);

                TextView timeTv = (TextView) doublePeriodLinearLayout.findViewById(R.id.time_tv);
                timeTv.setText(dp.beginAndEndTimesToString());

                TextView nameTv = (TextView) doublePeriodLinearLayout.findViewById(R.id.double_period_name_tv);
                nameTv.setText((String) dp.get(DoublePeriod.NAME));

                TextView teacherNameTv = (TextView) doublePeriodLinearLayout.findViewById(R.id.teacher_name_tv);
                teacherNameTv.setText((String) dp.get(DoublePeriod.TEACHER));

                TextView placeTV = (TextView) doublePeriodLinearLayout.findViewById(R.id.place_tv);
                placeTV.setText((String) dp.get(DoublePeriod.PLACE));

                switch ((String) dp.get(DoublePeriod.TYPE)) {
                    case DoublePeriod.LAB:
                        nameTv.setBackgroundColor(doublePeriodLinearLayout.getResources().getColor(R.color.colorBackgroundLab));
                        break;
                    case DoublePeriod.LECTURE:
                        nameTv.setBackgroundColor(doublePeriodLinearLayout.getResources().getColor(R.color.colorBackgroundLecture));
                        break;
                    case DoublePeriod.PRACTICE:
                        nameTv.setBackgroundColor(doublePeriodLinearLayout.getResources().getColor(R.color.colorBackgroundPractice));
                        break;
                    default:
                        nameTv.setBackgroundColor(doublePeriodLinearLayout.getResources().getColor(android.R.color.transparent));
                }

                dayLinearLayout.addView(doublePeriodLinearLayout);
            }
        }

        return view;
    }

    public String getNameOfDayOfTheWeek(int dayOfWeek) {
        String res = "";
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                res = context.getResources().getString(R.string.monday);
                break;
            case Calendar.TUESDAY:
                res = context.getResources().getString(R.string.tuesday);
                break;
            case Calendar.WEDNESDAY:
                res = context.getResources().getString(R.string.wednesday);
                break;
            case Calendar.THURSDAY:
                res = context.getResources().getString(R.string.thursday);
                break;
            case Calendar.FRIDAY:
                res = context.getResources().getString(R.string.friday);
                break;
            case Calendar.SATURDAY:
                res = context.getResources().getString(R.string.saturday);
                break;
            case Calendar.SUNDAY:
                res = context.getResources().getString(R.string.sunday);
                break;
        }
        return res;
    }

    public static String dayAndMonthToString(Calendar date) {
        Formatter formatter = new Formatter();
        return formatter.format("%02d.%02d", date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH) + 1).toString();
    }

    //method that check russian state days off
    private boolean isDayOff(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        switch (month){
            case 1:
                if (day<=8) return true;
            case 2:
                if(day==23) return true;
            case 3:
                if(day==8) return true;
            case 5:
                if((day==1) || (day==9)) return true;
            case 6:
                if(day==12) return true;
            case 11:
                if(day==4) return true;
        }
        return false;
    }

}
