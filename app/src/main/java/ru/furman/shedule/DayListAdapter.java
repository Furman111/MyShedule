package ru.furman.shedule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;

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
    private List<SheduleDay> sheduleDayList;
    public final static String LOG_TAG = "DayListAdapterLogs";

    public DayListAdapter(Context ctx, Shedule shedule) {
        super();
        this.context = ctx;
        this.layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shedule = shedule;
        sheduleDayList = new ArrayList<>();
        sortSheduleDayList();
        logSheduleDayList(sheduleDayList);
    }

    @Override
    public int getCount() {
        return sheduleDayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sheduleDayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        SheduleDay sheduleDay = sheduleDayList.get(position);

        if (sheduleDay == null || sheduleDay.isEmpty()) {
            view = layoutInflater.inflate(R.layout.day_off, parent, false);

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            Calendar currentDate = getCurrentDate();
            currentDate.add(Calendar.DAY_OF_YEAR, position);
            dateTv.setText(dayAndMonthToString(currentDate));

            TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
            dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)));
        } else {
            view = layoutInflater.inflate(R.layout.day, parent, false);

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            Calendar currentDate = getCurrentDate();
            currentDate.add(Calendar.DAY_OF_YEAR, position);
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

/*


        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.day, parent, false);
        }

        SheduleDay sheduleDay = sheduleDayList.get(position);


        TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
        Calendar currentDate = getCurrentDate();
        currentDate.add(Calendar.DAY_OF_YEAR, position);
        dateTv.setText(dayAndMonthToString(currentDate));

        TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
        dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)));



        if ((sheduleDay != null) && !sheduleDay.isEmpty()) {

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            Calendar currentDate = getCurrentDate();
            currentDate.add(Calendar.DAY_OF_YEAR, position);
            dateTv.setText(String.valueOf(currentDate.get(Calendar.DAY_OF_MONTH)) + "." + String.valueOf(currentDate.get(Calendar.MONTH)+1));

            TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
            dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)-currentDate.getFirstDayOfWeek()));

            TableLayout tl = (TableLayout) view.findViewById(R.id.double_period_table_layout);

            for (int i = 0; i < sheduleDay.size(); i++) {
                TableRow tableRow = (TableRow) layoutInflater.inflate(R.layout.double_period, tl, false);

                DoublePeriod dp = sheduleDay.get(i);

                TextView timeTv = (TextView) tableRow.findViewById(R.id.time_tv);
                timeTv.setText(dp.beginAndEndTimesToString());

                TextView nameTV = (TextView) tableRow.findViewById(R.id.double_period_name_tv);
                nameTV.setText((String) dp.get(DoublePeriod.NAME));

                TextView placeTV = (TextView) tableRow.findViewById(R.id.place_tv);
                placeTV.setText((String) dp.get(DoublePeriod.PLACE));

                TextView teacherNameTv = (TextView) tableRow.findViewById(R.id.teacher_name_tv);
                teacherNameTv.setText((String) dp.get(DoublePeriod.TEACHER));

                switch ((String) dp.get(DoublePeriod.TYPE)) {
                    case DoublePeriod.LAB:
                        nameTV.setBackgroundColor(tableRow.getResources().getColor(R.color.colorBackgroundLab));
                        break;
                    case DoublePeriod.LECTURE:
                        nameTV.setBackgroundColor(tableRow.getResources().getColor(R.color.colorBackgroundLecture));
                        break;
                    case DoublePeriod.PRACTICE:
                        nameTV.setBackgroundColor(tableRow.getResources().getColor(R.color.colorBackgroundPractice));
                        break;
                    default:
                        nameTV.setBackgroundColor(tableRow.getResources().getColor(android.R.color.transparent));
                }

                tl.addView(tableRow);
            }
        }

        return view;*/
    }

    private void sortSheduleDayList() {
        Calendar currentDate = getCurrentDate();
        Calendar dateOfStudyingStart = shedule.getDateOfStudyingStart();

        if (currentDate.get(Calendar.HOUR_OF_DAY) >= 18)
            currentDate.add(Calendar.DAY_OF_YEAR, 1);

        int weekNumber = currentDate.get(GregorianCalendar.WEEK_OF_YEAR) - dateOfStudyingStart.get(Calendar.WEEK_OF_YEAR) + 1;
        weekNumber = weekNumber % shedule.size();
        if (weekNumber == 0)
            weekNumber = shedule.size();

        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK) - 2;

        sheduleDayList.clear();

        int tmpWeekNumber = weekNumber;

        for (int i = dayOfWeek; i <= 7; i++) {
            sheduleDayList.add(shedule.get(weekNumber - 1).get(SheduleWeek.getDayOfWeekInString(i)));
        }

        tmpWeekNumber += 1;
        tmpWeekNumber %= shedule.size();
        if (tmpWeekNumber == 0)
            tmpWeekNumber = shedule.size();

        while (tmpWeekNumber != weekNumber) {
            for (int i = 1; i <= 7; i++) {
                sheduleDayList.add(shedule.get(tmpWeekNumber - 1).get(SheduleWeek.getDayOfWeekInString(i)));
            }

            tmpWeekNumber += 1;
            tmpWeekNumber %= shedule.size();
            if (tmpWeekNumber == 0)
                tmpWeekNumber = shedule.size();
        }

        for (int i = 1; i < dayOfWeek; i++) {
            sheduleDayList.add(shedule.get(weekNumber - 1).get(SheduleWeek.getDayOfWeekInString(i)));
        }
    }

    @Override
    public void notifyDataSetChanged() {
        sortSheduleDayList();
        super.notifyDataSetChanged();
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

    public static void logSheduleDayList(List<SheduleDay> sheduleDayList) {
        for (SheduleDay sheduleDay : sheduleDayList) {
            if (sheduleDay != null) {
                for (DoublePeriod dp : sheduleDay)
                    Log.d(LOG_TAG, "     para " + dp.get(DoublePeriod.NAME));
            } else
                Log.d(LOG_TAG, "     Day is empty");
            Log.d(LOG_TAG, " -------------------------- ");
        }
    }

    public static Calendar getCurrentDate() {
        Calendar currentDate = new GregorianCalendar();
        if (currentDate.get(Calendar.HOUR_OF_DAY) >= 18)
            currentDate.add(Calendar.DAY_OF_YEAR, 1);
        return currentDate;
    }

    public static String dayAndMonthToString(Calendar date) {
        Formatter formatter = new Formatter();
        return formatter.format("%02d.%02d", date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH) + 1).toString();
    }

}
