package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Singer;
import com.javaclimb.music.service.SingerService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    //添加歌手
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();//歌手标题
        String sex = request.getParameter("sex").trim();//歌手性别
        String pic = request.getParameter("pic").trim();//歌手图片
        String birth = request.getParameter("birthday").trim();//出生日期
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介

        byte b=Byte.parseByte(sex);//将sex强转为byte类型

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //保存到歌手的对象里
        Singer singer = new Singer();

        singer.setName(name);
        singer.setSex(b);
        singer.setPic(pic);
        singer.setBirthday(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        boolean flag = singerService.insert(singer);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    //修改歌手
    @PostMapping("/update")
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();//主键
        String name = request.getParameter("name").trim();//歌手标题
        String sex = request.getParameter("sex").trim();//歌手性别
        String pic = request.getParameter("pic").trim();//歌手图片
        String birth = request.getParameter("birthday").trim();//出生日期
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介

        byte b=Byte.parseByte(sex);//将sex强转为byte类型

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //保存到歌手的对象里
        Singer singer = new Singer();

        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(b);
        singer.setPic(pic);
        singer.setBirthday(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        boolean flag = singerService.update(singer);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    //删除歌手
    @GetMapping("/delete")
    public Object deleteSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id=request.getParameter("id").trim();//主键

        boolean flag = singerService.delete(Integer.parseInt(id));

        return flag;
    }

    //根据主键查询歌手
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id=request.getParameter("id").trim();//主键
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    //查询所有歌手
    @GetMapping("/allSinger")
    public Object allSinger(HttpServletRequest request){
        return singerService.allSinger();
    }

    //根据歌手名字模糊查询歌手
    @GetMapping("/singerOfName")
    public Object singerOfName(HttpServletRequest request){
        String name=request.getParameter("name").trim();
        return singerService.SingerOfName("%"+name+"%");
    }

    //根据性别模糊查询歌手
    @GetMapping("/singerOfSex")
    public Object singerOfSex(HttpServletRequest request){
        String sex=request.getParameter("sex").trim();
        return singerService.SingerOfSex(Integer.parseInt(sex));
    }
}
