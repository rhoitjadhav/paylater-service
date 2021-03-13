package com.paylater;

import com.paylater.controller.MainController;
import com.paylater.utils.ResponseValue;

import java.util.*;

public class App {
    MainController mainController;

    public void run() {
        mainController = new MainController();
        Scanner sc = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print("> ");
            String inputString = sc.nextLine();
            List<String> commands = new ArrayList<>(Arrays.asList(inputString.split("\\s+", 3)));
            int commandsLength = commands.size();
            if (commandsLength == 0) {
                System.out.println("Please enter command");
                continue;
            } else {
                if (commandsLength < 2) {
                    System.out.println("Please enter valid command");
                    continue;
                }

                if (commandsLength == 2) {
                    commands.add("");
                }
            }

            ResponseValue result = mainController.handler(commands);
            System.out.println(result.getMessage());
        }


    }

    public static void main(String[] args) {
        App app = new App();
        app.run();

    }
}
