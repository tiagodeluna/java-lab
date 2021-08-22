package javaio;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class InputAndOutputStreams {

    public static void main(String[] args) throws FileNotFoundException {
        readingUserInputsWithInputStreamAndConsole();
        quickIOTipsAndTricks();
    }

    private static void readingUserInputsWithInputStreamAndConsole() {
        System.out.println("FEEDBACK COLLECTOR");
        System.out.print("Please type something (using System.in): ");
        var reader = new BufferedReader(new InputStreamReader(System.in));
        try(reader) {
            String userInput = reader.readLine();
            System.out.println();
            System.out.println("You entered: " + userInput);
        } catch (IOException e) {
            //Handles IOException
        }

        try {
            // This line will throw IOException since the System.in was closed in the previous block
            reader.readLine();
        } catch (IOException e) {
            System.err.println("IOException was thrown!");
        }

        // Console is only provided as a Singleton via System.console().
        Console console = System.console();
        if (console != null) {
            char[] userInput = console.readPassword("Now enter a password (using Console): ");
            System.out.println();
            System.out.println("Your password is: " + new String(userInput));
        } else {
            System.err.println("Console is not available! =(");
        }
    }

    private static void quickIOTipsAndTricks() {
        System.out.println();
        System.out.println("IO QUICK TIPS AND TRICKS");
        System.out.println("Retrieving file separator...");
        System.out.println("> System.getProperty(\"file.separator\") = " + System.getProperty("file.separator"));
        System.out.println("> java.io.File.separator = " + File.separator);

        System.out.println("\nRetrieving line break or line separator...");
        System.out.println("> System.getProperty(\"line.separator\") = " + System.getProperty("line.separator"));
        System.out.println("> System.lineSeparator() = " + System.lineSeparator());

        // System.out is a PrintStream, which extends FilterOutputStream
        System.out.println("System.out is an instance of PrintStream:");
        System.out.println("> System.out instanceof PrintStream -> " + (System.out instanceof PrintStream));

        System.out.println("\nSystem.out has a built-in format() method (because it is a PrintStream!):");
        System.out.print("> System.out.format(\"Printing %s & %.2f!%n\", \"hey\", 3.141592) = ");
        System.out.format("Printing %s & %.2f!%n", "hey", 3.141592);
    }
}
