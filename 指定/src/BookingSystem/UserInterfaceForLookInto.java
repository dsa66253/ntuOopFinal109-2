package BookingSystem;

import java.text.ParseException;
import java.util.Scanner;

public class UserInterfaceForLookInto {
    public static void main (String[] args) throws ParseException {
        Scanner scanObj = new Scanner(System.in);
        LookInto li = new LookInto();

//        System.out.println("---BookingHistory---");
//        System.out.println("Please enter code: ");
//        String code = scanObj.next();
//        System.out.println("Please enter uid: ");
//        String uid = scanObj.next();
//        System.out.println("code: " + code);
//        System.out.println("uid: " + uid);
//        System.out.println("BookingHistory: " + li.BookingHistory(code, uid));


        System.out.println("---BookingCode---");
        System.out.println("Please enter uid: ");
        String uid = scanObj.next();
        System.out.println("Please enter start: ");
        String start = scanObj.next();
        System.out.println("Please enter end: ");
        String end = scanObj.next();
        System.out.println("Please enter date: ");
        String date = scanObj.next();
        System.out.println("uid: " + uid);
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("date: " + date);
        System.out.println("BookingCode: " + li.BookingCode(uid, start, end, date, null));

//
//        System.out.println("---SameDayTrains---");
//        System.out.println("Please enter date: ");
//        String date = scanObj.next();
//        System.out.println("date: " + date);
//        System.out.println("SameDayTrains: " + li.SameDayTrains(date));

//        System.out.println("---DiscountTrains---");
//        System.out.println("Please enter start: ");
//        String start = scanObj.next();
//        System.out.println("Please enter end: ");
//        String end = scanObj.next();
//        System.out.println("Please enter date: ");
//        String date = scanObj.next();
//        System.out.println("Please enter time: ");
//        String time = scanObj.next();
//        System.out.println("start: " + start);
//        System.out.println("end: " + end);
//        System.out.println("date: " + date);
//        System.out.println("time: " + time);
//        System.out.println("DiscountTrains: " + li.DiscountTrains(start, end, date, time));
    }
}