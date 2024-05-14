package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.LyricMapper;
import com.javaclimb.music.domain.Lyric;
import com.javaclimb.music.service.LyricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌词ServiceImpl
 */
@Service
public class LyricServiceImpl implements LyricService {

    @Autowired
    LyricMapper lyricMapper;

    /**
     * 上传歌词
     *
     * @param lyric
     */
    @Override
    public boolean insertLyric(Lyric lyric) {
        return lyricMapper.insertLyric(lyric)>0;
    }

    /**
     * 根据歌曲id删除歌词
     *
     * @param songId
     */
    @Override
    public boolean deleteLyric(Integer songId) {
        return lyricMapper.deleteLyric(songId)>0;
    }

    /**
     * 根据歌曲id修改歌词
     *
     * @param lyric
     */
    @Override
    public boolean updateLyric(Lyric lyric) {
        return lyricMapper.updateLyric(lyric)>0;
    }

    /**
     * 根据歌曲id返回歌词
     *
     * @param songId
     */
    @Override
    public List<Lyric> lyricsOfSong(Integer songId) {
        return lyricMapper.lyricsOfSong(songId);
    }
}
