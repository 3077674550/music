package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Lyric;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌词dao
 */
@Repository
public interface LyricMapper {

    /**
     * 上传歌词
     */
    int insertLyric(Lyric lyric);

    /**
     * 根据主键id删除歌词
     */
    int deleteLyric(Integer id);

    /**
     * 根据歌曲id修改歌词
     */
    int updateLyric(Lyric lyric);

    /**
     * 根据歌曲id返回歌词
     */
    List<Lyric> lyricsOfSong(Integer songId);
}
