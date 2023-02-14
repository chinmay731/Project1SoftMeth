package rosterpackage;
import java.util.Calendar;

/**
 * This class creates a properly formatted date and checks whether the date inputted by the user is valid
 * @author Chinmay Rajanahalli, Luca Vespa
 */

public class Date implements Comparable<Date> {

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int MIN_YEAR = 1;
    private static final int MAX_DAYS = 31;
    private static final int MIN_DAYS = 1;
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private static final int DAYS_IN_JANUARY = 31;
    private static final int DAYS_IN_FEBRUARY = 28;
    private static final int DAYS_IN_FEBRUARY_LEAP = 29;
    private static final int DAYS_IN_MARCH = 31;
    private static final int DAYS_IN_APRIL = 30;
    private static final int DAYS_IN_MAY = 31;
    private static final int DAYS_IN_JUNE = 30;
    private static final int DAYS_IN_JULY = 31;
    private static final int DAYS_IN_AUGUST = 31;
    private static final int DAYS_IN_SEPTEMBER = 30;
    private static final int DAYS_IN_OCTOBER = 31;
    private static final int DAYS_IN_NOVEMBER = 30;
    private static final int DAYS_IN_DECEMBER = 31;
    private static final int REQUIRED_AGE = 16;

    private int year;
    private int month;
    private int day;

    // Constructors
    Calendar calendar = Calendar.getInstance();
    public Date() {
        this.year = calendar.get((Calendar.YEAR));
        this.month = calendar.get(Calendar.MONTH) + 1; // months in calendar go from 0(Jan)-11(December)
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date(String date) {
        String[] splitDate = date.split("/");
        this.month = Integer.parseInt(splitDate[0]);
        this.day = Integer.parseInt((splitDate[1]));
        this.year = Integer.parseInt(splitDate[2]);
    }

    // Getters
    public int getYear() {
        return this.year;
    }
    public int getMonth() {
        return this.month;
    }
    public int getDay() {
        return this.day;
    }

    // equals method
    @Override
    public boolean equals (Object object) {
        if (!(object instanceof Date)) {
            return false;
        }
        Date checkDate = (Date) object;
        return ((this.day == checkDate.getDay()) && (this.month == checkDate.getMonth()) && (this.year == checkDate.year));
    }

    // compareTo method
    @Override
    public int compareTo(Date date) {
        if (date.year > this.year) {
            return 1;
        }
        if (date.year < this.year) {
            return -1;
        }
        if (date.month > this.month) {
            return 1;
        }
        if (date.month < this.month) {
            return -1;
        }
        if (date.day > this.day) {
            return 1;
        }
        if (date.day < this.day) {
            return -1;
        }
        return 0;
    }

    // toString method
    @Override
    public String toString() {
        String date = "";
        date = date + Integer.toString(this.month) + "/";
        date = date + Integer.toString(this.day) + "/";
        date = date + Integer.toString(this.year);
        return date;
    }

    // Checks if the assigned year is a leap year
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL != 0) {
                return true; // Leap Year: Divisible by 4 and not 100
            }
            if (year % QUATERCENTENNIAL == 0) {
                return true; // Divisible by 4 and 100 - Leap Year if divisible by 400 not Leap Year otherwise
            }
        }
        return false;
    }

    // Checks if the assigned date is a valid calendar date
    public boolean isValid() {
        if ((month < JANUARY || month > DECEMBER) || (day > MAX_DAYS || day < MIN_DAYS) || (year > calendar.get(Calendar.YEAR) || year < MIN_YEAR)) {
            return false;
        }
        if (month == 1) return (day <= DAYS_IN_JANUARY);
        if (month == 2) { // have to check for leap year
            if (isLeapYear()) {
                return (day <= DAYS_IN_FEBRUARY_LEAP);
            } else {
                return (day <= DAYS_IN_FEBRUARY);
            }
        }
        if (month == 3) return (day <= DAYS_IN_MARCH);
        if (month == 4) return (day <= DAYS_IN_APRIL);
        if (month == 5) return (day <= DAYS_IN_MAY);
        if (month == 6) return (day <= DAYS_IN_JUNE);
        if (month == 7) return (day <= DAYS_IN_JULY);
        if (month == 8) return (day <= DAYS_IN_AUGUST);
        if (month == 9) return (day <= DAYS_IN_SEPTEMBER);
        if (month == 10) return (day <= DAYS_IN_OCTOBER);
        if (month == 11) return (day <= DAYS_IN_NOVEMBER);
        if (month == 12) return (day <= DAYS_IN_DECEMBER);
        return true;
    }

    // Checks if the given date is at least 16 years from the current date
    public boolean isOldEnough () {
        Date today = new Date();
        if(today.getYear() - this.getYear() > REQUIRED_AGE) {
            return true;
        }else if(today.getYear() - this.getYear() == REQUIRED_AGE) {
            if(today.getMonth() > this.getMonth()) {
                return true;
            }else if(today.getMonth() == this.getMonth() && today.getDay() >= this.getDay()) {
                return true;
            }
        }
        return false;
    }

    // Testbed main() for isValid() method in Date class
    public static void main(String[] args) {
        // test case 1
        Date d1 = new Date("2/29/2003");
        System.out.println(d1.isValid());

        // test case 2
        Date d2 = new Date("4/31/2003");
        System.out.println(d2.isValid());

        // test case 3
        Date d3 = new Date("13/31/2003");
        System.out.println(d3.isValid());

        // test case 4
        Date d4 = new Date("3/32/2003");
        System.out.println(d4.isValid());

        // test case 5
        Date d5 = new Date("-1/31/2003");
        System.out.println(d5.isValid());

        // test case 6
        Date d6 = new Date("2/29/2004");
        System.out.println(d6.isValid());

        // test case 7
        Date d7 = new Date("12/31/2003");
        System.out.println(d7.isValid());
    }
}
