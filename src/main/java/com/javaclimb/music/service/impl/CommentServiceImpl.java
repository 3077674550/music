package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.CommentMapper;
import com.javaclimb.music.domain.Comment;
import com.javaclimb.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insertSelective(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

    //    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> selectPageComment(Integer pageNum,Integer pageSize)
    {
        return commentMapper.selectPageComment(pageNum,pageSize);
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId)

    {
        return commentMapper.commentOfSongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId)

    {
        return commentMapper.commentOfSongListId(songListId);
    }
}
