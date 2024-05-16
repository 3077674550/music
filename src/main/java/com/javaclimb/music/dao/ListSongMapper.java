package com.javaclimb.music.dao;

import com.javaclimb.music.domain.ListSong;
import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper {
    /**
     * 在歌单中添加歌曲
     */
    int insert(ListSong record);

    /**
     * 根据主键更新
     */
    int update(ListSong record);

    /**
     * 根据歌曲id删除歌曲
     */
    int deleteSong(ListSong record);

    /**
     * 根据主键查找
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询歌单里所有歌曲
     */
    List<ListSong> allListSong();

    /**
     * 根据歌单id查找歌单里的歌曲
     */
    List<Song> selectSongBySongListId(Integer songListId);

}
