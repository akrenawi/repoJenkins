package Controllers;

import Scheduling.SingleRouteInfo;

import java.util.ArrayList;

public class Bus {

    private int id;
    private static int idSerial = 10000;

    private ArrayList<SingleRouteInfo> routes;

    public Bus(){
        id = idSerial;
        idSerial++;

        routes = new ArrayList<SingleRouteInfo>();
    }

    public int getId(){ return id; }

    public ArrayList<SingleRouteInfo> getRoutes() { return routes; }
    public SingleRouteInfo getSingleRoute(int position) {
        return (position >= routes.size() || position < 0) ? null : routes.get(position);
    }

    public void addRoute(SingleRouteInfo route){
        routes.add(route);
    }


    @Override
    public String toString() {
        String s = "";

        s += "Id: " + getId() + "\n";
        s += "Routes: " + routes + "\n";

        return s;
    }
}
