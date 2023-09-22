package com.martrust.employee.temporal;

import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 21/09/2023
 * Time: 2:03 pm
 */

@Service
public class TemporalService {
    public void call() {
        System.out.println("Service was called");
    }
}
