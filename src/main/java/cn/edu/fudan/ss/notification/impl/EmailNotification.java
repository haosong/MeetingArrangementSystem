package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.notification.Notify;

public class EmailNotification implements Notify {
    public void notify(Employee employee) {
        System.out.println("Email Notify: " + employee.getName() + "(" + employee.getEmail() + ")");
    }
}
