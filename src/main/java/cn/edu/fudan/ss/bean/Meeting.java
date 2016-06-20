package cn.edu.fudan.ss.bean;

import cn.edu.fudan.ss.dao.Dao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Meeting {
    private int id;
    private String title, content;
    private int roomId;
    private String sponsor;
    private Timestamp start;
    private int duration;
    private String[] employees;

    public Meeting() {
    }

    public Meeting(String title, int roomId,
                   String sponsor, String content,
                   Timestamp start, int duration,
                   String[] employees) {
        this.title = title;
        this.roomId = roomId;
        this.sponsor = sponsor;
        this.start = start;
        this.duration = duration;
        this.employees = employees;
        this.content = content;
    }

    public JSONObject toJSONObject() {
        JSONObject meeting = new JSONObject();
        try {
            meeting.put("id", id);
            meeting.put("title", title);
            meeting.put("roomId", roomId);
            meeting.put("sponsor", sponsor);
            meeting.put("start", start);
            meeting.put("duration", duration);
            JSONArray employeeArray = new JSONArray();
            for (String employee: employees) {
                employeeArray.put(employee);
            }
            meeting.put("employees", employeeArray);
            meeting.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meeting;
    }

    public void insert() throws SQLException {
        String sqlInsert = "insert into meeting(title, roomId, start, duration, sponsor, content) " +
                "values('" + title + "', '" + roomId + "', '" + start + "', '" + duration
                           + "', '" + sponsor + "', '" + content + "')";
        Dao.insert(sqlInsert);
        String sqlQuery = "select * from meeting where title='" + title + "' and (start between '" + start + "' and '" + start + "')";
        id = Dao.findMeetingId(sqlQuery);
        for (String employee: employees) {
            sqlInsert = "insert into meeting_employee(employee, start, end, meetingId) " +
                    "values('" + employee + "', '" + ((int) (start.getTime() / 60000L)) + "', '"
                     + (((int) (start.getTime() / 60000L)) + duration) + "', '" + id + "')";
            Dao.insert(sqlInsert);
        }
        sqlInsert = "insert into meeting_room(roomId, start, end, meetingId) " +
                "values('" + roomId + "', '" + ((int) (start.getTime() / 60000L)) + "', '"
                + (((int) (start.getTime() / 60000L)) + duration) + "','" + id + "')";
        Dao.insert(sqlInsert);
    }
}
