package SQLConnection;

import Controllers.Bus;
import Controllers.ScheduleInfo;
import Controllers.StationInfo;
import Scheduling.SingleRouteInfo;

import javax.persistence.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBConnection {

    private static DBConnection instance = null;
    private Connection connection;
    private boolean isConnected;
    private String failedConnectionResult;

    private static final String END_POINT = "sqldb2019.ckwj7pwgj8n2.us-east-2.rds.amazonaws.com";
    private static final String DB_NAME = "scheduleBus";
    private static final String USERNAME = "sqldb2019";
    private static final String PASSWORD = "sqldb2019";

    private DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + END_POINT +"/" + DB_NAME + "?useUnicode=true&characterEncoding=utf8", USERNAME, PASSWORD);

            isConnected = true;
            failedConnectionResult = "";
        }catch (Exception ex) { isConnected = false; failedConnectionResult = ex.getMessage(); }
    }

    public static DBConnection getInstance(){
        if(instance == null)
            instance = new DBConnection();

        return instance;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getFailedConnectionResult() {
        return failedConnectionResult;
    }

    public boolean add(StationInfo st){

        String query = "INSERT INTO StationInfo Values('" + st.getLineNum() +
                "', '" + st.getLineID() + "', '" + st.getDirection() +
                "', '" + st.getReplacement() + "', '" + st.getStationSerialNum()
                + "', '" + st.getStationSerialID() + "', '" + st.getDistance()
                + "', '" + st.getEshkolNum() + "', '" + st.getShelot()
                + "', '" + st.getNextStationSerialNum() + "', '" + st.getDistanceFromPreviousStation()
                + "','" + st.getCityCode() + "','" + st.getAreaCode()
                + "','" + st.getSharedCode() + "','" + st.getStationName()
                + "', '" + st.getMafeel() +
                "', '" + st.getEshkol() + "', '" + st.getSubEshkol() +
                "', '" + st.getMahuz() + "', '" + st.getUpDown()
                + "', '" + st.getFloor() + "', '" + st.getPlatform()
                + "', '" + st.getCityName() + "', '" + st.getAddress()
                + "', '" + st.getHouseNum() + "', '" + st.getActiveStation()
                + "','" + st.getAreaName() + "','" + st.getSharedDesc()
                + "','" + st.getRouteTime() + "','" + st.getLatitude()
                + "','" + st.getLongitude()
                + "');";

        return executeAddEditOrDeleteQuery(query);
    }

    public boolean add(ScheduleInfo st) {

        String query = "INSERT INTO ScheduleInfo(LineNum, LineID, Direction, Replacement, EshkulNum," +
                " RouteNum, Shelot, ReplacementIDentity, Mafeel, Eshkol, SubEshkol, Mahuz, Day, ExitTime) Values('" + st.getLineNum() +
                "', '" + st.getLineID() + "', '" + st.getDirection() +
                "', '" + st.getReplacement() + "', '" + st.getEshkulNum()
                + "', '" + st.getRouteNum() + "', '" + st.getShelot()
                + "', '" + st.getReplacementIDentity() + "', '" + st.getMafeel()
                + "', '" + st.getEshkol() + "', '" + st.getSubEshkol()
                + "','" + st.getMahuz() + "','" + st.getDay()
                + "','" + new java.sql.Time(st.getExitTime().getTimeInMillis()) + "');";

        return executeAddEditOrDeleteQuery(query);
    }

    public boolean add(Schedule schedule){

        String query = "INSERT INTO Schedule(busNum, startStationLineId, endStationLineId, startTime, endTime) VALUES('" + schedule.getBusNum()
                + "', '" + schedule.getStartStationLineId() + "', '" + schedule.getEndStationLineId()
                + "', '" + schedule.getStartTime() + "', '" + schedule.getEndTime() + "');";

        return executeAddEditOrDeleteQuery(query);
    }

    public boolean add(ScheduleInJSON schedule){
        String query = "INSERT INTO ScheduleFinal VALUES('" + schedule.getKav() + "', '"
                + schedule.getMakat() + "', '" + schedule.getHalofa() + "', '" + schedule.getDirection()
                + "', '" + schedule.getDistanceFromDistinationToOrigin() + "', '" + schedule.getStart() + "', '"
                + schedule.getEnd() + "', '" + schedule.getOrigin() + "', '" + schedule.getDistination()
                + "', '" + schedule.getLangitude() + "', '" + schedule.getLattidude() + "');";

        return executeAddEditOrDeleteQuery(query);
    }

    /**
     * Execute Add, Edit, Delete
     * SQL Queries
     * **/
    private boolean executeAddEditOrDeleteQuery(String query){
        try{

            if(connection == null)
                return false;

            Statement st = connection.createStatement();
            int rs = st.executeUpdate(query);
            return rs > 0;

        }catch (Exception ex){
            System.out.println(ex.getMessage()); return false; }
    }

    /**
     * For GET (SELECT)
     * We Need to Specify for each Table its own GET METHOD
     * **/

    public ArrayList<ScheduleInfo> getAllScheduleInfo(){

        String query = "SELECT * FROM ScheduleInfo;";
        ArrayList<ScheduleInfo> arrayList = new ArrayList<>();

        if(connection == null)
            return arrayList;

        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs != null) {
                while(rs.next()){

                    int lineNum = rs.getInt("LineNum");
                    int lineID = rs.getInt("LineID");
                    int direction = rs.getInt("Direction");
                    int replacement = rs.getInt("Replacement");

                    Calendar exitTime = Calendar.getInstance();
                    exitTime.setTime(new Date(rs.getTimestamp("ExitTime").getTime()));

                    String mafeel = rs.getString("Mafeel");
                    int eshkolNum = rs.getInt("EshkulNum");
                    String eshkol = rs.getString("Eshkol");
                    String subEshkol = rs.getString("SubEshkol");
                    String mahuz = rs.getString("Mahuz");
                    int routeNum = rs.getInt("RouteNum");
                    String shelot = rs.getString("Shelot");
                    String day = rs.getString("Day");
                    int replacementIdentity = rs.getInt("ReplacementIDentity");

                    arrayList.add(new ScheduleInfo(lineNum, lineID, direction, replacement,
                            eshkolNum, routeNum, shelot, replacementIdentity, mafeel, eshkol, subEshkol,
                            mahuz, day ,exitTime));
                }
            }

        }catch (Exception ex){  }

        return arrayList;
    }

    public ArrayList<StationInfo> getAllStationInfo(){
        String query = "SELECT * FROM StationInfo;";
        ArrayList<StationInfo> arrayList = new ArrayList<>();

        if(connection == null)
            return arrayList;

        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs != null) {
                while(rs.next()){

                    int lineNum = rs.getInt("LineNum");
                    int lineID = rs.getInt("LineID");
                    int direction = rs.getInt("Direction");
                    int replacement = rs.getInt("Replacement");
                    int stationSerialNum = rs.getInt("StationSerialNum");
                    int stationSerialID = rs.getInt("StationSerialID");
                    int distance = rs.getInt("Distance");
                    String stationName = rs.getString("StationName");
                    float routeTime = rs.getFloat("RouteTime");
                    float lat = rs.getFloat("Latitude");
                    float longitude = rs.getFloat("Longitude");

                    String mafeel = rs.getString("Mafeel");
                    String eshkol = rs.getString("Eshkol");
                    String subEshkol = rs.getString("SubEshkol");
                    String mahuz = rs.getString("Mahuz");
                    String upDown = rs.getString("UpDown");
                    String floor = rs.getString("Floor");
                    String platform = rs.getString("Platform");
                    String cityName = rs.getString("CityName");
                    String cityAddress = rs.getString("Address");
                    String houseNum = rs.getString("HouseNum");
                    String StationActive = rs.getString("ActiveStation");
                    String areaName = rs.getString("AreaName");
                    String sharedDesc = rs.getString("SharedDesc");

                    int eshkolNum = rs.getInt("EshkolNum");
                    String shelot = rs.getString("Shelot");
                    int nextStation = rs.getInt("NextStationSerialNum");
                    int distaceFromPrev = rs.getInt("DistanceFromPreviousStation");
                    int cityCode = rs.getInt("CityCode");
                    int areaCode = rs.getInt("AreaCode");
                    int sharedCode = rs.getInt("SharedCode");

                    arrayList.add(new StationInfo(lineNum, lineID, direction, replacement,
                            stationSerialNum, stationSerialID, distance, eshkolNum, shelot,
                            nextStation, distaceFromPrev, cityCode, areaCode,
                            sharedCode, stationName, mafeel, eshkol,
                            subEshkol, mahuz, upDown, floor, platform,
                            cityName, cityAddress, houseNum, StationActive, areaName,
                            sharedDesc, routeTime, lat, longitude));

                }
            }

        }catch (Exception ex){  }

        return arrayList;
    }

    public ArrayList<StationInfo> getAllStationInfoByLineID(int givenLineID){
        String query = "SELECT * FROM StationInfo WHERE LineID='" + givenLineID +"';";
        ArrayList<StationInfo> arrayList = new ArrayList<>();

        if(connection == null)
            return arrayList;

        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs != null) {
                while(rs.next()){

                    int lineNum = rs.getInt("LineNum");
                    int lineID = rs.getInt("LineID");
                    int direction = rs.getInt("Direction");
                    int replacement = rs.getInt("Replacement");
                    int stationSerialNum = rs.getInt("StationSerialNum");
                    int stationSerialID = rs.getInt("StationSerialID");
                    int distance = rs.getInt("Distance");
                    String stationName = rs.getString("StationName");
                    float routeTime = rs.getFloat("RouteTime");
                    float lat = rs.getFloat("Latitude");
                    float longitude = rs.getFloat("Longitude");

                    String mafeel = rs.getString("Mafeel");
                    String eshkol = rs.getString("Eshkol");
                    String subEshkol = rs.getString("SubEshkol");
                    String mahuz = rs.getString("Mahuz");
                    String upDown = rs.getString("UpDown");
                    String floor = rs.getString("Floor");
                    String platform = rs.getString("Platform");
                    String cityName = rs.getString("CityName");
                    String cityAddress = rs.getString("Address");
                    String houseNum = rs.getString("HouseNum");
                    String StationActive = rs.getString("ActiveStation");
                    String areaName = rs.getString("AreaName");
                    String sharedDesc = rs.getString("SharedDesc");

                    int eshkolNum = rs.getInt("EshkolNum");
                    String shelot = rs.getString("Shelot");
                    int nextStation = rs.getInt("NextStationSerialNum");
                    int distaceFromPrev = rs.getInt("DistanceFromPreviousStation");
                    int cityCode = rs.getInt("CityCode");
                    int areaCode = rs.getInt("AreaCode");
                    int sharedCode = rs.getInt("SharedCode");

                    arrayList.add(new StationInfo(lineNum, lineID, direction, replacement,
                            stationSerialNum, stationSerialID, distance, eshkolNum, shelot,
                            nextStation, distaceFromPrev, cityCode, areaCode,
                            sharedCode, stationName, mafeel, eshkol,
                            subEshkol, mahuz, upDown, floor, platform,
                            cityName, cityAddress, houseNum, StationActive, areaName,
                            sharedDesc, routeTime, lat, longitude));

                }
            }

        }catch (Exception ex){  }

        return arrayList;
    }

    public ArrayList<Schedule> getAllSchedules(){
        ArrayList<Schedule> arrayList = new ArrayList<>();

        if(connection == null)
            return arrayList;

        try{

            String query = "SELECT * FROM Schedule;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs != null){
                while (rs.next()){

                    int id = rs.getInt("id");
                    int busNum = rs.getInt("busNum");
                    int startStationLineId = rs.getInt("startStationLineId");
                    int endStationLineId = rs.getInt("endStationLineId");
                    Time startTime = rs.getTime("startTime");
                    Time endTime = rs.getTime("endTime");

                    arrayList.add(new Schedule(id, busNum, startStationLineId, endStationLineId, startTime, endTime));
                }
            }

        }catch (Exception ex){}

        return arrayList;
    }

    public ArrayList<ScheduleInJSON> getAllSchedulesInJSONFormat(){
        ArrayList<ScheduleInJSON> arrayList = new ArrayList<>();

        if(connection == null)
            return arrayList;

        try{

            String query = "SELECT * FROM ScheduleFinal;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs != null){
                while (rs.next()){

                    int kav = rs.getInt("kav");
                    int makat = rs.getInt("makat");
                    int halofa = rs.getInt("halofa");
                    int direction = rs.getInt("direction");
                    int distanceFromDistinationToOrigin = rs.getInt("distance");
                    Time start = rs.getTime("start");
                    Time end = rs.getTime("end");
                    String origin = rs.getString("origin");
                    String distination = rs.getString("dist");
                    float langitude = rs.getFloat("lang");
                    float lattidude = rs.getFloat("lat");

                    arrayList.add(new ScheduleInJSON(kav, makat, halofa, direction, distanceFromDistinationToOrigin, start, end
                            , origin, distination, langitude, lattidude));
                }
            }

        }catch (Exception ex){}

        return arrayList;
    }




    /**
     * Hibernate
     * ***/

    //private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BusScheduleWebApp");

    public boolean add_hibernate(StationInfo st){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BusScheduleWebApp");

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;

        try{

            et = em.getTransaction();
            et.begin();
            em.persist(st);
            et.commit();

            return true;
        }catch (Exception ex){
            failedConnectionResult = ex.getMessage();
        }finally {
            em.close();
        }

        return false;
    }


    public StationInfo getSingleStationInfo(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BusScheduleWebApp");

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;

        try{

            String query = "SELECT s FROM StationInfo s WHERE s.LineNum = :lineNum LIMIT 1;";
            TypedQuery<StationInfo> tq  = em.createNamedQuery(query, StationInfo.class);
            tq.setParameter("lineNum", id);
            StationInfo st = null;

            st = tq.getSingleResult();
            return st;
        }catch (Exception ex){
            failedConnectionResult = ex.getMessage();
        }finally {
            em.close();
        }

        return null;
    }

}
