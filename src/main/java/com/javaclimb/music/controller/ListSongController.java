package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.service.impl.ListSongServiceImpl;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/listSong")
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongServiceImpl;

    /**
     * 在歌单中添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String song_id = request.getParameter("songId").trim();
        String song_list_id = request.getParameter("songListId").trim();

        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.valueOf(song_id));
        listSong.setSongListId(Integer.valueOf(song_list_id));
        boolean flag = listSongServiceImpl.insert(listSong);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 根据主键更新歌单信息
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String song_id = request.getParameter("songId").trim();
        String song_list_id = request.getParameter("songListId").trim();

        ListSong listSong = new ListSong();
        listSong.setId(Integer.valueOf(id));
        listSong.setSongId(Integer.valueOf(song_id));
        listSong.setSongListId(Integer.valueOf(song_list_id));

        boolean flag = listSongServiceImpl.update(listSong);
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
     * 根据歌曲id删除歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest request){
        String song_id = request.getParameter("songId").trim();
        String song_list_id = request.getParameter(("songListId")).trim();

        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.valueOf(song_id));
        listSong.setSongListId(Integer.valueOf(song_list_id));

        return listSongServiceImpl.deleteSong(listSong);
    }

    /**
     * 根据主键查找
     */
    @RequestMapping(value = "/select/id",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return listSongServiceImpl.selectByPrimaryKey(Integer.valueOf(id));
    }

    /**
     * 根据歌单id查找歌单
     */
    @RequestMapping(value = "/select/songListId",method = RequestMethod.GET)
    public Object selectBySongListId(HttpServletRequest request){
        String song_list_id = request.getParameter("songListId").trim();
        return listSongServiceImpl.selectBySongListId(Integer.valueOf(song_list_id));
    }

    /**
     * 查询所有listSong
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Object allListSong(){
        return listSongServiceImpl.allListSong();
    }

}
