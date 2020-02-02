package Controllers;

public class StationInfo {


    public static final int MAFEEL_INDEX = 1;
    public static final int ESHKOL_NUM_INDEX = 2;
    public static final int ESHKOL_INDEX = 3;
    public static final int SUB_ESHKOL_INDEX = 4;
    public static final int MAHUZ_INDEX = 5;
    public static final int LINE_ID_INDEX = 6;
    public static final int LINE_NUM_INDEX = 7;
    public static final int DIRECTION_INDEX = 8;
    public static final int REPLACEMENT_INDEX = 9;
    public static final int SHELOT_INDEX = 10;
    public static final int STATION_SERIAL_INDEX = 11;
    public static final int STATION_ID_INDEX = 12;
    public static final int STATION_NAME_INDEX = 13;
    public static final int UP_DOWN_PEOPLE_INDEX = 14;
    public static final int NEXT_STATION_SERIAL_NUMBER_INDEX = 15;
    public static final int DISTANCE_FROM_PREVIOUS_STATION_INDEX = 16;
    public static final int DISTANCE_FROM_ORIGIN_INDEX = 17;
    public static final int ROUTE_TIME_INDEX = 18;
    public static final int FLOOR_INDEX = 19;
    public static final int PLATFORM_INDEX = 20;
    public static final int LAT_INDEX = 21;
    public static final int LONG_INDEX = 22;
    public static final int CITY_CODE_INDEX = 23;
    public static final int CITY_NAME_INDEX = 24;
    public static final int ADDRESS_INDEX = 25;
    public static final int HOUSE_NUM_INDEX = 26;
    public static final int STATION_ACTIVE_INDEX = 27;
    public static final int AREA_CODE_INDEX = 28;
    public static final int AREA_NAME_INDEX = 29;
    public static final int SHARED_CODE_INDEX = 30;
    public static final int SHARED_CODE_DESC_INDEX = 31;

    private int LineNum, LineID, Direction, Replacement, StationSerialNum, StationSerialID, Distance, EshkolNum;
    private int NextStationSerialNum, DistanceFromPreviousStation, CityCode, AreaCode, SharedCode;
    private String StationName, Mafeel, Eshkol, SubEshkol, Mahuz, UpDown, Floor, Platform, CityName, Address;
    private String HouseNum, ActiveStation, AreaName, SharedDesc, Shelot;
    private float RouteTime, Latitude, Longitude;

    public StationInfo(){}

    public StationInfo(int lineNum, int lineID, int direction, int replacement, int stationSerialNum, int stationSerialID, int distance, int eshkolNum, String shelot, int nextStationSerialNum, int distanceFromPreviousStation, int cityCode, int areaCode, int sharedCode, String stationName, String mafeel, String eshkol, String subEshkol, String mahuz, String upDown, String floor, String platform, String cityName, String address, String houseNum, String activeStation, String areaName, String sharedDesc, float routeTime, float latitude, float longitude) {
        LineNum = lineNum;
        LineID = lineID;
        Direction = direction;
        Replacement = replacement;
        StationSerialNum = stationSerialNum;
        StationSerialID = stationSerialID;
        Distance = distance;
        EshkolNum = eshkolNum;
        Shelot = shelot;
        NextStationSerialNum = nextStationSerialNum;
        DistanceFromPreviousStation = distanceFromPreviousStation;
        CityCode = cityCode;
        AreaCode = areaCode;
        SharedCode = sharedCode;
        StationName = stationName;
        Mafeel = mafeel;
        Eshkol = eshkol;
        SubEshkol = subEshkol;
        Mahuz = mahuz;
        UpDown = upDown;
        Floor = floor;
        Platform = platform;
        CityName = cityName;
        Address = address;
        HouseNum = houseNum;
        ActiveStation = activeStation;
        AreaName = areaName;
        SharedDesc = sharedDesc;
        RouteTime = routeTime;
        Latitude = latitude;
        Longitude = longitude;
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

    public int getStationSerialNum() {
        return StationSerialNum;
    }

    public void setStationSerialNum(int stationSerialNum) {
        StationSerialNum = stationSerialNum;
    }

    public int getStationSerialID() {
        return StationSerialID;
    }

    public void setStationSerialID(int stationSerialID) {
        StationSerialID = stationSerialID;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public int getEshkolNum() {
        return EshkolNum;
    }

    public void setEshkolNum(int eshkolNum) {
        EshkolNum = eshkolNum;
    }

    public String getShelot() {
        return Shelot;
    }

    public void setShelot(String shelot) {
        Shelot = shelot;
    }

    public int getNextStationSerialNum() {
        return NextStationSerialNum;
    }

    public void setNextStationSerialNum(int nextStationSerialNum) {
        NextStationSerialNum = nextStationSerialNum;
    }

    public int getDistanceFromPreviousStation() {
        return DistanceFromPreviousStation;
    }

    public void setDistanceFromPreviousStation(int distanceFromPreviousStation) {
        DistanceFromPreviousStation = distanceFromPreviousStation;
    }

    public int getCityCode() {
        return CityCode;
    }

    public void setCityCode(int cityCode) {
        CityCode = cityCode;
    }

    public int getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(int areaCode) {
        AreaCode = areaCode;
    }

    public int getSharedCode() {
        return SharedCode;
    }

    public void setSharedCode(int sharedCode) {
        SharedCode = sharedCode;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
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

    public String getUpDown() {
        return UpDown;
    }

    public void setUpDown(String upDown) {
        UpDown = upDown;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public String getActiveStation() {
        return ActiveStation;
    }

    public void setActiveStation(String activeStation) {
        ActiveStation = activeStation;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getSharedDesc() {
        return SharedDesc;
    }

    public void setSharedDesc(String sharedDesc) {
        SharedDesc = sharedDesc;
    }

    public float getRouteTime() {
        return RouteTime;
    }

    public void setRouteTime(float routeTime) {
        RouteTime = routeTime;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    @Override
    public String toString() {
        return "" +
                "LineNum=" + LineNum +
                ", LineID=" + LineID +
                ", Direction=" + Direction +
                ", Replacement=" + Replacement +
                ", StationSerialNum=" + StationSerialNum +
                ", StationSerialID=" + StationSerialID +
                ", Distance=" + Distance +
                ", StationName='" + StationName + '\'' +
                ", RouteTime=" + RouteTime +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude;
    }
}
