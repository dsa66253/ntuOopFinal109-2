package BookingSystem;

import backend.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void main (String[] args) throws SoldBuySeatException, ParseException, wrongInputException {
        TicketManager aTicketManager = new TicketManager();
        int index = 1;

        Scanner scanObj = new Scanner(System.in);
        generalBooking order = new generalBooking();

        order.setCode(index);
        System.out.println("Round trip or not? (Enter 0 for no, 1 for yes)");
        order.setRoundTrip(scanObj.next());
        if (order.isRoundTrip()) System.out.println("Round trip");
        else System.out.println("One-way ticket");

        System.out.println("Please enter number of general ticket and university discount ticket:");
        System.out.println("General ticket: ");
        order.setGeneralTicket(scanObj.next());
        System.out.println("University discount ticket: ");
        order.setUniversityTicket(scanObj.next());

        System.out.println("Enter 0 for default seats, 1 for window seats, 2 for aisle seats: ");
        order.setPreference(scanObj.nextInt());

        System.out.println("General ticket x" + order.getGeneralTicket() + ", university ticket x" + order.getUniversityTicket() + " in total");

        if (order.isRoundTrip()) {
            System.out.println("Let's set the detail of Departure first: ");
            order.set_toOrFrom(0);
        }

        System.out.println("Please enter user ID: ");
        order.setUid(scanObj.next());
        System.out.println("User ID: " + order.getUid());
        System.out.println("Please enter start station ID: ");
        order.setStartStationID(scanObj.next());
        System.out.println("Start station ID: " + order.getStartStationID());
        System.out.println("Please enter end station ID: ");
        order.setEndStationID(scanObj.next());
        System.out.println("End station ID: " + order.getEndStationID());
        System.out.println("Please enter ticket type (business or standard): ");
        order.setTicketType(scanObj.next());
        System.out.println("Ticket type: " + order.getTicketType());

        System.out.println("Enter \"0\" for search by time, \"1\" for search by train number: ");
        String searchChoice = scanObj.next();
        if (searchChoice.equals("0")) {
            System.out.println("Please enter date in form YYYY-MM-DD: ");
            order.setDate(scanObj.next());
            System.out.println("date: " + order.getDate());
            System.out.println("Enter time with 24-hour clock (Please enter hours only): ");
            order.findValidTrainNo(scanObj.nextInt());
            System.out.println(order.showValidTrainInfo());
            System.out.println("Please enter the index of the train you choose: ");
            order.setValidIndex(scanObj.nextInt());
            order.sellTicket();

            if (order.isRoundTrip()) {
                order.clearListOfValidTrainNo();
                System.out.println("Now search for return ticket: ");

                String tempStartStationID = order.getStartStationID();
                String tempEndStationID = order.getEndStationID();

                order.set_toOrFrom(1);

                order.setStartStationID(tempEndStationID);
                order.setEndStationID(tempStartStationID);

                System.out.println("Start station turns to " + order.getStartStationID());
                System.out.println("End station turns to " + order.getEndStationID());

                System.out.println("Please enter return date in form YYYY-MM-DD: ");
                order.setDate(scanObj.next());
                System.out.println("date: " + order.getDate());
                System.out.println("Enter return time with 24-hour clock (Please enter hours only): ");
                order.findValidTrainNo(scanObj.nextInt());
                System.out.println(order.showValidTrainInfo());
                System.out.println("Please enter the index of the train you choose: ");
                order.setValidIndex(scanObj.nextInt());
                order.sellTicket();
                order.paymentDeadlineCalculator();
            }
            order.paymentDeadlineCalculator();
            order.totalPriceCalculator();

        }
        else if (searchChoice.equals("1")) {
            System.out.println("Please enter date in form YYYY-MM-DD: ");
            order.setDate(scanObj.next());
            System.out.println("Please enter train number: ");
            order.setListOfValidTrainNo2(scanObj.next());
            System.out.println(order.showValidTrainInfo());
            order.setValidIndex(1);
            order.sellTicket();

            if (order.isRoundTrip()) {
                order.clearListOfValidTrainNo();
                System.out.println("Now search for return ticket: ");

                String tempStartStationID = order.getStartStationID();
                String tempEndStationID = order.getEndStationID();

                order.set_toOrFrom(1);

                order.setStartStationID(tempEndStationID);
                order.setEndStationID(tempStartStationID);

                System.out.println("Start station turns to " + order.getStartStationID());
                System.out.println("End station turns to " + order.getEndStationID());

                System.out.println("Please enter train number: ");
                order.setListOfValidTrainNo2(scanObj.next());
                System.out.println(order.showValidTrainInfo());
                order.setValidIndex(1);
                order.sellTicket();
            }
            order.paymentDeadlineCalculator();
            order.totalPriceCalculator();

        }
        else System.out.println("Invalid input!");



        System.out.println("Saving Ticket......");

        aTicketManager.addTicket(order.getaTicket());
        aTicketManager.save("booking01.json");

        System.out.println("Booking information: ");
        String code = aTicketManager.getTicketObj(index + 1).getCode();
        String uid = aTicketManager.getTicketObj(index + 1).getUid();
        String date_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "date");
        String type_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsType");
        String generalTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountA");
        String uniTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountB");
        String start_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "start");
        String end_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "end");
        String seat_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "seats");
        String price = aTicketManager.getTicketObj(index + 1).getPayment();
        String deadline = aTicketManager.getTicketObj(index + 1).getPayDeadLine();

        if (order.isRoundTrip()){
            String date_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "date");
            String type_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsType");
            String generalTicket_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsCountA");
            String uniTicket_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "ticketsCountB");
            String start_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "start");
            String end_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "end");
            String seat_1 = aTicketManager.getTicketObj(index + 1).getTicketInfo(1, "seats");
            String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDeparture ticket details: \n" + "Date: " + date_0 + "\nTicket type: "
                     + type_0 + "\nNumber of general tickets: " + generalTicket_0 + "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
                     + "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nReturn ticket details: \n" + "Date: " + date_1 + "\nTicket type: "
                    + type_1 + "\nNumber of general tickets: " + generalTicket_1 + "\nNumber of university discount tickets: " + uniTicket_1 + "\nStart station: " + start_1
                    + "\nEnd station: " + end_1 + "\nSeat: " + seat_1 + "\nTotal price: " + price + "\nThe payment deadline: " + deadline;
            System.out.println(information);
        }
        else {
            String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDate: " + date_0 + "\nTicket type: "
                    + type_0 + "\nNumber of general tickets: " + generalTicket_0 + "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
                    + "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nTotal price: " + price + "\nThe payment deadline: " + deadline;
            System.out.println(information);
        }
    }
}
