package SQLConnection;

import java.sql.Time;

public class Schedule {

    private int id, busNum, startStationLineId, endStationLineId;
    private Time startTime, endTime;

    public Schedule() {
    }

    public Schedule(int busNum, int startStationLineId, int endStationLineId, Time startTime, Time endTime) {
        this.busNum = busNum;
        this.startStationLineId = startStationLineId;
        this.endStationLineId = endStationLineId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule(int id, int busNum, int startStationLineId, int endStationLineId, Time startTime, Time endTime) {
        this.id = id;
        this.busNum = busNum;
        this.startStationLineId = startStationLineId;
        this.endStationLineId = endStationLineId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusNum() {
        return busNum;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }

    public int getStartStationLineId() {
        return startStationLineId;
    }

    public void setStartStationLineId(int startStationLineId) {
        this.startStationLineId = startStationLineId;
    }

    public int getEndStationLineId() {
        return endStationLineId;
    }

    public void setEndStationLineId(int endStationLineId) {
        this.endStationLineId = endStationLineId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", busNum=" + busNum +
                ", startStationLineId=" + startStationLineId +
                ", endStationLineId=" + endStationLineId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


