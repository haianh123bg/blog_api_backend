package com.haianh123bg.shopbook.repository;

import com.haianh123bg.shopbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long id);
}
