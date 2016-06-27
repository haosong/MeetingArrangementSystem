package cn.edu.fudan.ss.notification.impl;

import cn.edu.fudan.ss.bean.Employee;

/**
 * Created by ddz on 16/6/25.
 */
public class Notification implements Notify {
    private static Log log;

    static{
        LogFactory factory = new ConsoleLogFactory();
        log = factory.Create();
    }

    @Override
    public void notify(Employee employee) {
        log.log("Notify : " + employee.getName());

    }
}
