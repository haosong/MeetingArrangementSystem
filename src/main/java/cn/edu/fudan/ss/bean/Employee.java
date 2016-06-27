package cn.edu.fudan.ss.bean;

import org.json.JSONException;
import org.json.JSONObject;
import cn.edu.fudan.ss.observer.*;

public class Employee implements Subject{
    private int id;
    private String name;
    private String wechat;
    private String email;
    private Vector<Observer> vector = new Vector<Observer>();

    public Employee() {
    }

    public Employee(int id, String name, String wechat, String email) {
        this.id = id;
        this.name = name;
        this.wechat = wechat;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public void add(Observer observer) {
        vector.add(observer);
    }
    
    public void del(Observer observer) {
        vector.remove(observer);
    }
    
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }

    public JSONObject toJSONObject() {
        JSONObject user = new JSONObject();
        try {
            user.put("id", id);
            user.put("name", name);
            user.put("wechat", wechat);
            user.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
