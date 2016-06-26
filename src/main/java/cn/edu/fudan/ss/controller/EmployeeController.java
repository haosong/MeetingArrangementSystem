package cn.edu.fudan.ss.controller;

import cn.edu.fudan.ss.bean.Employee;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Path("employee")
public class EmployeeController {

    private static Map<String, Employee> userMap;

    static {
        userMap = new HashMap<String, Employee>();
    }

    @GET
    @Path("/signup")
    @Produces({MediaType.APPLICATION_JSON})
    public String signup(@Context HttpServletRequest httpServletRequest,
            @QueryParam("account") String account,
            @QueryParam("name") String name,
            @QueryParam("password") String password) throws JSONException {
        JSONObject response = new JSONObject();
        if (userMap.containsKey(account)) {
            response.put("status", "用户名重复");
        } else {
            //userMap.put(account, new Employee(name, account, password));
            response.put("status", "注册成功");
        }
        return response.toString();
    }

    @GET
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public String login(@Context HttpServletRequest httpServletRequest,
             @QueryParam("account") String account,
             @QueryParam("password") String password) throws JSONException {
        JSONObject response = new JSONObject();
        /*
        if (userMap.containsKey(account)
                && userMap.get(account).getPassword().equals(password)) {
            response.put("status", "登录成功");
            response.put("user", userMap.get(account).toJSONObject());
        } else {
            response.put("status", "用户名或密码错误");
        }
        */
        return response.toString();
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public String list(@Context HttpServletRequest httpServletRequest) throws JSONException {
        JSONArray response = new JSONArray();
        Iterator iter = userMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            response.put(((Employee) entry.getValue()).toJSONObject());
        }
        return response.toString();
    }
}
