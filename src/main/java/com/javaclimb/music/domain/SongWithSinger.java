package com.javaclimb.music.domain;

public class SongWithSinger extends Song{
    //账号
    private Singer singer;

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
