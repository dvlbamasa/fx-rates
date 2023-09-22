package com.martrust.employee.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 20/09/2023
 * Time: 6:14 pm
 */
@Configuration
public class TemporalConfig {

    private final static String TARGET_ENDPOINT = "127.0.0.1:7233";

    private final static String NAMESPACE = "test-namespace";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions
                .newBuilder()
                .setTarget(TARGET_ENDPOINT)
                .build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder().setNamespace(NAMESPACE).build());
    }

    @Bean(name="createWorkerFactory")
    public WorkerFactory createWorkerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean(name="createWorker")
    @DependsOn("createWorkerFactory")
    public Worker createWorker(WorkerFactory workerFactory, TemporalService temporalService){
        Worker worker = workerFactory.newWorker(TemporalWorkflow.TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(TemporalWorkflowImpl.class);
        // need the instance not the class, so we pass it as argument on bean method
        worker.registerActivitiesImplementations(new TemporalActivityImpl(temporalService));
        workerFactory.start();
        return worker;
    }
}
