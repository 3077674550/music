package com.javaclimb.music.service;

import com.javaclimb.music.domain.Lyric;

import java.util.List;

/**
 * 歌词Service
 */

public interface LyricService {

    /**
     * 上传歌词
     */
    boolean insertLyric(Lyric lyric);

    /**
     * 根据歌曲id删除歌词
     */
    boolean deleteLyric(Integer id);

    /**
     * 根据歌曲id修改歌词
     */
    boolean updateLyric(Lyric lyric);

    /**
     * 根据歌曲id返回歌词
     */
    List<Lyric> lyricsOfSong(Integer songId);
}
