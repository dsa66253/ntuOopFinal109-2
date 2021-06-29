package BookingSystem;

import java.text.*;
import java.util.*;

import backend.*;

import static java.lang.Math.max;

/**
 * 查詢訂票紀錄、查詢訂票號碼、查詢當日列車、查詢優惠車次
 *
 * @author Lu, Ying-Sheng
 */

public class LookInto {
    TicketManager tm = new TicketManager();
    TimeTable tt = new TimeTable();
    EarlyDiscount ed = new EarlyDiscount();
    UniversityDiscount ud = new UniversityDiscount();

    /**
     * 輸入日期會得到該日的星期英文全名
     *
     * @param date 日期
     * @return 該日的星期英文全名
     * @throws ParseException
     */
    private String DateToDay(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = dateFormat.parse(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return dayFormat.format(temp);
    }

    /**
     * 輸入車站編號會得到該車站中文名稱
     *
     * @param stationID 車站編號
     * @return 該車站中文名稱
     */
    private String StationIDToStationName(String stationID) {
        switch (stationID) {
            case "0990":
                return "南港";
            case "1000":
                return "台北";
            case "1010":
                return "板橋";
            case "1020":
                return "桃園";
            case "1030":
                return "新竹";
            case "1035":
                return "苗栗";
            case "1040":
                return "台中";
            case "1043":
                return "彰化";
            case "1047":
                return "雲林";
            case "1050":
                return "嘉義";
            case "1060":
                return "台南";
            case "1070":
                return "左營";
        }
        return "??";
    }

    /**
     * 查詢訂票紀錄
     *
     * @param code 訂票號碼
     * @param uid 使用者代號
     * @return 訂票紀錄
     * @throws wrongInputException
     */
    public String BookingHistory(String code, String uid) throws wrongInputException {
        ArrayList searchResult = new ArrayList();
        boolean flag = true;
        String result = "";
        for (int i = 0; i < tm.getSize(); i++) {
            if (tm.getTicketObj(i).getCode().equals(code) && tm.getTicketObj(i).getUid().equals(uid)) {
                result = "\nBooking Code: " + tm.getTicketObj(i).getCode();
                result = result + "\nUser ID: " + tm.getTicketObj(i).getUid();
                result = result + "\nDate: " + tm.getTicketObj(i).getTicketInfo(0, "date");
                result = result + "\nTickets Type: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsType");
                result = result + "\nGeneral Tickets: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsCountA");
                result = result + "\nUniversity Tickets: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsCountB");
                result = result + "\nFrom: " + StationIDToStationName(tm.getTicketObj(i).getTicketInfo(0, "start"));
                result = result + "\nTo: " + StationIDToStationName(tm.getTicketObj(i).getTicketInfo(0, "end"));
                result = result + "\nSeats: " + tm.getTicketObj(i).getTicketInfo(0, "seats");
                result = result + "\nPay Deadline: " + tm.getTicketObj(i).getPayDeadLine(0);
                result = result + "\nPayment: " + tm.getTicketObj(i).getPayment(0) + "\n";
                searchResult.add(result);
                flag = false;
                break;
            }
        }
        if (flag) throw new wrongInputException("NO BOOKING HISTORY FOUND");
        return searchResult.toString();
    }

    /**
     * 查詢訂票號碼
     *
     * @param uid 使用者代號
     * @param start 車票起程站
     * @param end 車票到達站
     * @param date 車票日期
     * @param trainNo 列車編號
     * @return 訂票號碼
     * @throws wrongInputException
     */
    public String BookingCode(String uid, String start, String end, String date, String trainNo) throws wrongInputException {
        for (int i = 0; i < tm.getSize(); i++) {
            if (tm.getTicketObj(i).getUid().equals(uid) &&
                    tm.getTicketObj(i).getTicketInfo(0, "start").equals(start) &&
                    tm.getTicketObj(i).getTicketInfo(0, "end").equals(end) &&
                    tm.getTicketObj(i).getTicketInfo(0, "date").equals(date)) {
                return tm.getTicketObj(i).getCode();
            }
        }
        throw new wrongInputException("NO BOOKING CODE FOUND");
    }

    /**
     * 查詢當日列車
     *
     * @param date 日期
     * @return 列車時刻表
     * @throws ParseException
     */
    public String SameDayTrains(String date) throws ParseException {
        LinkedList searchResultDown = new LinkedList();
        LinkedList searchResultUp = new LinkedList();
        for (int i = 0; i < 182; i++) {
            if (tt.get(i, "GeneralTimetable", "ServiceDay", DateToDay(date)).equals("1")) {
                String result = ("\nTrainNo: " + tt.get(i, "GeneralTimetable", "GeneralTrainInfo", "TrainNo") + "  ");
                int k = 0;
                int downOrUp = tt.get(i, "GeneralTimetable", "GeneralTrainInfo", "Direction").equals("0") ? 5 : 35;
                for (int j = 0; j < 12; j++) {
                    String dashOrTime = k == tt.getNumOfStopTimes(i) ?
                            "d" : tt.get(downOrUp, j, "GeneralTimetable", "StopTimes", "StationID").equals(tt.get(i, k, "GeneralTimetable", "StopTimes", "StationID")) ? "t" : "d";
                    switch (dashOrTime) {
                        case "d":
                            dashOrTime = " -----";
                            break;
                        case "t":
                            dashOrTime = " " + tt.get(i, k, "GeneralTimetable", "StopTimes", "DepartureTime");
                            k++;
                    }
                    String nullOrArrow = j == 11 ? "" : " > ";
                    result = result + tt.get(downOrUp, j, "GeneralTimetable", "StopTimes", "StationName", "Zh_tw") + dashOrTime + nullOrArrow;
                }
                if (downOrUp == 5) searchResultDown.add(result);
                else searchResultUp.add(result);
            }
        }
        Comparator<Object> comparator = (o1, o2) -> {
            int largerFromIndex = max(o1.toString().indexOf(":", 19), o2.toString().indexOf(":", 19));
            return o1.toString().substring(largerFromIndex - 2, largerFromIndex + 3).compareTo(o2.toString().substring(largerFromIndex - 2, largerFromIndex + 3));
        };
        Collections.sort(searchResultDown, comparator);
        Collections.sort(searchResultUp, comparator);
        return "\n南下車次" + searchResultDown.toString() + "\n\n北上車次" + searchResultUp.toString();
    }

    /**
     * 查詢優惠車次
     *
     * @param start 起程站
     * @param end 到達站
     * @param date 日期
     * @param time 時間
     * @return 列車時刻表及折扣方案
     * @throws ParseException
     */
    public String DiscountTrains(String start, String end, String date, String time) throws ParseException {
        LinkedList searchResult = new LinkedList();
        for (int i = 0; i < 182; i++) {
            String result = "";
            if (tt.get(i, "GeneralTimetable", "ServiceDay", DateToDay(date)).equals("1")) {
                for (int j = 0; j < tt.getNumOfStopTimes(i) - 1; j++) {
                    if (tt.get(i, j, "GeneralTimetable", "StopTimes", "StationID").equals(start)
                            && tt.get(i, j, "GeneralTimetable", "StopTimes", "DepartureTime").compareTo(time) > 0) {
                        for (int k = j + 1; k < tt.getNumOfStopTimes(i); k++) {
                            if (tt.get(i, k, "GeneralTimetable", "StopTimes", "StationID").equals(end)) {
                                boolean flag = true;
                                for (int l = 0; l < 136; l++) {
                                    if (tt.get(i, "GeneralTimetable", "GeneralTrainInfo", "TrainNo").equals(ed.get(l, "DiscountTrains", "TrainNo"))) {
                                        result = ("\nTrainNo: " + tt.get(i, "GeneralTimetable", "GeneralTrainInfo", "TrainNo") +
                                                "  " + tt.get(i, j, "GeneralTimetable", "StopTimes", "StationName", "Zh_tw") +
                                                " " + tt.get(i, j, "GeneralTimetable", "StopTimes", "DepartureTime") +
                                                " > " + tt.get(i, k, "GeneralTimetable", "StopTimes", "StationName", "Zh_tw") +
                                                " " + tt.get(i, k, "GeneralTimetable", "StopTimes", "DepartureTime") +
                                                "  EarlyDiscount: " + ed.get(l, "DiscountTrains", "ServiceDayDiscount", DateToDay(date)));
                                        flag = false;
                                        break;
                                    }
                                }
                                for (int l = 0; l < 69; l++) {
                                    if (tt.get(i, "GeneralTimetable", "GeneralTrainInfo", "TrainNo").equals(ud.get(l, "DiscountTrains", "TrainNo"))) {
                                        result = result + ("  UniversityDiscount: " + ud.get(l, "DiscountTrains", "ServiceDayDiscount", DateToDay(date)));
                                        break;
                                    }
                                }
                                if (!flag) {
                                    searchResult.add(result);
                                }
                            }
                        }
                    }
                }
            }
        }
        Comparator<Object> comparator = Comparator.comparing(o -> o.toString().substring(19, 24));
        Collections.sort(searchResult, comparator);
        return searchResult.toString();
    }
}