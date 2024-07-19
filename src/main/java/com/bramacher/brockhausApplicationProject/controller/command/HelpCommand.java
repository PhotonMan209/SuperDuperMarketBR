package com.bramacher.brockhausApplicationProject.controller.command;

import com.bramacher.brockhausApplicationProject.model.constants.CommandConstants;

public class HelpCommand implements CommandInterface {
    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        System.out.println("- " + CommandConstants.EXIT + ": Display this help message\"");
        System.out.println("- " + CommandConstants.HELP + ": Displays available commands");
        System.out.println("- " + CommandConstants.DISPLAYPRODUCTS + ": Displays products currently in database\"");
        System.out.println("- " + CommandConstants.DISPLAYPRODUCTSEXPECTED + " X" + ": Displays products with a prediction of X days (Quality degradation)");
    }
}
