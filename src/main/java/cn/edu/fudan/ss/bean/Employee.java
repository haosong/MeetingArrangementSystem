package cn.edu.fudan.ss.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Employee {
    private int id;
    private String name;
    private String account;
    private String password;

    public Employee() {
    }

    public Employee(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public JSONObject toJSONObject() {
        JSONObject user = new JSONObject();
        try {
            user.put("id", id);
            user.put("name", name);
            user.put("account", account);
            user.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
