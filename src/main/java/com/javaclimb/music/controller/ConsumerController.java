package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Consumer;
import com.javaclimb.music.service.ConsumerService;
import com.javaclimb.music.utils.Consts;
import com.javaclimb.music.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/avatorImages/**").addResourceLocations("file:/Users/16921/Desktop/music/img/avatorImages/");
        }
    }

    //    添加用户
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birthday").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        String avator = req.getParameter("avator").trim();

        if (username.equals("") || username == null){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名或密码错误");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e){
            e.printStackTrace();
        }
        consumer.setUsername(username);
        consumer.setPassword(password);

        byte b=Byte.parseByte(sex);//将sex强转为byte类型
        consumer.setSex(b);
        if (phone_num == "") {
            consumer.setPhoneNum(null);
        } else{
            consumer.setPhoneNum(phone_num);
        }

        if (email == "") {
            consumer.setEmail(null);
        } else{
            consumer.setEmail(email);
        }
        consumer.setBirthday(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.insert(consumer);
        if (res) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "注册成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "注册失败");
            return jsonObject;
        }
    }

    //    判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/user/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        //System.out.println(username+"  "+password);
        boolean res = consumerService.vertifyPassword(username, password);

        if (res){
            Map<String,Object> claims=new HashMap<>();
            claims.put("username",username);
            claims.put("password",password);
            String Jwt= JwtUtils.generateJWT(claims);

            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            jsonObject.put(Consts.DATA,Jwt);
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名或密码错误");
            return jsonObject;
        }

    }

    //    返回所有用户
    @RequestMapping(value = "/selectPageUser", method = RequestMethod.GET)
    public Object selectPageUser(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return consumerService.selectPageConsumer((pageNum-1)*pageSize,pageSize);
    }

    //    返回指定ID的用户
    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public Object userOfId(HttpServletRequest req){
        String id = req.getParameter("id");
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //    删除用户
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest req){
        String id = req.getParameter("id");
        return consumerService.delete(Integer.parseInt(id));
    }

    //    更新用户信息
    @ResponseBody
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Object updateUserMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phone_num = req.getParameter("phone_num").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birthday").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        //String avator = req.getParameter("avator").trim();
//        System.out.println(username+"  "+password+"  "+sex+"   "+phone_num+"     "+email+"      "+birth+"       "+introduction+"      "+location);

        if (username.equals("") || username == null){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名或密码错误");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        byte b=Byte.parseByte(sex);//将sex强转为byte类型
        consumer.setSex(b);
        consumer.setPhoneNum(phone_num);
        consumer.setEmail(email);
        consumer.setBirthday(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        //consumer.setAvator(avator);
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.update(consumer);
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }
    }

    //    更新用户头像
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean res = consumerService.update(consumer);
            if (res){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put(Consts.MSG, "上传成功");
                return jsonObject;
            }else {
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "上传失败");
                return jsonObject;
            }
        }catch (IOException e){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
