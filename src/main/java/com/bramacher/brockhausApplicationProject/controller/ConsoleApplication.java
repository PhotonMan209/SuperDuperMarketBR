package com.bramacher.brockhausApplicationProject.controller;

import com.bramacher.brockhausApplicationProject.controller.command.*;
import com.bramacher.brockhausApplicationProject.model.constants.CommandConstants;
import com.bramacher.brockhausApplicationProject.service.automation.Scheduler;
import com.bramacher.brockhausApplicationProject.service.database.ProductService;
import com.bramacher.brockhausApplicationProject.service.database.QualityDegradationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsoleApplication implements CommandLineRunner {

    //Context and Services to be passed into Command classes
    private final Scheduler scheduler;
    private final ConsoleInputListener consoleInputListener;
    private final ProductService productService;
    private final QualityDegradationService qualityDegradationService;

    private final Map<String, CommandInterface> commandMap = new HashMap<>();

    public ConsoleApplication(Scheduler scheduler, ConsoleInputListener consoleInputListener, ProductService productService, QualityDegradationService qualityDegradationService) {
        this.scheduler = scheduler;
        this.consoleInputListener = consoleInputListener;
        this.productService = productService;
        this.qualityDegradationService = qualityDegradationService;

        // Initialize the command map
        commandMap.put(CommandConstants.EXIT, new ExitCommandInterface());
        commandMap.put(CommandConstants.HELP, new HelpCommandInterface());
        commandMap.put(CommandConstants.DISPLAYPRODUCTS, new DisplayProducts(productService));
        commandMap.put(CommandConstants.DISPLAYPRODUCTSEXPECTED, new DisplayProductsExpected(productService, qualityDegradationService));
    }

    //main Running Application
    @Override
    public void run(String... args) {
        scheduler.performUpdates();
        consoleInputListener.listenToConsoleInput()
                .subscribe(input -> {
                    System.out.println("Received input: " + input); // Info that my command was correctly received

                    String[] inputParts = input.trim().split("\\s+");
                    CommandInterface commandInterface = null;
                    String commandName = null;

                    // Find the longest matching command. Not looking for single words, because a User is more familiar with separated words
                    for (int i = inputParts.length; i > 0; i--) {
                        StringBuilder potentialCommandBuilder = new StringBuilder();
                        for (int j = 0; j < i; j++) {
                            if (j > 0) {
                                potentialCommandBuilder.append(" ");
                            }
                            potentialCommandBuilder.append(inputParts[j].toLowerCase());
                        }
                        String potentialCommand = potentialCommandBuilder.toString();
                        commandInterface = commandMap.get(potentialCommand);
                        if (commandInterface != null) {
                            commandName = potentialCommand;
                            break;
                        }
                    }
                    // Pass command args correctly and send to command class
                    if (commandInterface != null) {
                        int commandNameLength = commandName.split("\\s+").length;
                        String[] commandArgs = new String[inputParts.length - commandNameLength];
                        System.arraycopy(inputParts, commandNameLength, commandArgs, 0, commandArgs.length);
                        System.out.println("Executing command: " + commandName + " with args: " + String.join(" ", commandArgs)); // Debug statement
                        commandInterface.execute(commandArgs);
                    } else {
                        System.out.println("Unknown Command. Type 'help' to show available commands");
                    }
                });
    }
}
