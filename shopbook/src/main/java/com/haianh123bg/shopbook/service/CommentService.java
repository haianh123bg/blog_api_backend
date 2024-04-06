package com.haianh123bg.shopbook.service;

import com.haianh123bg.shopbook.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);
    List<CommentDTO> getCommentByPostId(long postId);

    CommentDTO getCommentById(Long postId,Long commentId);

    CommentDTO updateComment(Long postId, long commentId, CommentDTO commentRequest);

    void deleteComment(Long postId, Long commentId);
}
