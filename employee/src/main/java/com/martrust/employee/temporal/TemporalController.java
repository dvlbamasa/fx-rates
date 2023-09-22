package com.martrust.employee.temporal;

import io.temporal.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 2:16 pm
 */

@RestController
@RequiredArgsConstructor
public class TemporalController {

    private final WorkflowClient workflowClient;

    @GetMapping("/test")
    public ResponseEntity<String> invokeWorkFlow() {
        TemporalWorkflow sampleWorkflow = workflowClient.newWorkflowStub(TemporalWorkflow.class, TemporalWorkflow.GetWorkflowOption());
        WorkflowClient.start(sampleWorkflow::process);
        return ResponseEntity.ok("OK");
    }
}
