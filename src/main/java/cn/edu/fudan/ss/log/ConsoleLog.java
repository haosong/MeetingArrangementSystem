package cn.edu.fudan.ss.log;

import java.util.Date;

/**
 * Created by ddz on 16/6/25.
 */
public class ConsoleLog extends Log{

    @Override
    void log(String content) {
        Date date = new Date();
        log(content,date);
    }

    @Override
    void log(String content, Date date) {
        System.out.println(date.toString() + " : " + content);
    }
}
