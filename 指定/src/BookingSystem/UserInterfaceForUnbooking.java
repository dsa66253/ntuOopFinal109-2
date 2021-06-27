package BookingSystem;

import java.text.ParseException;
import java.util.Scanner;

public class UserInterfaceForUnbooking {
    public static void main (String[] args) throws ParseException {
        Scanner scanObj = new Scanner(System.in);
        Unbooking u = new Unbooking();
        System.out.println("---Unbooking---");
        System.out.println("Please enter code: ");
        String code = scanObj.next();
        System.out.println("Please enter uid: ");
        String uid = scanObj.next();
        System.out.println("code: " + code);
        System.out.println("uid: " + uid);
        System.out.println("Unbooking: ");
        u.Unbooking(code, uid);
    }
}