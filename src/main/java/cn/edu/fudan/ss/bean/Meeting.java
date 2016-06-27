package cn.edu.fudan.ss.bean;

import cn.edu.fudan.ss.dao.Dao;
import cn.edu.fudan.ss.iterator.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.edu.fudan.ss.observer.Observer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Meeting implements Observer{
    private int id;
    private String title, content;
    private int roomId;
    private String sponsor;
    private Timestamp start;
    private int duration;
    private String[] employees;
    private int[] mustAttend;
    private Iterator iterator;
    private int size;

    private String employeeList;
    private String attend;
    private static Dao dao;

    static{
        dao = Dao.getInstance();
    }

    public Meeting() {
        size = 0;
    }

    public Meeting(String title, int roomId,
                   String sponsor, String content,
                   Timestamp start, int duration,
                   String[] employees, boolean[] mustAttend) {
        this.title = title;
        this.roomId = roomId;
        this.sponsor = sponsor;
        this.start = start;
        this.duration = duration;
        this.employees = employees;
        size = employees.length;
        ((EmployeeIterator)iterator).currentIndex = 0;
        this.content = content;
        this.mustAttend = new int[mustAttend.length];
        for (int i = 0; i < mustAttend.length; i++) {
            if (mustAttend[i]) {
                this.mustAttend[i] = 1;
            } else {
                this.mustAttend[i] = 0;
            }
        }
    }

    public String getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(String employeeList) {
        this.employeeList = employeeList;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String[] getEmployees() {
        return employees;
    }

    public void setEmployees(String[] employees) {
        this.employees = employees;
        size = employees.length;
        ((EmployeeIterator)iterator).currentIndex = 0;
    }

    public String getEmployeeLevel(int i) {
        if (mustAttend[i] != 0) {
            return "must attend";
        } else {
            return "nice to attend";
        }
    }

    public JSONObject toJSONObject() {
        JSONObject meeting = new JSONObject();
        try {
            meeting.put("id", id);
            meeting.put("title", title);
            meeting.put("roomId", roomId);
            meeting.put("sponsor", sponsor);
            meeting.put("start", new SimpleDateFormat("MM/dd/yyyy HH:mm").format(start));
            meeting.put("duration", duration);
            meeting.put("names", employeeList);
            meeting.put("attend", attend);
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
        dao.insert(sqlInsert);
        String sqlQuery = "select * from meeting where title='" + title + "' and (start between '" + start + "' and '" + start + "')";
        id = dao.findMeetingId(sqlQuery);
        for (int i = 0; i < employees.length; i++) {
            sqlInsert = "insert into meeting_employee(employee, start, end, meetingId, attend) " +
                    "values('" + employees[i] + "', '" + ((int) (start.getTime() / 60000L)) + "', '"
                     + (((int) (start.getTime() / 60000L)) + duration) + "', '" + id + "', '" + mustAttend[i] + "')";
            dao.insert(sqlInsert);
        }
        sqlInsert = "insert into meeting_room(roomId, start, end, meetingId) " +
                "values('" + roomId + "', '" + ((int) (start.getTime() / 60000L)) + "', '"
                + (((int) (start.getTime() / 60000L)) + duration) + "','" + id + "')";
        dao.insert(sqlInsert);
    }

    public void update() {
        System.out.println("Employee has changed.");
    }

    public Iterator iterator(){
        return new EmployeeIterator();
    }

    private class EmployeeIterator implements Iterator{
        private int currentIndex = 0;

        public boolean hasNext() {
            if(currentIndex >= size) return false;
            else return true;
        }

        public Object next() {
            Object o = employees[currentIndex];
            currentIndex++;
            return o;
        }
    }

}
