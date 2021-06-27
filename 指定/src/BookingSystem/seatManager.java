package BookingSystem;

import backend.*;

import static java.lang.Integer.*;

public class seatManager {
    Seat[][] seatArray = new Seat[182][];
    TimeTable aTimeTable = new TimeTable();

    public seatManager(){
        int i, j;
        for (i = 0; i < seatArray.length; i++) {
            seatArray[i] = new Seat[aTimeTable.getNumOfStopTimes(i)];
            for (j = 0; j < aTimeTable.getNumOfStopTimes(i); j++) {
                seatArray[i][j] = new Seat(aTimeTable.get(i, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"), aTimeTable.get(i, j, "GeneralTimetable", "StopTimes", "StationID"));
            }
        }
    }
}
