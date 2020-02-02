package Scheduling;

import Controllers.StationInfo;

import java.util.Calendar;

public class SingleRouteInfo {

    private StationInfo startStation, endStation;
    private Calendar startTime, endTime;


    public SingleRouteInfo(StationInfo startStation, StationInfo endStation, Calendar startTime, Calendar endTime){
        this.startStation = startStation;
        this.endStation = endStation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SingleRouteInfo(SingleRouteInfo other){
        this(other.getStartStation(), other.getEndStation(), other.getStartTime(), other.getEndTime());
    }

    public StationInfo getStartStation() {
        return startStation;
    }

    public void setStartStation(StationInfo startStation) {
        this.startStation = startStation;
    }

    public StationInfo getEndStation() {
        return endStation;
    }

    public void setEndStation(StationInfo endStation) {
        this.endStation = endStation;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String s = "";

        s += "LineNum: " + startStation.getLineNum()
//                + ", LineID: " + startStation.getLineID()
//                + ", Direction: " + startStation.getDirection()
//                + ", Replacement: " + startStation.getReplacement()
                + ", Distance: " + endStation.getDistance()
                + ", Origin Station: " + startStation.getStationName()
                + ", Destination Station: " + endStation.getStationName()
                + ", Route Times In Minutes: " + endStation.getRouteTime()
                + ", Start At: " + startTime.getTime()
                + ", End At: " + endTime.getTime()
                + ", Origin Serial ID: " + startStation.getStationSerialID()
                + ", Destination Serial ID: " + endStation.getStationSerialID()
                + "\n";
//
//        s += "==> Route Information: \n";
//
//        s += "LineNum = " + startStation.getLineNum() + "\n";
//        s += "LineID = " + startStation.getLineID() + "\n";
//        s += "Direction = " + startStation.getDirection() + "\n";
//        s += "Replacement = " + startStation.getReplacement() + "\n";
//        s += "Route Distance = " + endStation.getDistance() + "\n";
//        s += "Origin Station = " + startStation.getStationName() + "\n";
//        s += "Destination Station = " + endStation.getStationName() + "\n";
//        s += "Route Times In Minutes: " + endStation.getRouteTime() + "\n";
//        s += "Starts At: " + startTime.getTime() + "\n";
//        s += "Ends At: " + endTime.getTime() + "\n";

        return s;
    }

}
