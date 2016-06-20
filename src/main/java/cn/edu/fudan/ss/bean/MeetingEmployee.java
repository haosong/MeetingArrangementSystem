package cn.edu.fudan.ss.bean;

public class MeetingEmployee {
    public String employee;
    public int start, end, meetingId;

    public MeetingEmployee(String employee, int start, int end, int meetingId) {
        this.employee = employee;
        this.start = start;
        this.end = end;
        this.meetingId = meetingId;
    }
}
