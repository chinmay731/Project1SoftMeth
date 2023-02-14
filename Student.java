package rosterpackage;

/**
 * This class represents a Student which includes their profile, major, and the number of credits they have completed.
 * @author Chinmay Rajanahalli
 */

public class Student implements Comparable<Student> {

    private static final int CREDITS_FOR_SOPHOMORE = 30;
    private static final int CREDITS_FOR_JUNIOR = 60;
    private static final int CREDITS_FOR_SENIOR = 90;

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     * Constructor for when all three parameters are provided.
     * @param profile the profile of the student which includes first name, last name, and date of birth.
     * @param major the major of the student including the major code and the school name.
     * @param creditCompleted the number of credits completed by the student.
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Constructor for when only the number of credits is not provided.
     * @param profile the profile of the student which includes first name, last name, and date of birth.
     * @param major the major of the student including the major code and the school name.
     */
    public Student(Profile profile, Major major) {
        this.profile = profile;
        this.major = major;
    }

    /**
     * Constructor for when only the profile is provided.
     * @param profile the profile of the student which includes first name, last name, and date of birth.
     */
    public Student (Profile profile) {
        this.profile = profile;
        this.major = null;
    }

    /**
     * Constructor for when nothing is provided.
     */
    public Student () {
        this.profile = null;
        this.major = null;
    }

    /**
     * Gets the profile associated with the student.
     * @return this student's profile.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the major associated with the student.
     * @return this student's major.
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Gets the number of credits associated with the student.
     * @return the number of credits completed by this student.
     */
    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    /**
     * Gets the seniority of the student based on the number of credits they have completed.
     * @return a String that represents the student's seniority.
     */
    public String getSeniority() {
        if (creditCompleted < CREDITS_FOR_SOPHOMORE) {
            return "Freshman";
        } else if (creditCompleted < CREDITS_FOR_JUNIOR) {
            return "Sophomore";
        } else if (creditCompleted < CREDITS_FOR_SENIOR) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    /**
     * An override of the compareTo method.
     * @param student the object to be compared.
     * @return 0 if the students are equal, 1 if this student is lexicographically greater than the student given as a parameter, -1 otherwise.
     */
    @Override
    public int compareTo(Student student) {
        return this.getProfile().compareTo(student.profile);
    }

    /**
     * An override of the equals method.
     * @param object of type Student.
     * @return returns true if the student's profiles are the same, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student)) {
            return false;
        }
        Student checkStudent = (Student) object;
        return (this.profile.equals(checkStudent.profile));
    }

    /**
     * An override of the toString method.
     * @return a String representation of this student.
     */
    @Override
    public String toString() {
        return profile.toString() + " (" + major.getMajorCode() + " " + major.getMajorName() + " " + major.getSchoolNames() + ") " +
                "credits completed: " + getCreditCompleted() + " (" + getSeniority() + ")";
    }

    public static void main(String[] args) {
        // test case 1
        Date d1 = new Date("07/31/2002");
        Profile p1 = new Profile("Rajanahalli", "Chinmay", d1);
        Major major =  Major.CS;
        Student s1 = new Student(p1,major,95);

        Date d2 = new Date("07/31/2002");
        Profile p2 = new Profile("Rajanahalli", "Chinmay", d2);
        Major major2 =  Major.CS;
        Student s2 = new Student(p2,major2,20);

        System.out.println(s1.compareTo(s2));

        // test case 2
        Date d3 = new Date("07/31/2002");
        Profile p3 = new Profile("Rajanahalli", "Chinmay", d3);
        Major major3 =  Major.CS;
        Student s3 = new Student(p3,major3,95);

        Date d4 = new Date("07/31/2002");
        Profile p4 = new Profile("Rajanahalli", "Chinmay", d4);
        Major major4 =  Major.MATH;
        Student s4 = new Student(p4,major4,120);

        System.out.println(s3.compareTo(s4));

        // test case 3
        Date d5 = new Date("07/31/2002");
        Profile p5 = new Profile("Rajanahalli", "Vihan", d5);
        Major major5 =  Major.CS;
        Student s5 = new Student(p5,major5,120);

        Date d6 = new Date("07/31/2002");
        Profile p6 = new Profile("Rajanahalli", "Chinmay", d6);
        Major major6 =  Major.CS;
        Student s6 = new Student(p6,major6,120);

        System.out.println(s5.compareTo(s6));

        // test case 4
        Date d7 = new Date("07/31/2002");
        Profile p7 = new Profile("Rajanahalli", "Chinmay", d7);
        Major major7 =  Major.CS;
        Student s7 = new Student(p7,major7,120);

        Date d8 = new Date("07/31/1996");
        Profile p8 = new Profile("Rajanahalli", "Chinmay", d8);
        Major major8 =  Major.MATH;
        Student s8 = new Student(p8,major8,120);

        System.out.println(s7.compareTo(s8));

        // test case 5
        Date d9 = new Date("07/31/1996");
        Profile p9 = new Profile("Rajanahalli", "Chinmay", d9);
        Major major9 =  Major.CS;
        Student s9 = new Student(p9,major9,120);

        Date d10 = new Date("07/31/2002");
        Profile p10 = new Profile("Rajanahalli", "Chinmay", d10);
        Major major10 =  Major.MATH;
        Student s10 = new Student(p10,major10,120);

        System.out.println(s9.compareTo(s10));

        // test case 6
        Date d11 = new Date("07/31/1996");
        Profile p11 = new Profile("Rajanaballi", "Chinmay", d11);
        Major major11 =  Major.CS;
        Student s11 = new Student(p11,major11,120);

        Date d12 = new Date("07/31/2002");
        Profile p12 = new Profile("Rajanahalli", "Chinmay", d12);
        Major major12 =  Major.MATH;
        Student s12 = new Student(p12,major12,120);

        System.out.println(s11.compareTo(s12));



        


    }

}
