package com.javaclimb.music.service;

import com.javaclimb.music.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> selectPageComment(Integer pageNum,Integer pageSize);

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);

}
