package com.javaclimb.music.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 歌词
 */
public class Lyric implements Serializable {

    //主键
    private Integer id;

    //歌曲id
    private Integer songId;

    //时间戳
    private String timestamp;

    //歌词
    private String lyricText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLyricText() {
        return lyricText;
    }

    public void setLyricText(String lyricText) {
        this.lyricText = lyricText;
    }
}

