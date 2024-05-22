package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.SongMapper;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongWithSinger;
import com.javaclimb.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public SongWithSinger selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询歌曲
     */
    @Override
    public List<Song> selectPageSong(Integer pageNum,Integer pageSize) {
        return songMapper.selectPageSong(pageNum,pageSize);
    }

    /**
     * 根据歌手id查询
     *
     * @param id
     */
    @Override
    public List<Song> songOfSingerId(Integer id) {
        return songMapper.songOfSingerId(id);
    }

    /**
     * 根据歌名模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }
}
