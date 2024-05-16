package com.javaclimb.music.service;

import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongList;

import java.util.List;

public interface ListSongService {
    /**
     * 在歌单中添加歌曲
     */
    boolean insert(ListSong record);

    /**
     * 根据主键更新
     */
    boolean update(ListSong record);

    /**
     * 根据主键删除
     */
    boolean deleteSong(ListSong record);

    /**
     * 根据主键查找
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有对应关系
     */
    List<ListSong> allListSong();

    /**
     * 根据歌曲id查找
     */

    List<Song> selectSongBySongListId(Integer songListId);


}
