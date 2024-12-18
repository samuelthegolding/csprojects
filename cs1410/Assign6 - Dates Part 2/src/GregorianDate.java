public class GregorianDate extends Date {

    GregorianDate() {
        // Following Date Calculation Method
        super(1970, 1, 1);
        // Calculating Date Offset and Adding
        double dateOffset = System.currentTimeMillis();
        double timeZoneOffset = java.util.TimeZone.getDefault().getRawOffset();
        int dayOffset = (int) Math.floor((timeZoneOffset + dateOffset) / 86400000);
        addDays(dayOffset);
    }

    GregorianDate(int year, int month, int day) {
        super(year, month, day);
    }

    @Override
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}