package com.prod_ready_features.services;


import com.prod_ready_features.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService  {
    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPosts);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto inputPost, Long postId);
}
