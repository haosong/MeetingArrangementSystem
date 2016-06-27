package src;

import java.io.InputStream;
import java.util.Date;

/**
 * Created by ddz on 16/6/25.
 */
public abstract class Log {
    abstract void log(String content);
    abstract void log(String content, Date date);
}
