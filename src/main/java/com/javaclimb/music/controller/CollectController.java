package com.javaclimb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.music.domain.Collect;
import com.javaclimb.music.service.CollectService;
import com.javaclimb.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController

public class CollectController {

    @Autowired
    private CollectService collectService;

    //    添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_id=req.getParameter("songId");
        String song_list_id=req.getParameter("songListId");
        if (song_id == ""){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "收藏歌曲为空");
            return jsonObject;
        } else if (collectService.existSongId(Integer.parseInt(user_id), Integer.parseInt(song_id))) {
            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MSG, "已收藏");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(user_id));

        byte b=Byte.parseByte(type);//将type强转为byte类型
        collect.setType(b);

        if (b == 0) {
            collect.setSongId(Integer.parseInt(song_id));
        } else if (b == 1) {
            collect.setSongListId(Integer.parseInt(song_list_id));
        }
        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if (res){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "收藏成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "收藏失败");
            return jsonObject;
        }
    }

    //    返回所有用户收藏列表
    @RequestMapping(value = "/selectPageCollection", method = RequestMethod.GET)
    public Object selectPageCollection(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return collectService.selectPageCollect((pageNum-1)*pageSize,pageSize);
    }

    //    返回的指定用户ID收藏列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest req){
        String userId = req.getParameter("userId");
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

    //    删除收藏的歌曲
    @RequestMapping(value = "/collection/delete", method = RequestMethod.GET)
    public Object deleteCollection(HttpServletRequest req){
        String user_id = req.getParameter("userId").trim();
        String song_id = req.getParameter("songId").trim();
        return collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(song_id));
    }

    //    更新收藏
    @ResponseBody
    @RequestMapping(value = "/collection/update", method = RequestMethod.POST)
    public Object updateCollectMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String song_id = req.getParameter("songId").trim();
//        String song_list_id = req.getParameter("songListId").trim();

        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        byte b=Byte.parseByte(type);//将type强转为byte类型
        collect.setType(b);
        collect.setSongId(Integer.parseInt(song_id));

        boolean res = collectService.updateCollectMsg(collect);
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
}

