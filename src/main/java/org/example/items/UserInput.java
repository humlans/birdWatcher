package org.example.items;

import java.io.IOException;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner = new Scanner(System.in);

    public String getCanBeBlankStringInput() {
        return scanner.nextLine();
    }

    public String getCanNotBeBlankStringInput() {
        while (true) {
            String input = scanner.nextLine();
            if (!input.isBlank()){
                return input;
            }
            System.out.println("You have to assign this attribute a value!");
        }
    }

    public int getIntInput() {
        int intInput = Integer.MIN_VALUE;
        String input = scanner.nextLine();

        if (input.isBlank()) {
            return intInput;
        }

        while(true) {
            try {
                intInput = Integer.parseInt(input);
                return intInput;
            } catch (Exception e) {
                System.out.println("Write a number");
                input = scanner.nextLine();
            }
        }
    }

    public int getIntInputOrQuit() {
        int intInput = Integer.MIN_VALUE;
        String input = scanner.nextLine();

        while(true) {
            if (input.equals("Quit")) {
                return intInput;
            }

            try {
                intInput = Integer.parseInt(input);
                return intInput;
            } catch (Exception e) {
                System.out.println("Write a number or \"Quit\"");
                input = scanner.nextLine();
            }
        }
    }

    public int getIntInputInInterval(int max) {
        int intInput = Integer.MIN_VALUE;
        String input = scanner.nextLine();

        while(true) {
            try {
                intInput = Integer.parseInt(input);
                if(intInput < max && intInput > -1) {
                    return intInput;

                }
                else {
                    System.out.println("Write a valid number");
                    input = scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Write a valid number");
                input = scanner.nextLine();
            }
        }
    }
}
