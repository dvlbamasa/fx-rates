package com.martrust.employee.temporal;

import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 1:13 pm
 */

@WorkflowInterface
public interface TemporalWorkflow {

    String TASK_QUEUE = TemporalWorkflow.class.getSimpleName().concat("_Queue");

    @WorkflowMethod
    void process();

    static WorkflowOptions GetWorkflowOption() {
        return  WorkflowOptions.newBuilder()
                .setWorkflowId(UUID.randomUUID().toString())
                .setTaskQueue(TemporalWorkflow.TASK_QUEUE)
                .build();
    }
}
