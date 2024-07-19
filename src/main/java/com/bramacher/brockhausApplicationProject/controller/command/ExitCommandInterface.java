package com.bramacher.brockhausApplicationProject.controller.command;

public class ExitCommandInterface implements CommandInterface {
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}
