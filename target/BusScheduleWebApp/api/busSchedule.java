import Controllers.ScheduleInfo;
import Controllers.StationInfo;
import SQLConnection.DBConnection;
import SQLConnection.Schedule;
import SQLConnection.ScheduleInJSON;
import SQLConnection.Users;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "busSchedule")
public class busSchedule extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String uri = request.getRequestURI();

        int index = uri.indexOf("/busSchedule/");

        String param = "No Param";

        if(index != -1){
            param = uri.substring(index + "/busSchedule/".length());
        }

        switch (param){
            case "test":
                out.println(test());
                break;

            case "tt":
                out.println("Function Under Construction");
                break;

            default:
                out.println("404 NOT FOUND");
                break;

        }



        //out.println(new Gson().toJson(getSchedule()));
    }

    private List<ScheduleInJSON> getSchedule(){

        DBConnection db = DBConnection.getInstance();
        List<ScheduleInJSON> arrayList = db.getAllSchedulesInJSONFormat();
        return arrayList;
    }

    public String test(){
        return "TESTING";
    }

}
