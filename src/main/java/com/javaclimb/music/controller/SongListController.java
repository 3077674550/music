package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.SongList;
import com.javaclimb.music.service.SongListService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * 歌单控制类
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:/Users/16921/Desktop/music/img/songListPic/");
        }
    }

    /**
     * 添加歌单
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();//歌单标题
        String pic = "/img/songPic/example.jpg";
        String introduction = request.getParameter("introduction").trim();//简介
        String style = request.getParameter("style").trim();//风格

        //保存到歌单的对象里
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();//主键
        String title = request.getParameter("title").trim();//歌单标题
        String introduction = request.getParameter("introduction").trim();//简介
        String style = request.getParameter("style").trim();//风格
        //保存到歌单的对象里
        SongList songList = new SongList();
        songList.setId(Integer.valueOf(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.update(songList);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 删除歌单
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();//主键
        boolean flag = songListService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public ResponseEntity<Object> selectByPrimaryKey(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        if(songListService.selectByPrimaryKey(Integer.parseInt(id))!=null){
            return ResponseEntity.ok(songListService.selectByPrimaryKey(Integer.parseInt(id)));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查无该对象");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }

    }

    /**
     * 查询所有歌单
     */
    @RequestMapping(value = "/selectPageSongList",method = RequestMethod.GET)
    public Object selectPageSongList(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return songListService.selectPageSongList((pageNum-1)*pageSize,pageSize);
    }

    /**
     * 根据歌单标题模糊查询列表
     */
    @RequestMapping(value = "/likeTitle",method = RequestMethod.GET)
    public ResponseEntity<Object> likeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        JSONObject jsonObject = new JSONObject();
        if(!songListService.likeTitle("%"+title+"%").isEmpty()){
            return ResponseEntity.ok(songListService.likeTitle("%"+title+"%"));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查无该对象");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 根据歌单名字精确查询
     */
    @RequestMapping(value = "/songListOfName",method = RequestMethod.GET)
    public ResponseEntity<Object> songListOfName(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        JSONObject jsonObject = new JSONObject();
        if(songListService.songListOfName(title).isEmpty()){
            return ResponseEntity.ok(songListService.songListOfName(title));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查无该对象");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 根据歌单风格模糊查询
     */
    @RequestMapping(value = "/likeStyle",method = RequestMethod.GET)
    public ResponseEntity<Object> likeStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();
        JSONObject jsonObject = new JSONObject();
        if(!songListService.likeStyle(style).isEmpty()){
            return ResponseEntity.ok(songListService.likeStyle(style));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查无该对象");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 更改歌单图片
     */
    @RequestMapping(value = "/update/songListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file")MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + FileSystems.getDefault().getSeparator() +"img"
                + FileSystems.getDefault().getSeparator() +"songListPic";
        //如果文件路径不存在,新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath + FileSystems.getDefault().getSeparator() + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
}
