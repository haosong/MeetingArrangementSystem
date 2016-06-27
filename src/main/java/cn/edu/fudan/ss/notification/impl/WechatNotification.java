package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
/**
 * Created by ddz on 16/6/25.
 */
public class WechatNotification extends Notification {
    private Notification notification;

    public WechatNotification(Notification notification){
        this.notification = notification;

    }

    public void notify(Employee employee){
        notification.notify(employee);
        System.out.println("Notify : " + employee.getWechat());
    }
}
