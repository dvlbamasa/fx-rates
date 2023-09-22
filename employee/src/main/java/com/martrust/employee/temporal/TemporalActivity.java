package com.martrust.employee.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 2:02 pm
 */
@ActivityInterface
public interface TemporalActivity {

    @ActivityMethod
    public void activity1();
}
