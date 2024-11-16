public class JulianDate extends Date {
    JulianDate() {
        // Following the Method of Calculating Julian Date
        super(1, 1, 1);
        // Calculating Day Offset
        double dateOffset = System.currentTimeMillis();
        double timeZoneOffset = java.util.TimeZone.getDefault().getRawOffset();
        int dayOffset = (int) Math.floor((timeZoneOffset + dateOffset) / 86400000);
        addDays(dayOffset + 719164);
    }

    JulianDate(int year, int month, int day) {
        super(year, month, day);
    }

    @Override
    public boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

}
