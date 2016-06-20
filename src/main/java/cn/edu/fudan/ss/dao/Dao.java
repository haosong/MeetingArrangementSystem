package cn.edu.fudan.ss.dao;

import cn.edu.fudan.ss.bean.Employee;
import cn.edu.fudan.ss.bean.MeetingEmployee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://10.131.226.239:3306/sslab3?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false";

    private final static String dbUsername = "root";
    private final static String dbPassword = "123456";

    static {
        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static List<MeetingEmployee> queryMeetingEmployee(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sql);
        List<MeetingEmployee> meetingEmployees = new ArrayList<MeetingEmployee>();
        while (results.next()) {
            meetingEmployees.add(new MeetingEmployee(results.getString("employee"),
                    results.getInt("start"), results.getInt("end"), results.getInt("meetingId")));
        }
        statement.close();
        connection.close();
        results.close();
        return meetingEmployees;
    }

    public static int queryRecordsCount(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sql);
        int n = 0;
        while (results.next()) {
            n++;
        }
        statement.close();
        connection.close();
        results.close();
        return n;
    }


    public static void insert(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public static int findMeetingId(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sql);
        if (results.next()) {
            return results.getInt("id");
        }
        statement.close();
        connection.close();
        results.close();
        return 0;
    }

    public static Employee findEmployee(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(sql);
        if (results.next()) {
            return new Employee(results.getInt("id"), results.getString("name"),
                    results.getString("wechat"), results.getString("email"));
        }
        statement.close();
        connection.close();
        results.close();
        return null;
    }
}
