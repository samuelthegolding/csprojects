public class GregorianDate {
    int year;
    int month;
    int day;

    GregorianDate() {
        // Following Date Calculation Method
        year = 1970;
        month = 1;
        day = 1;

        // Calculating Date Offset and Adding
        double dateOffset = System.currentTimeMillis();
        double timeZoneOffset = java.util.TimeZone.getDefault().getRawOffset();
        int dayOffset = (int) Math.floor((timeZoneOffset + dateOffset) / 86400000);
        addDays(dayOffset);
    }

    GregorianDate(int year, int month, int day) {
        // Overloaded Constructor
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void addDays(int days) {
        for (int i = 0; i < days; i++) {
            day++;

            // Checking Date Overflow
            if (day > getNumberOfDaysInMonth(year, month)) {
                day = 1;
                month++;

                // Checking Month Overflow
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        }
    }

    public void subtractDays(int days) {
        for (int i = 0; i < days; i++) {
            day--;

            // Check if Day Underflow
            if (day < 1) {
                month --;
                // Check if Year Underflow
                if (month < 1) {
                    month = 12;
                    year--;
                }
                day = getNumberOfDaysInMonth(year, month);
            }
        }
    }

    // Getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return day;
    }

    public String getMonthName() {
        // Mapping Month Names using Switch Expression
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Error";
        };
    }

    public boolean isLeapYear() {
        // Referencing Private Method
        return isLeapYear(year);
    }

    public void printShortDate() {
        System.out.printf("%02d/%02d/%4d", month, day, year);
    }

    public void printLongDate() {
        System.out.printf("%s %2d, %4d", getMonthName(), day, year);
    }

    private int getNumberOfDaysInMonth(int year, int month) {
        // Using Switch to get the Number of Days
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4, 6, 9, 11:
                return 30;
            default:
                return 0;
        }
    }

    private int getNumberOfDaysInYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        } else {
            return 365;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}