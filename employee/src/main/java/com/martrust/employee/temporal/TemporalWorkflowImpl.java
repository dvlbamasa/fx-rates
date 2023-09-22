package com.martrust.employee.temporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 1:22 pm
 */

@RequiredArgsConstructor
public class TemporalWorkflowImpl implements TemporalWorkflow{

    private final TemporalActivity temporalActivity = Workflow.newActivityStub(
            TemporalActivity.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(2)).build());

    @Override
    public void process() {
        this.temporalActivity.activity1();
        System.out.println("process running on workflow");
    }
}
