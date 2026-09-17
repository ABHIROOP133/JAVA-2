// Q24: Using java.util Package
import java.util.Date;
import java.util.Calendar;

public class JavaUtilDemo {
    public static void main(String[] args) {
        System.out.println("--- Demonstrating java.util Package ---");

        // 1. Demonstrate java.util.Date
        // The Date class represents a specific instant in time, with millisecond precision.
        Date currentDate = new Date();
        System.out.println("\n1. java.util.Date Demonstration:");
        System.out.println("   Current Date and Time: " + currentDate.toString());

        // 2. Demonstrate java.util.Calendar
        // The Calendar class is an abstract class that provides methods for converting 
        // between a specific instant in time and a set of calendar fields such as YEAR, MONTH, etc.
        Calendar calendar = Calendar.getInstance();
        System.out.println("\n2. java.util.Calendar Demonstration:");
        
        int year = calendar.get(Calendar.YEAR);
        // Note: Calendar months are 0-based (January is 0)
        int month = calendar.get(Calendar.MONTH) + 1; 
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        System.out.println("   Extracted fields from Calendar:");
        System.out.printf("   Date (YYYY-MM-DD): %d-%02d-%02d\n", year, month, day);
        System.out.printf("   Time (HH:MM:SS): %02d:%02d:%02d\n", hour, minute, second);
    }
}
