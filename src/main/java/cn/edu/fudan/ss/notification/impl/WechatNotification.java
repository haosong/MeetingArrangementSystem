package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.Meeting;
import cn.edu.fudan.ss.notification.Notify;

import java.sql.Date;

public class WechatNotification extends Notification {
    private int leadTime = 60;
    private Notification notification;

    private String flag = "Wechat not notified.";

    public String getFlag(){
        return flag;
    }

    public WechatNotification(Notification notification){
        this.notification = notification;

    }

    public void notify(Employee employee, Meeting meeting, String attend) {
        notification.notify(employee, meeting, attend);
        System.out.println("Wechat Notify: to " + employee.getName() + " " + employee.getWechat());
        System.out.println("level: " + attend);
        System.out.println("@room_" + meeting.getRoomId());
        System.out.println("date: " + new Date(meeting.getStart().getTime()));
        System.out.println("duration: " + meeting.getDuration());
        System.out.println("notified before: " + leadTime + "minutes");
        System.out.println(" ======= ");
        flag = "Wechat notified.";
    }
}
