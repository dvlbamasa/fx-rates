package com.martrust.employee.temporal;

import lombok.RequiredArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 2:03 pm
 */

@RequiredArgsConstructor
public class TemporalActivityImpl implements TemporalActivity {

    private final TemporalService temporalService;
    @Override
    public void activity1() {
        temporalService.call();
        System.out.println("Activity1 is running");
    }
}
