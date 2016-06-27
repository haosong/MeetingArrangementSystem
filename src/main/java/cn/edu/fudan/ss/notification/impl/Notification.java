package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.Meeting;
import cn.edu.fudan.ss.log.*;
import cn.edu.fudan.ss.notification.*;

/**
 * Created by ddz on 16/6/25.
 */
public class Notification implements Notify {
    private static Log log;

    static{
        LogFactory factory = new ConsoleLogFactory();
        log = factory.Create();
    }

    public void notify(Employee employee, Meeting meeting, String attend) {
        log.log("Notify : " + employee.getName());
    }
}
