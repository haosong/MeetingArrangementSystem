package cn.edu.fudan.ss.log;

import java.util.Date;

/**
 * Created by ddz on 16/6/25.
 */
public class FileLogFactory extends LogFactory {
    private String filename;


    public FileLogFactory(String filename){
        this.filename = filename;
    }

    public FileLogFactory(){
//        this(new Date().toString() + ".log");
        this(System.currentTimeMillis() + ".log");
    }


    @Override
    public Log Create() {
        return new FileLog(filename);
    }
}
