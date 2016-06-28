package cn.edu.fudan.ss.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class MeetingEmployee {
    public String employee;
    public int start, end, meetingId;

    public MeetingEmployee(String employee, int start, int end, int meetingId) {
        this.employee = employee;
        this.start = start;
        this.end = end;
        this.meetingId = meetingId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public JSONObject toJSONObject(){
        JSONObject user = new JSONObject();
        try {
            user.put("employee", employee);
            user.put("start", start);
            user.put("end", end);
            user.put("meetingId", meetingId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
