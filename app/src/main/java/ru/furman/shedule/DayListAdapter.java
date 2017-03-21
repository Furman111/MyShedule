package ru.furman.shedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
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

    public DayListAdapter(Context ctx, Shedule shedule) {
        super();
        this.context = ctx;
        this.layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shedule = shedule;
        sheduleDayList = new ArrayList<>();
        sortSheduleDayList();
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

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.day, parent, false);
        }

        SheduleDay sheduleDay = sheduleDayList.get(position);

        if (!(sheduleDay==null) && !sheduleDay.isEmpty()) {

            TextView dateTv = (TextView) view.findViewById(R.id.date_tv);
            Calendar currentDate = new GregorianCalendar();
            currentDate.add(Calendar.DAY_OF_YEAR, position+1);
            dateTv.setText(String.valueOf(currentDate.get(Calendar.DAY_OF_MONTH)) + "." + String.valueOf(currentDate.get(Calendar.MONTH)));

            TextView dayOfWeekTV = (TextView) view.findViewById(R.id.day_of_week_tv);
            dayOfWeekTV.setText(getNameOfDayOfTheWeek(currentDate.get(Calendar.DAY_OF_WEEK)));

            TableLayout tl = (TableLayout) view.findViewById(R.id.double_period_table_layout);

            for (int i = 0; i < sheduleDay.size(); i++) {
                TableRow tableRow = (TableRow) layoutInflater.inflate(R.layout.double_period, tl, false);

                DoublePeriod dp = sheduleDay.get(i);

                TextView timeTv = (TextView) tableRow.findViewById(R.id.time_tv);
                timeTv.setText(dp.beginAndEndTimesToString());

                TextView nameTV = (TextView) tableRow.findViewById(R.id.double_period_name_tv);
                nameTV.setText((String)dp.get(DoublePeriod.NAME));

                TextView placeTV = (TextView) tableRow.findViewById(R.id.place_tv);
                placeTV.setText((String)dp.get(DoublePeriod.PLACE));

                TextView teacherNameTv = (TextView) tableRow.findViewById(R.id.teacher_name_tv);
                teacherNameTv.setText((String)dp.get(DoublePeriod.TEACHER));

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

        return view;
    }

    private void sortSheduleDayList() {
        Calendar currentDate = new GregorianCalendar();
        Calendar dateOfStudyingStart = shedule.getDateOfStudyingStart();

        int weekNumber = currentDate.get(GregorianCalendar.WEEK_OF_YEAR) - dateOfStudyingStart.get(Calendar.WEEK_OF_YEAR) + 1;
        weekNumber = weekNumber % shedule.size();
        if (weekNumber == 0)
            weekNumber = shedule.size();

        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);

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

    public String getNameOfDayOfTheWeek(int day) {
        String res = "";
        switch (day) {
            case 1:
                res = context.getResources().getString(R.string.monday);
                break;
            case 2:
                res = context.getResources().getString(R.string.tuesday);
                break;
            case 3:
                res = context.getResources().getString(R.string.wednesday);
                break;
            case 4:
                res = context.getResources().getString(R.string.thursday);
                break;
            case 5:
                res = context.getResources().getString(R.string.friday);
                break;
            case 6:
                res = context.getResources().getString(R.string.saturday);
                break;
            case 7:
                res = context.getResources().getString(R.string.sunday);
                break;
        }
        return res;
    }


}
