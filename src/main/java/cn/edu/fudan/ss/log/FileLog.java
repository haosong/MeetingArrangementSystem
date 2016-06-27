package cn.edu.fudan.ss.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by ddz on 16/6/25.
 */
public class FileLog extends Log{
    private BufferedWriter bw;

    public FileLog(String outputPath){
        try {
            bw = new BufferedWriter(new FileWriter(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String content) {
        Date date = new Date();
        log(content,date);
    }

    @Override
    public void log(String content, Date date) {
        try {
            bw.write(date.toString() + " : " + content);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finalize() throws Throwable{
        bw.close();
    }
}
