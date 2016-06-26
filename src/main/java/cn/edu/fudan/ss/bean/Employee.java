package cn.edu.fudan.ss.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Employee {
    private int id;
    private String name;
    private String wechat;
    private String email;

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
