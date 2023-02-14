package rosterpackage;

/**
 * This class handles the roster array as well as any operations performed on the roster.
 * @author Luca Vespa
 */


public class Roster {

    private static final int NOT_FOUND = -1;

    private Student[] roster;
    private int size;

    // Constructors
    public Roster() {
        size = 0;
        roster = new Student[10];
    }

    // Getters

    /**
     * Gets the student at the given index.
     * @param index the index that has to be checked in the roster array.
     * @return the Student at the given index.
     */
    public Student getStudent(int index) {
        return roster[index];
    }

    /**
     * Gets the size of the roster.
     * @return the number of Students in the roster array.
     */
    public int getSize() {
        return size;
    }

    /**
     * Searches for the given student in roster.
     * @param student the student that is searched for in the roster array.
     * @return the index of the given student, NOT_FOUND (-1) if the student is not in the roster array.
     */
    private int find(Student student) {
        for ( int i = 0; i < roster.length; i++ ) {
            if ( roster[i] != null && roster[i].equals(student) ) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases the roster array capacity by 4.
     */
    private void grow() {
        Student[] tempRoster = new Student[roster.length + 4];
        for ( int i = 0; i < roster.length; i++ ) {
            tempRoster[i] = roster[i];
        }
        roster = tempRoster;
    }

    /**
     * Adds student to end of array.
     * Does nothing if the student is already in the array.
     * @param student the student to be added to the roster array.
     * @return true if the student was not already in the array, false otherwise.
     */
    public boolean add(Student student){
        if ( find(student) != NOT_FOUND ) {
            return false;
        }
        if ( roster[roster.length - 1] != null ) {
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    // Removes a student and maintains the order of the array

    /**
     * Removes a student and maintains the order of the array.
     * Does nothing if the student is not in the array.
     * @param student the student to be removed.
     * @return true if the student was successfully removed, false otherwise.
     */
    public boolean remove(Student student){
        int removeIndex = find(student);
        if ( removeIndex == NOT_FOUND ) {
            return false;
        }
        for ( int i = removeIndex; i < roster.length - 1; i++ ) {
            roster[i] = roster[i + 1];
        }
        roster[roster.length - 1] = null;
        size--;
        return true;
    }

    /**
     * Checks if the student is in roster.
     * @param student the student that is searched for in the roster array.
     * @return true if the student is in the array, false otherwise.
     */
    public boolean contains(Student student){
        return find(student) != -1;
    }

    /**
     * Changes the major of a student.
     * @param student the target student.
     * @return true if the student's major was successfully changed, false otherwise.
     */
    public boolean changeMajor(Student student) {
        int index = find(student);
        if ( index == - 1) {
            return false;
        }
        roster[index] = new Student(roster[index].getProfile(), student.getMajor(), roster[index].getCreditCompleted());
        return true;
    }

    /**
     * Prints roster sorted by profiles.
     */
    public void print () {
        if(roster[0] == null){
            System.out.println("Student roster is empty!");
            return;
        }
        for ( int i = 0; i < size - 1; i++ ) {
            for ( int j = i + 1; j < size; j++ ) {
                if ( roster[i].compareTo(roster[j]) > 0 ) {
                    Student tempStudent = roster[i];
                    roster[i] = roster[j];
                    roster[j] = tempStudent;
                }
            }
        }
        System.out.println("* Student roster sorted by last name, first name, DOB **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    }

    /**
     * Prints roster sorted by school major.
     */
    public void printBySchoolMajor() {
        if(roster[0] == null){
            System.out.println("Student roster is empty!");
            return;
        }
        for ( int i = 0; i < size - 1; i++ ) {
            for ( int j = i + 1; j < size; j++ ) {
                if ( roster[i].getMajor().compareTo(roster[j].getMajor()) > 0 ) {
                    Student tempStudent = roster[i];
                    roster[i] = roster[j];
                    roster[j] = tempStudent;
                }
            }
        }
        System.out.println("* Student roster sorted by school, major **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    }

    /**
     * Prints roster sorted by standing.
     */
    public void printByStanding() {
        if(roster[0] == null){
            System.out.println("Student roster is empty!");
            return;
        }
        for ( int i = 0; i < size - 1; i++ ) {
            for (int j = i + 1; j < size; j++) {
                if ((roster[i].getSeniority().compareTo(roster[j].getSeniority()) > 0)) {
                    Student tempStudent = roster[i];
                    roster[i] = roster[j];
                    roster[j] = tempStudent;
                }
            }
        }
        System.out.println("* Student roster sorted by standing **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    }
}
