package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.ListSongMapper;
import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongList;
import com.javaclimb.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 在歌单中添加歌曲
     *
     * @param record
     */
    @Override
    public boolean insert(ListSong record) {
        return listSongMapper.insert(record) > 0;
    }

    /**
     * 根据主键更新
     *
     * @param record
     */
    @Override
    public boolean update(ListSong record) {
        return listSongMapper.update(record) > 0;
    }

    /**
     * 根据歌曲id删除歌曲
     *
     * @param record
     */
    @Override
    public boolean deleteSong(ListSong record) {
        return listSongMapper.deleteSong(record) > 0;
    }

    /**
     * 根据主键查找
     *
     * @param id
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有对应关系
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }


    /**
     * 根据歌曲id查找
     *
     * @param songListId
     */
    @Override
    public List<Song> selectSongBySongListId(Integer songListId) {
        return listSongMapper.selectSongBySongListId(songListId);
    }

}
