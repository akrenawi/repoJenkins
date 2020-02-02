package Scheduling;

import Controllers.ScheduleInfo;
import Controllers.StationInfo;

import java.util.ArrayList;

public class RouteInfo {

    private StationInfo startStation, endStation;
    private ArrayList<ScheduleInfo> scheduleInfos;

    public RouteInfo(StationInfo startStation, StationInfo endStation){
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public void addScheduleInfo(ScheduleInfo scheduleInfo){
        if(scheduleInfos == null)
            scheduleInfos = new ArrayList<ScheduleInfo>();

        scheduleInfos.add(scheduleInfo);
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

    public ArrayList<ScheduleInfo> getScheduleInfos() {
        return scheduleInfos;
    }

    public void setScheduleInfos(ArrayList<ScheduleInfo> scheduleInfos) {
        this.scheduleInfos = scheduleInfos;
    }

    @Override
    public String toString() {
        String s = "";

        s += "==> Route Information: \n";

        s += "LineNum = " + startStation.getLineNum() + "\n";
        s += "LineID = " + startStation.getLineID() + "\n";
        s += "Direction = " + startStation.getDirection() + "\n";
        s += "Replacement = " + startStation.getReplacement() + "\n";
        s += "Route Distance = " + endStation.getDistance() + "\n";
        s += "Origin Station = " + startStation.getStationName() + "\n";
        s += "Destination Station = " + endStation.getStationName() + "\n";
        s += "Route Times In Minutes: " + endStation.getRouteTime() + "\n";

        s += "Schedule Times: \n\n";

        if(this.getScheduleInfos() != null && this.getScheduleInfos().size() > 0){

            for(ScheduleInfo scheduleInfo : getScheduleInfos()){
                s += scheduleInfo.getExitTime().getTime() + "\n";
            }
        }else{
            s += "No Times Found\n";
        }

        return s;
    }
}
