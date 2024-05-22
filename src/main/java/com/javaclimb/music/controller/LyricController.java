package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Lyric;
import com.javaclimb.music.service.impl.LyricServiceImpl;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 歌词Controller
 */
@RestController
@RequestMapping(value = "/lyric")
public class LyricController {

    @Autowired
    LyricServiceImpl lyricServiceImpl;

    /**
     * 添加歌词
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addLyric(HttpServletRequest request, @RequestParam("lrc")MultipartFile lrc){
        JSONObject jsonObject = new JSONObject();

        String song_id = request.getParameter("songId").trim();
        List<String> timestamp = new ArrayList<>();
        List<String> lyricText = new ArrayList<>();

        if(lrc.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词文件上传失败");
            return jsonObject;
        }

        String fileName = lrc.getOriginalFilename();//文件名
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "lyric";//文件夹
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        try {
            lrc.transferTo(dest);
            BufferedReader br = new BufferedReader(new FileReader(dest));
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("]");
                if (parts.length == 2) {
                    String time = parts[0].substring(1); // 去除时间戳中的"["
                    String lyric = parts[1].trim(); // 去除歌词前后的空格
                    timestamp.add(time);
                    lyricText.add(lyric);
                }
            }

            String timestamp1 = String.join("_",timestamp);
            String lyricText1 = String.join("_",lyricText);

            Lyric lyric = new Lyric();
            lyric.setSongId(Integer.valueOf(song_id));
            lyric.setTimestamp(timestamp1);
            lyric.setLyricText(lyricText1);

            boolean flag = lyricServiceImpl.insertLyric(lyric);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"歌词上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词上传失败"+e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 根据主键id删除歌词
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteLyric(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return lyricServiceImpl.deleteLyric(Integer.valueOf(id));
    }

    /**
     * 根据主键id修改歌词
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateLyric(HttpServletRequest request, @RequestParam("lrc")MultipartFile lrc){
        JSONObject jsonObject = new JSONObject();

        String id = request.getParameter("id").trim();
        String song_id = request.getParameter("songId").trim();
        List<String> timestamp = new ArrayList<>();
        List<String> lyricText = new ArrayList<>();

        if(lrc.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词文件上传失败");
            return jsonObject;
        }

        String fileName = lrc.getOriginalFilename();//文件名
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "lyric";//文件夹
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        try {
            lrc.transferTo(dest);
            BufferedReader br = new BufferedReader(new FileReader(dest));
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("]");
                if (parts.length == 2) {
                    String time = parts[0].substring(1); // 去除时间戳中的"["
                    String lyric = parts[1].trim(); // 去除歌词前后的空格
                    timestamp.add(time);
                    lyricText.add(lyric);
                }
            }

            String timestamp1 = String.join("_",timestamp);
            String lyricText1 = String.join("_",lyricText);

            Lyric lyric = new Lyric();
            lyric.setId(Integer.valueOf(id));
            lyric.setSongId(Integer.valueOf(song_id));
            lyric.setTimestamp(timestamp1);
            lyric.setLyricText(lyricText1);

            boolean flag = lyricServiceImpl.updateLyric(lyric);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"歌词修改成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词修改失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌词修改失败"+e.getMessage());
            return jsonObject;
        }
    }

    /**
     * 根据歌曲id返回歌词
     */
    @RequestMapping(value = "/songLyric",method = RequestMethod.GET)
    public ResponseEntity<Object> lyricsOfSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String song_id = request.getParameter("songId").trim();

        List<Lyric> lyric = lyricServiceImpl.lyricsOfSong(Integer.valueOf(song_id));
        if(lyric.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"找不到歌词");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
        }
        int count = 1;
        for(Lyric lrc : lyric){
            String time = lrc.getTimestamp();
            String text = lrc.getLyricText();

            String[] timestamp = time.split("_");
            String[] lyricText = text.split("_");
            String cplLyric = "";

            for (int i = 0; i < timestamp.length; i++) {
                cplLyric = cplLyric + "["+timestamp[i]+"]"+lyricText[i]+"\n";
            }
            jsonObject.put("lyric"+count,cplLyric);
            count++;
        }
        return ResponseEntity.ok(jsonObject);
    }
}
