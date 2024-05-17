package com.javaclimb.music.service;

import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongWithSinger;

import java.util.List;

/**
 * 歌曲Service接口
 */

public interface SongService {
    /**
     * 增加
     */
    public boolean insert(Song song);
    /**
     * 修改
     */
    public boolean update(Song song);
    /**
     * 根据主键删除
     */
    public boolean delete(Integer id);
    /**
     * 根据主键查询整个对象
     */
    public SongWithSinger selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();
    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer id);
    /**
     * 根据歌名模糊查询
     */
    public List<Song> songOfName(String name);
}
