import Controllers.Bus;
import Controllers.StationInfo;
import SQLConnection.DBConnection;
import Scheduling.ScheduleBuses;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args){

        ScheduleBuses scheduleBuses = new ScheduleBuses();
        scheduleBuses.ExecuteAlgo();
        scheduleBuses.PrintSingleRoutesArray();

        ArrayList<Bus> buses = scheduleBuses.getBuses();

        for(int i = 0; i < scheduleBuses.getBuses().size(); i++){

            Bus current = buses.get(i);

            if(current.getId() % 50 == 0)
                System.out.println(current);

        }

        System.out.println("Wasted Time: " + scheduleBuses.GetWastedTimeForBuses() * 100 + " %.");


//        DBConnection db = DBConnection.getInstance();
//
//        if(db.isConnected()){
//            System.out.println("Connection Established.");
//
//            StationInfo st = db.getSingleStationInfo(348);
//            System.out.println(st);
//
//        }else{
//            System.out.println("Connection Failed ..");
//            System.out.println(db.getFailedConnectionResult());
//        }

    }

}
