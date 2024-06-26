package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.SongListMapper;
import com.javaclimb.music.domain.SongList;
import com.javaclimb.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList)>0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList)>0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id)>0;
    }

    /**
     * 根据主键查询对象
     *
     * @param id
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单
     *
     */
    @Override
    public List<SongList> selectPageSongList(Integer pageNum,Integer pageSize) {
        return songListMapper.selectPageSongList(pageNum,pageSize);
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title
     */
    @Override
    public List<SongList> songListOfName(String title) {
        return songListMapper.songListOfName(title);
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.likeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.likeStyle(style);
    }
}
