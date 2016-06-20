package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.notification.Notify;

public class WechatNotification implements Notify {
    public void notify(Employee employee) {
        System.out.println("Wechat Notify: " + employee.getName() + "(" + employee.getWechat() + ")");
    }
}
