package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.service.SongService;
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
import java.util.Date;

/**
 * 歌曲管理Controller
 */
@RestController
@RequestMapping(value = "/song")
public class SongController {

    @Autowired
    private SongService songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:/Users/16921/Desktop/music/img/songPic/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:/Users/16921/Desktop/music/song/");
        }
    }

    /**
     * 添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        String singer_id = request.getParameter("singerId").trim();//歌手id
        String name = request.getParameter("name").trim();//歌名
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/example.jpg";

        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"音乐上传失败");
            return jsonObject;
        }
        String fileName = mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/"+fileName;
        //保存歌曲的到对象中
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.valueOf(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put("avator", storeUrlPath);
                jsonObject.put(Consts.MSG,"音乐上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"音乐上传失败");
            return jsonObject;
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败" + e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    /**
     * 更新歌曲
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String singer_id = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();

        Song song = new Song();
        song.setId(Integer.valueOf(id));
        song.setSingerId(Integer.valueOf(singer_id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());

        boolean flag = songService.update(song);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"更新成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"更新失败");
        return jsonObject;
    }

    /**
     * 根据主键删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return songService.delete(Integer.valueOf(id));
    }

    /**
     * 根据主键查询歌曲
     */
    @RequestMapping(value = "/select/id",method = RequestMethod.GET)
    public ResponseEntity<Object> selectById(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        if(songService.selectByPrimaryKey(Integer.valueOf(id))!=null){
            return ResponseEntity.ok(songService.selectByPrimaryKey(Integer.valueOf(id)));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"找不到歌曲");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 查询所有歌曲
     */
    @RequestMapping(value = "/selectPageSong",method = RequestMethod.GET)
    public Object selectPageSong(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return songService.selectPageSong((pageNum-1)*pageSize,pageSize);
    }

    /**
     * 根据歌手id查询
     */
    @RequestMapping(value = "/select/singerId",method = RequestMethod.GET)
    public ResponseEntity<Object> selectBySinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String singer_id = request.getParameter("singerId");
        if(!songService.songOfSingerId(Integer.valueOf(singer_id)).isEmpty()){
            return ResponseEntity.ok(songService.songOfSingerId(Integer.valueOf(singer_id)));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"找不到歌曲");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 根据歌名模糊查询
     */
    @RequestMapping(value = "/select/name",method = RequestMethod.GET)
    public ResponseEntity<Object> selectByName(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        if(!songService.songOfName("%"+name+"%").isEmpty()){
            return ResponseEntity.ok(songService.songOfName("%"+name+"%"));
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"找不到歌曲");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
    }

    /**
     * 更新歌曲图片
     */
    @RequestMapping(value = "update/songPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("pic") MultipartFile pic,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();

        if(pic.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传图片失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+pic.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        String urlStore = "/img/songPic/"+fileName;
        try {
            pic.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(urlStore);
            boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"更新图片成功");
                jsonObject.put("avator",urlStore);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"更新图片失败");
            return jsonObject;
        }catch (IOException e){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"更新图片失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
