package rosterpackage;
import java.util.Scanner;

/**
 * This class handles the inputs and outputs of the entire program
 * @author Luca Vespa
 */

public class RosterManager {

    private Roster roster = new Roster();
    private Scanner input = new Scanner(System.in);

    /**
     * Creates an instance of student.
     * @param components the Strings values needed to create the Student.
     * @return the created Student.
     */
    private Student createStudent(String [] components) {
        Date newDate = new Date(components[3]);
        Profile newProfile = new Profile(components[2], components[1], newDate);
        if (components.length == 5) {
            Major newMajor = Major.valueOf(components[4].toUpperCase());
            return new Student(newProfile, newMajor);
        }
        if (components.length == 6) {
            Major newMajor = Major.valueOf(components[4].toUpperCase());
            return new Student(newProfile, newMajor, Integer.parseInt(components[5]));
        }
        return new Student(newProfile);
    }

    /**
     * Check if the inputted Date is valid and print the according error messages if not.
     * @param student the student whose date of birth will be checked.
     * @return true if the date of birth is valid, false otherwise.
     */
    private boolean checkValidDate(Student student) {
        if(!student.getProfile().getDob().isValid()) {
            System.out.println("DOB invalid: " + student.getProfile().getDob().toString() + " not a valid calendar date!");
            return false;
        }
        if(!student.getProfile().getDob().isOldEnough()) {
            System.out.println("DOB invalid: " + student.getProfile().getDob().toString() + " younger than 16 years old.");
            return false;
        }
        return true;
    }

    /**
     * Check if the inputted Major is valid.
     * @param str the string input for the major.
     * @return true if the major is a valid major, false otherwise.
     */
    private boolean checkIfValidMajor (String str ) {
        return (str.equalsIgnoreCase("CS") || str.equalsIgnoreCase("BAIT") || str.equalsIgnoreCase("MATH") || str.equalsIgnoreCase("ITI") || str.equalsIgnoreCase("EE"));
    }

    /**
     * Check if the inputted School is valid.
     * @param str the string input for the school.
     * @return true if the major is a valid major, false otherwise.
     */
    private boolean checkIfValidSchool (String str) {
        return (str.equalsIgnoreCase("RBS") || str.equalsIgnoreCase("SAS") || str.equalsIgnoreCase("SC&I") || str.equalsIgnoreCase("SOE"));
    }

    /**
     * Check if the inputted Number of Credits is valid and print the according error messages if not.
     * @param str the string input for the number of credits.
     * @return true if the number of credits is valid, false otherwise.
     */
    private boolean checkIfValidCredits (String str) {
        try {
            int test = Integer.parseInt(str);
            if (test < 0) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return false;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        }
        return true;
    }

    /**
     * Performs the necessary checks before adding a student to the roster and prints the corresponding message.
     * @param inputs the Strings values needed to create the Student.
     */
    private void addStudent (String[] inputs) {
        Student student = createStudent(inputs);
        if (!checkValidDate(student)) {
            return;
        }
        if (roster.contains(student)) {
            System.out.println(student.getProfile().toString() + " is already in the roster.");
            return;
        }
        roster.add(student);
        System.out.println(student.getProfile().toString() + " added to the roster.");
    }

    /**
     * Executes the code associated with the command "L".
     * @param components
     */
    private void executeCommandL(String [] components) {
        if (!checkIfValidSchool(components[1])) {
            System.out.println("School doesn't exist: " + components[1]);
            return;
        }
        System.out.println("* Students in " + components[1] + " *");
        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getStudent(i).getMajor().getSchoolNames().equalsIgnoreCase(components[1])) {
                System.out.println(roster.getStudent(i).toString());
            }
        }
        System.out.println("* end of list **");
    }

    /**
     * Executes the code associated with the command "A".
     * @param commands the Strings values needed to create the Student.
     */
    private void executeCommandA(String [] commands) {
        if (checkIfValidMajor(commands[4]) && checkIfValidCredits(commands[5])) {
            addStudent(commands);
        }else if (!checkIfValidMajor(commands[4])) {
            System.out.println("Major code invalid: " + commands[4]);
        }
    }

    /**
     * Executes the code associated with the command "R".
     * @param commands the Strings values needed to create the Student.
     */
    private void executeCommandR(String [] commands) {
        Student studentToRemove = createStudent(commands);
        if (roster.contains(studentToRemove)) {
            roster.remove(studentToRemove);
            System.out.println(studentToRemove.getProfile().toString() + " removed from the roster.");
        } else {
            System.out.println(studentToRemove.getProfile().toString() + " is not in the roster.");
        }
    }

    /**
     * Executes the code associated with the command "C".
     * @param commands the Strings values needed to create the Student.
     */
    private void executeCommandC(String [] commands) {
        if (checkIfValidMajor(commands[4])) {
            Student studentToChange = createStudent(commands);
            if (roster.contains(studentToChange)) {
                roster.changeMajor(createStudent(commands));
                System.out.println(studentToChange.getProfile().toString() + " major changed to " + commands[4]);
            } else {
                System.out.println(studentToChange.getProfile().toString() + " is not in the roster.");
            }
        }else {
            System.out.println("Major code invalid: " + commands[4]);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        System.out.println("Roster Manager running...\n");
        while ( true ) {
            String commandsString = input.nextLine();
            String[] commands = commandsString.split("\\s+");
            if (commandsString.length() == 0) {
                //Do not do anything if the input line is blank
            }else if (commands[0].equals("Q")) {
                System.out.println("Roster Manager terminated.");
                break;
            } else if (commands[0].equals("A") && commands.length == 6) {
                executeCommandA(commands);
            } else if (commands[0].equals("R") && commands.length == 4) {
                executeCommandR(commands);
            } else if (commands[0].equals("P")) {
                roster.print();
            } else if (commands[0].equals("PS")) {
                roster.printByStanding();
            } else if (commands[0].equals("PC")) {
                roster.printBySchoolMajor();
            } else if (commands[0].equals("C") && commands.length == 5) {
                executeCommandC(commands);
            } else if (commands[0].equals("L") && commands.length == 2) {
                executeCommandL(commands);
            } else{
                System.out.println(commandsString + " is an invalid command!");
            }
        }
    }
}