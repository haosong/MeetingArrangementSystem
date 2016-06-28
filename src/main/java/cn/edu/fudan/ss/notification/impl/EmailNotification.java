package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.Meeting;
import cn.edu.fudan.ss.notification.Notify;

import java.sql.Date;
import java.sql.Timestamp;

public class EmailNotification extends Notification {
    private Notification notification;

    public EmailNotification(){
    }

    public EmailNotification(Notification notification){
        this.notification = notification;

    }

    private String flag = "Email not notified.";

    public String getFlag(){
        return flag;
    }

    public void notify(Employee employee, Meeting meeting, String attend) {
        notification.notify(employee, meeting, attend);
        System.out.println("Email Notify: to " + employee.getName() + " " + employee.getEmail());
        System.out.println("level: " + attend);
        System.out.println("@room_" + meeting.getRoomId());
        System.out.println("date: " + new Date(meeting.getStart().getTime()));
        System.out.println("duration: " + meeting.getDuration());
        System.out.println(" ======= ");
        flag = "Email notified.";
    }
}
