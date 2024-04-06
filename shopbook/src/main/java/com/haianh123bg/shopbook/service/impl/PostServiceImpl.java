package com.haianh123bg.shopbook.service.impl;

import com.haianh123bg.shopbook.entity.Post;
import com.haianh123bg.shopbook.exception.ResourceNotFoundException;
import com.haianh123bg.shopbook.payload.PostDTO;
import com.haianh123bg.shopbook.payload.PostResponse;
import com.haianh123bg.shopbook.repository.PostRepository;
import com.haianh123bg.shopbook.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Post post = maptoEntity(postDTO);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDTO postResponse = maptoDTO(newPost);
        return postResponse;
    }


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDTO> content = listOfPosts.stream().map(post -> maptoDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return maptoDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());

        Post updatePost = postRepository.save(post);
        return maptoDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDTO maptoDTO(Post post){
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
//        PostDTO postDTO = new PostDTO();
//        postDTO.setId(post.getId());
//        postDTO.setContent(post.getContent());
//        postDTO.setDescription(post.getDescription());
//        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
    private Post maptoEntity(PostDTO postDTO){
        Post post = modelMapper.map(postDTO, Post.class);
//        Post post = new Post();
//        post.setContent(postDTO.getContent());
//        post.setDescription(postDTO.getDescription());
//        post.setTitle(postDTO.getTitle());
        return post;
    }
}
