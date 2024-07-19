package com.bramacher.brockhausApplicationProject.controller.command;

public class ExitCommand implements CommandInterface {
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}
