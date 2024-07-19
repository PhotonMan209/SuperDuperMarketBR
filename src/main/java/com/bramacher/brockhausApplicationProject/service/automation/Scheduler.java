package com.bramacher.brockhausApplicationProject.service.automation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    @Async("taskExecutor")
    public void performUpdates() {
        System.out.println("Performing automatic updates (placeholder)");

        // in an application that is continuously running, here I would implement a daily scheduler.
        // this would pull products, and apply the qualityDegradationRule for each to have updated values
        //suggested time would be local time 03:00:00

        System.out.println("Enter commands like 'help' or 'exit' to quit.");
    }
}
