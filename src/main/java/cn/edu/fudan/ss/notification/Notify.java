package cn.edu.fudan.ss.notification;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.Meeting;

import java.sql.Timestamp;

public interface Notify {
    public void notify(Employee employee, Meeting meeting, String attend);
}
