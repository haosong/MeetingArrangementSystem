package cn.edu.fudan.ss.controller;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.Meeting;
import cn.edu.fudan.ss.bean.MeetingEmployee;
import cn.edu.fudan.ss.dao.Dao;
import cn.edu.fudan.ss.notification.Notify;
import cn.edu.fudan.ss.notification.impl.EmailNotification;
import cn.edu.fudan.ss.notification.impl.WechatNotification;
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

    public Dao dao = null;

    private final static int TOTAL_ROOMS = 1 << 8;
    private final static int TOTAL_MEETINGS = 1 << 8;

    private final static Notify emailNotification;
    private final static Notify wechatNotification;

    private int createFlag = 0;

    private boolean createMeeting;

    static {
        emailNotification = new EmailNotification();
        wechatNotification = new WechatNotification();
    }

    public MeetingController(){
        dao = Dao.getInstance();
    }

    public int getCreateFlag(){
        return createFlag;
    }

    public Notify getEmailNotification(){
        return emailNotification;
    }

    public Notify getWechatNotification(){
        return wechatNotification;
    }

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    public String create(@Context HttpServletRequest httpServletRequest,
                         @QueryParam("sponsor") String sponsor,
                         @QueryParam("title") String title,
                         @QueryParam("employees") String employeeList,
                         @QueryParam("attend") String attend,
                         @QueryParam("start") String startTime,
                         @QueryParam("content") String content,
                         @QueryParam("duration") int duration) throws JSONException, ParseException, SQLException {
        Date date = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(startTime.substring(0, 16)).getTime());
        Time time = new Time(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(startTime.substring(0, 16)).getTime());
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

        createFlag = 0;
        //System.out.println(employees.length);

        int n = mustAttend.length;
        int m = TOTAL_MEETINGS;
        int timeLine[][][] = new int[n][m][2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                timeLine[i][j][0] = -1;
                timeLine[i][j][1] = -1;
            }
        for (int i = 0; i < n; i++) {
            if (!mustAttend[i])
                continue;
            String sql = "select * from sslab3.meeting_employee where employee='" + employees[i] + "' and attend='1'";

            List<MeetingEmployee> meetingEmployees = dao.queryMeetingEmployee(sql);

            for (int j = 0; j < meetingEmployees.size(); j++) {
                timeLine[i][j][0] = meetingEmployees.get(j).start;
                timeLine[i][j][1] = meetingEmployees.get(j).end;
            }
        }

        int start = getTime(time);
        int end = getTime(date) + duration;
        JSONObject response = new JSONObject();
        if (checkEmployeeAvailable(timeLine, n, m, start, end)) {
            for (int i = 1; i < TOTAL_ROOMS; i++) {
                if (checkRoomAvailable(i, start, end)) {
                    createFlag = 1;
                    Meeting meeting = new Meeting(title, i, sponsor, content, new Timestamp(time.getTime()),
                            duration, employees, mustAttend);
                    meeting.setAttend(attend);
                    meeting.setEmployeeList(employeeList);
                    meeting.insert();
                    final String[] _employees = employees;
                    final Meeting _meeting = meeting;
                    new Thread(new Runnable() {
                        public void run() {
                            notifyEmployee(_employees, _meeting);
                        }
                    }).start();
                    response.put("status", "0");
                    response.put("meeting", meeting.toJSONObject());
                    return response.toString();
                }
            }
        }
        createFlag = -1;
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
                                duration, employees, mustAttend);
                        meeting.setAttend(attend);
                        meeting.setEmployeeList(employeeList);
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
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public String search(@Context HttpServletRequest httpServletRequest,
                       @QueryParam("name") String employee) throws SQLException, ParseException, JSONException {
        String sql = "select * from sslab3.meeting_employee where employee='" + employee + "'";
        List<MeetingEmployee> meetingEmployeeList = dao.queryMeetingEmployee(sql);
        JSONObject response = new JSONObject();
        JSONArray meetings = new JSONArray();
        for (MeetingEmployee meetingEmployee: meetingEmployeeList) {
            sql = "select * from sslab3.meeting where id='" + meetingEmployee.getMeetingId() + "'";
            List<Meeting> meetingList = dao.getMeetings(sql);
            for (Meeting meeting: meetingList) {
                meetings.put(meeting.toJSONObject());
            }
        }
        response.put("name", employee);
        response.put("meeting", meetings);
        return response.toString();
    }

    public boolean checkRoomAvailable(int roomId, int start, int end) throws SQLException {
        String sqlQuery = "select * from meeting_room where roomId='" + roomId + "' and ((start <='" + start
                + "' and end>='" + start + "') or (start<='" + end + "' and start>='" + start + "'))";
        int count = dao.queryRecordsCount(sqlQuery);
        if ( count > 0)
            return false;
        return true;
    }

    public boolean checkEmployeeAvailable(int[][][] timeLine, int n, int m, int start, int end) {
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

    public boolean checkTimeAvailable(int x1, int y1, int x2, int y2) {
        if ((x1 > y2) || (y1 < x2))
            return true;
        return false;
    }

    public int getTime(Time time) {
        return (int) (time.getTime() / 1000L / 60L);
    }

    public int getTime(Date date) {
        return (int) (date.getTime() / 1000L / 60L);
    }

    public Employee employee;
    public void notifyEmployee(final String[] employees, final Meeting meeting) {
        for (int k = 0; k < employees.length; k++) {
            String sqlQuery = "select * from employee where name='" + employees[k] + "'";

            try {
                employee = dao.findEmployee(sqlQuery);
                System.out.println(employee.getName());
                emailNotification.notify(employee, meeting, meeting.getEmployeeLevel(k));
                wechatNotification.notify(employee, meeting, meeting.getEmployeeLevel(k));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(emailNotification.getFlag());
    }
}
