package Scheduling;

import Controllers.Bus;
import Controllers.LatLong;
import Controllers.ScheduleInfo;
import Controllers.StationInfo;
import SQLConnection.DBConnection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleBuses {

    private ArrayList<StationInfo> stationInfoArrayList;
    private ArrayList<ScheduleInfo> scheduleInfoArrayList;

    private ArrayList<RouteInfo> RoutesList;
    private ArrayList<SingleRouteInfo> routes;

    private ArrayList<Bus> buses;

    private DBConnection db;

    public ScheduleBuses(){

        db = DBConnection.getInstance();

        stationInfoArrayList = db.getAllStationInfo();
        scheduleInfoArrayList = db.getAllScheduleInfo();

        RoutesList = new ArrayList<RouteInfo>();
        routes = new ArrayList<SingleRouteInfo>();

        buses = new ArrayList<Bus>();
    }

    private void CreateRoutesList(){

        int startIndex = 0;

        for(int i = 1; i < stationInfoArrayList.size(); i++){

            if(stationInfoArrayList.get(i).getStationSerialNum() == 1){

                RoutesList.add(new RouteInfo(stationInfoArrayList.get(startIndex), stationInfoArrayList.get(i - 1)));
                startIndex = i;
            }
        }

        RoutesList.add(new RouteInfo(stationInfoArrayList.get(startIndex), stationInfoArrayList.get(stationInfoArrayList.size() - 1)));
    }

    private void CreateScheduleInfoRoutes(){

        for(int i = 0; i < scheduleInfoArrayList.size(); i++){

            ScheduleInfo scheduleInfo = scheduleInfoArrayList.get(i);

            int index = getIndexOfRouteList(scheduleInfo.getLineID(), scheduleInfo.getDirection(), scheduleInfo.getReplacement());

            if(index != -1){
                RoutesList.get(index).addScheduleInfo(scheduleInfo);
            }
        }

    }

    private int getIndexOfRouteList(int lineId, int direction, int replacement){

        for(int i = 0; i < RoutesList.size(); i++){

            StationInfo s = RoutesList.get(i).getStartStation();

            if(s != null && s.getLineID() == lineId && s.getDirection() == direction && s.getReplacement() == replacement)
                return i;
        }

        return -1;
    }

    public void PrintStationsInfoArrayList(){
        for(int i = 0; i < stationInfoArrayList.size(); i++){
            System.out.println(stationInfoArrayList.get(i));
        }
    }

    public void PrintTimesArrayList(){
        for(int i = 0; i < scheduleInfoArrayList.size(); i++){
            System.out.println(scheduleInfoArrayList.get(i));
        }
    }

    private void PrintRouteList(){
        for(int i = 0; i < RoutesList.size(); i++){
            System.out.println(RoutesList.get(i));
        }
    }

    public void FillSingleRoutes(){

        for(int i = 0; i < RoutesList.size(); i++){

            RouteInfo currentRoute = RoutesList.get(i);

            if(currentRoute.getScheduleInfos() != null && currentRoute.getScheduleInfos().size() > 0){
                for(int j = 0; j < currentRoute.getScheduleInfos().size(); j++){

                    Calendar startTime = currentRoute.getScheduleInfos().get(j).getExitTime();
                    Calendar endTime = (Calendar) startTime.clone();
                    endTime.add(Calendar.MINUTE, (int)currentRoute.getEndStation().getRouteTime());

                    routes.add(new SingleRouteInfo(currentRoute.getStartStation(), currentRoute.getEndStation(), startTime, endTime));
                }
            }
        }
    }

    public void PrintSingleRoutesArray(){
        for(int i = 0; i < routes.size(); i++){

            System.out.println(" number : "  + i + " , " + routes.get(i));
        }
    }

    public void SortSingleRoutes(){
        for(int i = 0; i < routes.size() - 1; i++){
            for(int j = i + 1; j < routes.size(); j++){

                if(routes.get(i).getStartTime().after(routes.get(j).getStartTime())){

                    SingleRouteInfo s = routes.get(i);
                    routes.set(i, routes.get(j));
                    routes.set(j, s);

                }

            }
        }
    }

    /**
     * Schedule Buses Algorithm
     * **/
    public void Schedule(){

        int countOfMinBuses = getCountOfMinBuses();
        ArrayList<Calendar> busEndsArray = fillBusEndsArray(countOfMinBuses);
        fillRestOfRoutes(countOfMinBuses, busEndsArray);
    }

    /**
     * Get Count Of Minimum buses
     * **/
    private int getCountOfMinBuses(){
        int countOfMinBuses = 1;

        Date startTime = routes.get(0).getStartTime().getTime();

        for(int i = 1; i < routes.size(); i++){
            if(startTime.equals(routes.get(i).getStartTime().getTime())){
                countOfMinBuses++;
            }else{
                break;
            }
        }

        return countOfMinBuses;
    }

    /**
     * fill buses array list according to count of minimum buses
     * **/
    private ArrayList<Calendar> fillBusEndsArray(int countOfMinBuses){
        ArrayList<Calendar> busEndTime = new ArrayList<Calendar>();

        for(int i = 0; i < countOfMinBuses; i++) {
            busEndTime.add(routes.get(i).getEndTime());
            addBus();
            buses.get(buses.size() - 1).addRoute(routes.get(i));
        }

        return busEndTime;
    }

    /**
     * Create New Bus
     * **/
    private void addBus(){
        buses.add(new Bus());
    }

    /**
     * get available bus index according to bus location is same as route origin station
     * **/
    private int getAvailableBusIndexByTimeAndOriginLocation(ArrayList<Integer> availableBusIndex, SingleRouteInfo routeInfo){
        for(int i = 0; i < availableBusIndex.size(); i++){

            int routeOriginStationSerialId = routeInfo.getStartStation().getStationSerialID();

            int busIndex = availableBusIndex.get(i);
            SingleRouteInfo currentBusRouteLocation = buses.get(busIndex).getSingleRoute(buses.get(busIndex).getRoutes().size() - 1);
            int currentBusSerialId = currentBusRouteLocation == null ? -1 : currentBusRouteLocation.getEndStation().getStationSerialID();

            if(routeOriginStationSerialId == currentBusSerialId){
                return busIndex;
            }
        }

        return -1;
    }

    /**
     * get List Of Indexes of available buses
     * **/
    private ArrayList<Integer> getListOfAvailableBuses(ArrayList<Calendar> busEnds, SingleRouteInfo route){

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        if(busEnds == null || busEnds.size() == 0)
            return arrayList;

        for(int i = 0; i < busEnds.size(); i++){
            Calendar c = (Calendar) busEnds.get(i).clone();
            c.add(Calendar.MINUTE, 30);

            if(route.getStartTime().after(c))
                arrayList.add(i);
        }

        return arrayList;
    }


    /**
     * Get Available Bus By Time and Minimum Lat, Long Location
     * **/
    private int getAvailableBusIndexByTimeAndLatLongLocation(ArrayList<Integer> availableBusIndex, SingleRouteInfo route) {

        LatLong latLongOrigin = new LatLong(route.getStartStation().getLatitude(), route.getStartStation().getLongitude());
        int busIndex = -1;
        double distance = 50000000;

        for(int i = 0; i < availableBusIndex.size(); i++){

            int currIndex = availableBusIndex.get(i);
            SingleRouteInfo currentRoute = buses.get(currIndex).getSingleRoute(buses.get(currIndex).getRoutes().size() - 1);
            double currentDistance = latLongOrigin.GetDistance(new LatLong(currentRoute.getEndStation().getLatitude(), currentRoute.getEndStation().getLongitude()));

            float currMinutes = getDistanceInMin((float)currentDistance);

            if(getDiffInMinutesBetweenTwoDates(currentRoute.getEndTime().getTime(), route.getStartTime().getTime()) >= currMinutes){
                if(currentDistance < distance){
                    distance = currentDistance;
                    busIndex = currIndex;
                }
            }
        }

        return busIndex;
    }

    /**
     * fill All Routes
     * **/
    private void fillRestOfRoutes(int countOfMinBuses, ArrayList<Calendar> busEndsArray){
        for(int i = countOfMinBuses; i < routes.size(); i++){

            SingleRouteInfo route = routes.get(i);

            ArrayList<Integer> listOfAvailableBusesIndexes = getListOfAvailableBuses(busEndsArray, route);

            if(listOfAvailableBusesIndexes.size() == 0){
                busEndsArray = createNewBusAndInsertData(route, busEndsArray);
            }else{

                int busIndex = getAvailableBusIndexByTimeAndOriginLocation(listOfAvailableBusesIndexes, route);

                if(busIndex == -1) {
                    busIndex = getAvailableBusIndexByTimeAndLatLongLocation(listOfAvailableBusesIndexes, route);

                    if (busIndex == -1) {
                        busEndsArray = createNewBusAndInsertData(route, busEndsArray);
                    } else {
                        busEndsArray.set(busIndex, route.getEndTime());
                        buses.get(busIndex).addRoute(route);
                    }

                }else{
                    busEndsArray.set(busIndex, route.getEndTime());
                    buses.get(busIndex).addRoute(route);
                }
            }
        }
    }

    /**
     * create new bus
     * add Route to it
     * **/
    private ArrayList<Calendar> createNewBusAndInsertData(SingleRouteInfo route, ArrayList<Calendar> busEndsArray){
        addBus();
        buses.get(buses.size() - 1).addRoute(route);

        if(busEndsArray == null)
            busEndsArray = new ArrayList<Calendar>();

        busEndsArray.add(route.getEndTime());

        return busEndsArray;
    }

    public void PrintBusArray(){
        for(int i = 0; i < buses.size(); i++){
            System.out.println(buses.get(i));
        }
    }

    private int getDiffInMinutesBetweenTwoDates(Date d1, Date d2){
        return  (int)( (d2.getTime() - d1.getTime())  / (1000 * 60) );
    }

    /**
     * Get Wasted Time
     * */
    public double GetWastedTimeForBuses(){

        if(buses == null || buses.size() == 0)
            return 0;

        double minutesTotal = 0, minutesWaited = 0;
        Calendar min = (Calendar) this.routes.get(0).getStartTime().clone();
        Calendar max = (Calendar) this.routes.get(routes.size()-1).getStartTime().clone();
        System.out.println("min time is: "  + min.getTime() +   " ,  max time is: " + max.getTime());
        for(int i = 0; i < buses.size(); i++){


            ArrayList<SingleRouteInfo> routes = buses.get(i).getRoutes();

            if(routes.size() > 0){
                minutesWaited += getDiffInMinutesBetweenTwoDates(min.getTime(), routes.get(0).getStartTime().getTime());
                minutesWaited += getDiffInMinutesBetweenTwoDates(routes.get(routes.size() -1 ).getStartTime().getTime(),max.getTime() );
                for(int j = 0; j < routes.size() - 1; j++){

                    Date first = routes.get(j).getEndTime().getTime();
                    Date second = routes.get(j + 1).getStartTime().getTime();

                    int diffInMinutes = getDiffInMinutesBetweenTwoDates(first, second);
                    diffInMinutes -= 30;

                    minutesWaited += diffInMinutes;

                    minutesTotal +=  getDiffInMinutesBetweenTwoDates(routes.get(j).getStartTime().getTime(), routes.get(j).getEndTime().getTime());

                }

                minutesTotal += getDiffInMinutesBetweenTwoDates(routes.get(routes.size() - 1).getStartTime().getTime(), routes.get(routes.size() - 1).getEndTime().getTime());
            }

        }

        System.out.println("MinWait: " + minutesWaited + ", MinTotal: " + minutesTotal);

        return ( minutesTotal != 0 ? (minutesWaited / minutesTotal) : 0);
    }

    public void ExecuteAlgo(){

        CreateRoutesList();
        CreateScheduleInfoRoutes();

        FillSingleRoutes();
        SortSingleRoutes();

        ArrayList<SingleRouteInfo> newRoutes = new ArrayList<>();

        for(int i=13 ;i<routes.size();i++)
            {
                newRoutes.add(routes.get(i));
            }

        this.routes = new ArrayList<>();
        for(int i=0 ; i<newRoutes.size();i++)
        {
            routes.add(newRoutes.get(i));
        }

        //Schedule();

        ScheduleAdvanced();
    }

    /**
     * Clone An Array
     * **/
    private ArrayList<SingleRouteInfo> CloneArray(ArrayList<SingleRouteInfo> arr){
        ArrayList<SingleRouteInfo> arrayList = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++)
            arrayList.add(new SingleRouteInfo(arr.get(i)));

        return arrayList;
    }

    private void ScheduleAdvanced(){

        ArrayList<SingleRouteInfo> routesCopy = CloneArray(routes);

        //Create Bus and Add the First Route
        addBus();
        buses.get(0).addRoute(routesCopy.get(0));
        routesCopy.remove(routesCopy.get(0));

        while (routesCopy.size() > 0) {

            int indexBySameOrigin = getIndexOfEarliestRouteBySameOrigin(routesCopy);
            int indexByDistance = getIndexOfEarliestRouteByDistance(routesCopy);

            if(indexByDistance == -1 && indexBySameOrigin == -1){
                addBus();
                buses.get(buses.size() -1).addRoute(routesCopy.get(0));
                routesCopy.remove(routesCopy.get(0));

            }
            else if(indexByDistance != -1 && indexBySameOrigin != -1){

                //GET MIN
                if(routesCopy.get(indexBySameOrigin).getStartTime().
                        after(routesCopy.get(indexByDistance).getStartTime())){

                    buses.get(buses.size() - 1).addRoute(routesCopy.get(indexByDistance));
                    routesCopy.remove(routesCopy.get(indexByDistance));

                }else{

                    buses.get(buses.size() - 1).addRoute(routesCopy.get(indexBySameOrigin));
                    routesCopy.remove(routesCopy.get(indexBySameOrigin));

                }

            }
            else if(indexBySameOrigin != -1){
                buses.get(buses.size() - 1).addRoute(routesCopy.get(indexBySameOrigin));
                routesCopy.remove(routesCopy.get(indexBySameOrigin));
            }else{
                buses.get(buses.size() - 1).addRoute(routesCopy.get(indexByDistance));
                routesCopy.remove(routesCopy.get(indexByDistance));
            }

        }
    }

    /**
     * Get Index Of Earliest Route By Same Origin
     * **/
    private int getIndexOfEarliestRouteBySameOrigin(ArrayList<SingleRouteInfo> cpyArr){

        SingleRouteInfo lastRoute = buses.get(buses.size() - 1).getSingleRoute(buses.get(buses.size() - 1).getRoutes().size() - 1);

        Calendar editedEnd = Calendar.getInstance();
        editedEnd.setTime(lastRoute.getEndTime().getTime());
        editedEnd.add(Calendar.MINUTE, 30);

        lastRoute.setEndTime(editedEnd);

        ArrayList<SingleRouteInfo> matchesRoutes = new ArrayList<>();

        for(int i = 0; i < cpyArr.size(); i++){

            SingleRouteInfo curr = cpyArr.get(i);

            if(curr.getStartTime().after(lastRoute.getEndTime())
                && curr.getStartStation().getStationSerialID() == lastRoute.getEndStation().getStationSerialID()){
                matchesRoutes.add(curr);
            }

        }

        editedEnd.add(Calendar.MINUTE, -30);
        lastRoute.setEndTime(editedEnd);

        matchesRoutes = SortMatchesArray(matchesRoutes);
        return matchesRoutes == null ? -1 : cpyArr.indexOf(matchesRoutes.get(0));
    }

    /**
     * Sort Array Of Matches
     * **/
    private ArrayList<SingleRouteInfo> SortMatchesArray(ArrayList<SingleRouteInfo> arrayList){

        if(arrayList == null || arrayList.size() == 0)
            return null;

        for(int i = 0; i < arrayList.size() - 1; i++){
            for(int j = 1; j < arrayList.size(); j++){

                if(arrayList.get(i).getStartTime().after(arrayList.get(j))){

                    SingleRouteInfo tmp = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, tmp);
                }

            }
        }

        return arrayList;
    }

    /**
     * Get Index Of Earliest Route By Same Distance
     * **/
    private int getIndexOfEarliestRouteByDistance(ArrayList<SingleRouteInfo> cpyArr){

        SingleRouteInfo lastRoute = buses.get(buses.size() - 1).getSingleRoute(buses.get(buses.size() - 1).getRoutes().size() - 1);

        Calendar editedEnd = (Calendar) lastRoute.getEndTime().clone();
        editedEnd.add(Calendar.MINUTE, 30);
        lastRoute.setEndTime(editedEnd);

        ArrayList<SingleRouteInfo> matchesRoutes = new ArrayList<>();

        ArrayList<Integer> stationIds = new ArrayList<>();

        for(int i = 0; i < cpyArr.size(); i++){

            SingleRouteInfo curr = cpyArr.get(i);

            if(curr.getStartTime().after(lastRoute.getEndTime())
            ){
                if(!stationIds.contains(curr.getStartStation().getStationSerialID())){

                    stationIds.add(curr.getStartStation().getStationSerialID());
                    matchesRoutes.add(curr);

                }
            }

        }

        //TODO: 1. Calculate Distance IN MIN. Between Last Station and Each Station.
        ArrayList<Float> distancesInMin = new ArrayList<>();

        //LatLong latLong = new LatLong(lastRoute.getEndStation().getLatitude(), lastRoute.getEndStation().getLongitude());

        for(int i = 0; i < matchesRoutes.size(); i++){

            distancesInMin.add(getDistanceInMin((float) LatLong.distance(lastRoute.getEndStation().getLatitude(), lastRoute.getEndStation().getLongitude(),
                    matchesRoutes.get(i).getStartStation().getLatitude(),
                    matchesRoutes.get(i).getStartStation().getLongitude() )));
        }



        //TODO: 2. CHECK IF THERE IS ENOUGH TIME TO REACH REQUIRED STATION

        editedEnd.add(Calendar.MINUTE, -30);
        lastRoute.setEndTime(editedEnd);

        if(matchesRoutes == null || matchesRoutes.size() == 0)
            return -1;

        ArrayList<Integer> distanceOfmatchAndLastRoutesinMin = new ArrayList<>();
        int i = 0;
        int matchSize = matchesRoutes.size();
        int min = 50000;
        int indexMin = 0;

        while (i < matchSize)
        {
            Calendar edited = (Calendar) lastRoute.getEndTime().clone();
            edited.add(Calendar.MINUTE, (int)distancesInMin.get(i).floatValue());

            if(matchesRoutes.get(i).getStartTime().before(edited))
            {
                matchesRoutes.remove(i);
                distancesInMin.remove(i);
                matchSize--;
            }
            else{

                distanceOfmatchAndLastRoutesinMin.add(
                        getDiffInMinutesBetweenTwoDates(lastRoute.getEndTime().getTime()
                                ,matchesRoutes.get(i).getStartTime().getTime())
                        - (int)distancesInMin.get(i).floatValue()
                );

                if(distanceOfmatchAndLastRoutesinMin.get(i) < min) {

                    min = distanceOfmatchAndLastRoutesinMin.get(i);
                    indexMin = i;
                }

                i++;
            }
        }

        return (matchesRoutes == null || matchesRoutes.size() == 0) ? -1 : cpyArr.indexOf(matchesRoutes.get(indexMin));
    }

    private float getDistanceInMin(float dist){
        return (float) (dist / 30.0) * 60;
    }

    public void PrintAfterMixingFiles(){
        CreateRoutesList();
        CreateScheduleInfoRoutes();

        FillSingleRoutes();

        PrintSingleRoutesArray();
    }

    public ArrayList<Bus> getBuses() { return buses; }
}
