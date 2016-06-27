package cn.edu.fudan.ss.log;

import java.io.InputStream;
import java.util.Date;

/**
 * Created by ddz on 16/6/25.
 */
public abstract class Log {
    public abstract void log(String content);
    public abstract void log(String content, Date date);
}
