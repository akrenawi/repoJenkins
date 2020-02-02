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

        out.println(new Gson().toJson(getSchedule()));
    }

    private List<ScheduleInJSON> getSchedule(){

        DBConnection db = DBConnection.getInstance();
        List<ScheduleInJSON> arrayList = db.getAllSchedulesInJSONFormat();
        return arrayList;
    }
}
