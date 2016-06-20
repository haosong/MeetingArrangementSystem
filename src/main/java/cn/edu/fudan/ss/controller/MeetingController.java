package cn.edu.fudan.ss.controller;

import cn.edu.fudan.ss.bean.Meeting;
import cn.edu.fudan.ss.bean.MeetingEmployee;
import cn.edu.fudan.ss.dao.Dao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("meeting")
public class MeetingController {

    private final static int TOTAL_ROOMS = 1 << 10;

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    public String create(@Context HttpServletRequest httpServletRequest,
                         @QueryParam("sponsor") String sponsor,
                         @QueryParam("subject") String title,
                         @QueryParam("names") String employeeList,
                         @QueryParam("attend") String attend,
                         @QueryParam("date") String startTime,
                         @QueryParam("content") String content,
                         @QueryParam("time") int duration) throws JSONException, ParseException, SQLException {
        Date date = new Date(new SimpleDateFormat("MM-dd-yyyy HH:mm").parse(startTime.substring(0, 16)).getTime());
        Time time = new Time(new SimpleDateFormat("MM-dd-yyyy HH:mm").parse(startTime.substring(0, 16)).getTime());
        String[] employees = employeeList.split(",");
        String[] attends = attend.split(",");
        boolean[] mustAttend = new boolean[employees.length];
        for (int i = 0; i < attends.length; i++) {
            if (attends[i].equals("true")) {
                mustAttend[i] = true;
            } else {
                mustAttend[i] = false;
            }
        }
        int n = mustAttend.length;
        int m = 20;
        int timeLine[][][] = new int[n][m][2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                timeLine[i][j][0] = -1;
                timeLine[i][j][1] = -1;
            }
        for (int i = 0; i < n; i++) {
            if (!mustAttend[i])
                continue;
            String sql = "select * from meeting_employee where employee='" + employees[i] + "'";
            List<MeetingEmployee> meetingEmployees = Dao.queryMeetingEmployee(sql);
            for (int j = 0; j < meetingEmployees.size(); j++) {
                timeLine[i][j][0] = meetingEmployees.get(j).start;
                timeLine[i][j][1] = meetingEmployees.get(j).end;
            }
        }
        int start = getTime(time);
        int end = getTime(date) + duration;
        JSONObject response = new JSONObject();
        if (checkEmployeeAvailable(timeLine, n, m, start, end)) {
            for (int i = 0; i < TOTAL_ROOMS; i++) {
                if (checkRoomAvailable(i, start, end)) {
                    Meeting meeting = new Meeting(title, i, sponsor, content, new Timestamp(time.getTime()),
                            duration, employees);
                    meeting.insert();
                    response.put("status", "0");
                    response.put("meeting", meeting.toJSONObject());
                    return response.toString();
                }
            }
        }
        System.out.println("Here");
        int k = 0;
        List<Meeting> availableMeetings = new ArrayList<Meeting>();
        for (int i = 10; ;i += 10) {
            if (k > 7 || availableMeetings.size() > 15) break;
            long tmp = start + i;
            Time tmpTime = new Time(tmp * 60L * 1000L);
            Date tmpDate = new Date(tmp * 60L * 1000L);
            System.out.println(tmpTime);
            if (tmpTime.getHours() > 18) {
                i = i + 14 * 60; k++;
                continue;
            }
            if (tmpDate.getDay() < 1 || tmpDate.getDay() > 5) {
                i = i + 24 * 60; k++;
                continue;
            }
            if (checkEmployeeAvailable(timeLine, n, m, start + i, end + i)) {
                for (int j = 0; j < TOTAL_ROOMS; j++) {
                    if (checkRoomAvailable(j, start + i, end + i)) {
                        Meeting meeting = new Meeting(title, j, sponsor, content, new Timestamp(tmpTime.getTime()),
                                duration, employees);
                        availableMeetings.add(meeting);
                        i = i + duration - (i + duration) % 10;
                        break;
                    }
                }
            }
        }
        response.put("status", "-1");
        JSONArray meetingArray = new JSONArray();
        int delta = availableMeetings.size() / 5; k = 0;
        delta = delta > 0? delta: 1;
        while (k < availableMeetings.size()) {
            meetingArray.put(availableMeetings.get(k).toJSONObject());
            k += delta;
        }
        response.put("available", meetingArray);
        return response.toString();
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public String list(@Context HttpServletRequest httpServletRequest) throws JSONException {
        JSONObject response = new JSONObject();

        return response.toString();
    }

    private boolean checkRoomAvailable(int roomId, int start, int end) throws SQLException {
        String sqlQuery = "select * from meeting_room where roomId='" + roomId + "' and ((start<'" + start
                + "' and end>'" + start + "') or (start<'" + end + "' and start>'" + start + "'))";
        if (Dao.queryRecordsCount(sqlQuery) > 0)
            return false;
        return true;
    }

    private boolean checkEmployeeAvailable(int[][][] timeLine, int n, int m, int start, int end) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (timeLine[i][j][0] < 0)
                    break;
                if (!checkTimeAvailable(start, end, timeLine[i][j][0], timeLine[i][j][1])) {
                    return false;
                }
            }
        return true;
    }

    private boolean checkTimeAvailable(int x1, int y1, int x2, int y2) {
        if ((x1 > y2) || (y1 < x2))
            return true;
        return false;
    }

    private int getTime(Timestamp timestamp) {
        return (int) (timestamp.getTime() / 1000L / 60L);
    }

    private int getTime(Time time) {
        return (int) (time.getTime() / 1000L / 60L);
    }

    private int getTime(Date date) {
        return (int) (date.getTime() / 1000L / 60L);
    }

    private Date getTomorrow(Date tody) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tody);
        calendar.add(calendar.DATE, 1);
        return new Date(calendar.getTime().getTime());
    }


}
