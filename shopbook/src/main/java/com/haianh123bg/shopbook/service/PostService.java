package com.haianh123bg.shopbook.service;

import com.haianh123bg.shopbook.payload.PostDTO;
import com.haianh123bg.shopbook.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePostById(long id);

}
