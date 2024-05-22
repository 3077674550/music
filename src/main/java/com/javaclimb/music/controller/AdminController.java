package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.service.AdminService;
import com.javaclimb.music.utils.Consts;
import com.javaclimb.music.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    //    判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean res= adminService.verifyPassword(name, password);
        if (res) {
            Map<String,Object> claims=new HashMap<>();
            claims.put("name",name);
            claims.put("password",password);
            String Jwt= JwtUtils.generateJWT(claims);

            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            jsonObject.put(Consts.DATA,Jwt);
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名或密码错误");
            return jsonObject;
        }
    }
}

