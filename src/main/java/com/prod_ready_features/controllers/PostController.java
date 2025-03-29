package com.prod_ready_features.controllers;


import com.prod_ready_features.dto.PostDto;
import com.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;
    @GetMapping
    public List<PostDto> getAllPosts(){
        return  postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost){
        return  postService.createNewPost(inputPost);
    }

    @PutMapping("{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPost, @PathVariable Long postId){

        return  postService.updatePost(inputPost,postId);
    }


}
