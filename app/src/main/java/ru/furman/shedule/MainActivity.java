package ru.furman.shedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.GregorianCalendar;

import ru.furman.shedule.shedule.DoublePeriod;
import ru.furman.shedule.shedule.Shedule;
import ru.furman.shedule.shedule.SheduleDay;
import ru.furman.shedule.shedule.SheduleWeek;

import static ru.furman.shedule.shedule.SheduleWeek.MONDAY;
import static ru.furman.shedule.shedule.SheduleWeek.SUNDAY;
import static ru.furman.shedule.shedule.SheduleWeek.THURSDAY;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Shedule shedule = new Shedule(new GregorianCalendar(2017, 2, 6),"SSAU","6310",
                new SheduleWeek(
                        new SheduleDay(
                                new DoublePeriod("Военная кафедра", "", "", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Метрология", "Гречишников", "3a - 516", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Матлог", "Тишин", "3a - 516", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 05), DoublePeriod.LECTURE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(17, 0), DoublePeriod.getTimeInMillis(18, 35), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("БезОС", "Копенков", "НК - 608", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(10, 35), DoublePeriod.LECTURE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 514", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 7 этаж", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9,50), DoublePeriod.LAB),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 7 этаж", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.LAB),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 431", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 419", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("БезОС", "Веричев", "НК - 611", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.LAB),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 430", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE)
                                ),
                        new SheduleDay(
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(10, 00), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 201", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "НК - 608", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE)
                                ),
                        null
                ),
                new SheduleWeek(
                        new SheduleDay(
                                new DoublePeriod("Военная кафедра", "", "", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Метрология", "Гречишников", "3a - 516", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 608", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE),
                                new DoublePeriod("БезОС", "Копенков", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(17, 0), DoublePeriod.getTimeInMillis(18, 35), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Матлог", "Тишин", "14к - 514", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(10, 35), DoublePeriod.LECTURE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE)
                                ),
                        new SheduleDay(
                                new DoublePeriod("Матлог", "Богданов", "3a - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("Метрология", "Бутько", "3a - 101а", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LAB),
                                new DoublePeriod("Метрология", "Бутько", "3a - 101а", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LAB)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Алгебра", "Додонова", "14к - 421", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.PRACTICE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 215", DoublePeriod.getTimeInMillis(10, 00), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE)
                                ),
                        new SheduleDay(
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "3а - 102(А)", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LAB),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "3а - 102(А)", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LAB)
                        ),
                        null
                ),
                new SheduleWeek(
                        new SheduleDay(
                                new DoublePeriod("Военная кафедра", "", "", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Метрология", "Гречишников", "3a - 516", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Матлог", "Тишин", "3a - 516", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 05), DoublePeriod.LECTURE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(17, 0), DoublePeriod.getTimeInMillis(18, 35), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("БезОС", "Копенков", "НК - 608", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(10, 35), DoublePeriod.LECTURE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 514", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Метрология", "Бутько", "3а - 101а", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LAB),
                                new DoublePeriod("Метрология", "Бутько", "3а - 101а", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.LAB),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 431", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 419", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 7 этаж", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LAB),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 7 этаж", DoublePeriod.getTimeInMillis(10, 00), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.LAB),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 430", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(10, 00), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 201", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "НК - 608", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE)
                        ),
                        null
                ),
                new SheduleWeek(
                        new SheduleDay(
                                new DoublePeriod("Военная кафедра", "", "", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Метрология", "Гречишников", "3a - 516", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE),
                                new DoublePeriod("ТЗИ", "Дворянинов", "НК - 608", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LECTURE),
                                new DoublePeriod("БезОС", "Копенков", "НК - 608", DoublePeriod.getTimeInMillis(15, 15), DoublePeriod.getTimeInMillis(16, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Физкультура", "Решетин", "3к", DoublePeriod.getTimeInMillis(17, 0), DoublePeriod.getTimeInMillis(18, 35), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Матлог", "Тишин", "14к - 514", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.LECTURE),
                                new DoublePeriod("Алгебра", "Додонова", "14к - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(10, 35), DoublePeriod.LECTURE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LECTURE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Матлог", "Богданов", "3a - 514", DoublePeriod.getTimeInMillis(10, 0), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("БезОС", "Веричев", "НК - 611", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LAB),
                                new DoublePeriod("БезОС", "Веричев", "НК - 611", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LAB)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Алгебра", "Додонова", "14к - 421", DoublePeriod.getTimeInMillis(8, 15), DoublePeriod.getTimeInMillis(9, 50), DoublePeriod.PRACTICE),
                                new DoublePeriod("Методы оптимизации", "Ковалёв", "14к - 215", DoublePeriod.getTimeInMillis(10, 00), DoublePeriod.getTimeInMillis(11, 35), DoublePeriod.PRACTICE),
                                new DoublePeriod("Правоведение", "Берёзина", "14к - 514", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.PRACTICE)
                        ),
                        new SheduleDay(
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "3а - 102(А)", DoublePeriod.getTimeInMillis(11, 45), DoublePeriod.getTimeInMillis(13, 20), DoublePeriod.LAB),
                                new DoublePeriod("Сети и СПИ", "Кузнецов", "3а - 102(А)", DoublePeriod.getTimeInMillis(13, 30), DoublePeriod.getTimeInMillis(15, 5), DoublePeriod.LAB)
                        ),
                        null
                )
        );

        shedule.logShedule();

        lv = (ListView) findViewById(R.id.days_lv);
        lv.setAdapter(new DayListAdapter(this, shedule));
    }

}
