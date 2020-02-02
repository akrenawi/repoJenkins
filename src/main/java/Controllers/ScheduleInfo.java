package Controllers;

import java.util.Calendar;

public class ScheduleInfo {

    public static final int MAFEEL_INDEX = 1;
    public static final int ESHKOL_NUM_INDEX = 2;
    public static final int ESHKOL_INDEX = 3;
    public static final int SUB_ESHKOL_INDEX = 4;
    public static final int MAHUZ_INDEX = 5;
    public static final int ROUTE_NUM_INDEX = 6;
    public static final int LINE_ID_INDEX = 7;
    public static final int LINE_NUM_INDEX = 8;
    public static final int DIRECTION_INDEX = 9;
    public static final int REPLACEMENT_INDEX = 10;
    public static final int SHELOT_INDEX = 11;
    public static final int DAY_INDEX = 12;
    public static final int EXIT_TIME_INDEX = 13;
    public static final int REPLACEMENT_IDENTITY_INDEX = 14;

    private int LineNum, LineID, Direction, Replacement, EshkulNum, RouteNum, ReplacementIDentity;
    private String Mafeel, Eshkol, SubEshkol, Mahuz, Day, Shelot;
    private Calendar ExitTime;

    public ScheduleInfo(){}

    public ScheduleInfo(int lineNum, int lineID, int direction, int replacement, int eshkulNum, int routeNum, String shelot, int replacementIDentity, String mafeel, String eshkol, String subEshkol, String mahuz, String day, Calendar exitTime) {
        LineNum = lineNum;
        LineID = lineID;
        Direction = direction;
        Replacement = replacement;
        EshkulNum = eshkulNum;
        RouteNum = routeNum;
        Shelot = shelot;
        ReplacementIDentity = replacementIDentity;
        Mafeel = mafeel;
        Eshkol = eshkol;
        SubEshkol = subEshkol;
        Mahuz = mahuz;
        Day = day;
        ExitTime = exitTime;
    }

    public int getEshkulNum() {
        return EshkulNum;
    }

    public void setEshkulNum(int eshkulNum) {
        EshkulNum = eshkulNum;
    }

    public int getRouteNum() {
        return RouteNum;
    }

    public void setRouteNum(int routeNum) {
        RouteNum = routeNum;
    }

    public String getShelot() {
        return Shelot;
    }

    public void setShelot(String shelot) {
        Shelot = shelot;
    }

    public int getReplacementIDentity() {
        return ReplacementIDentity;
    }

    public void setReplacementIDentity(int replacementIDentity) {
        ReplacementIDentity = replacementIDentity;
    }

    public String getMafeel() {
        return Mafeel;
    }

    public void setMafeel(String mafeel) {
        Mafeel = mafeel;
    }

    public String getEshkol() {
        return Eshkol;
    }

    public void setEshkol(String eshkol) {
        Eshkol = eshkol;
    }

    public String getSubEshkol() {
        return SubEshkol;
    }

    public void setSubEshkol(String subEshkol) {
        SubEshkol = subEshkol;
    }

    public String getMahuz() {
        return Mahuz;
    }

    public void setMahuz(String mahuz) {
        Mahuz = mahuz;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public int getLineNum() {
        return LineNum;
    }

    public void setLineNum(int lineNum) {
        LineNum = lineNum;
    }

    public int getLineID() {
        return LineID;
    }

    public void setLineID(int lineID) {
        LineID = lineID;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }

    public int getReplacement() {
        return Replacement;
    }

    public void setReplacement(int replacement) {
        Replacement = replacement;
    }

    public Calendar getExitTime() {
        return ExitTime;
    }

    public void setExitTime(Calendar exitTime) {
        ExitTime = exitTime;
    }

    @Override
    public String toString() {

        String s = LineNum + "\n" + LineID + "\n" + Direction + "\n" + Replacement + "\n" + EshkulNum + "\n" + RouteNum
                + "\n" + Shelot + "\n" + ReplacementIDentity + "\n"+ Mafeel + "\n" + Eshkol
                + "\n" + SubEshkol + "\n" + Mahuz + "\n" + Day + "\n" + ExitTime.getTime() + "\n"
                + "========================================================================\n";

        return s;

//
//        return "" +
//                "LineNum=" + LineNum +
//                ", LineID=" + LineID +
//                ", Direction=" + Direction +
//                ", Replacement=" + Replacement +
//                ", ExitTime=" + ExitTime.getTime();
    }

}
