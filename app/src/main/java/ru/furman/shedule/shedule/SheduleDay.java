package ru.furman.shedule.shedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Furman on 17.03.2017.
 */

public class SheduleDay extends ArrayList<DoublePeriod> {

    public SheduleDay(DoublePeriod ... doublePeriods) {
        super();
        for(int i=0;i<doublePeriods.length;i++)
            add(doublePeriods[i]);
    }

}
