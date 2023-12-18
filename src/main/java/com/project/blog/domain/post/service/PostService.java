package com.project.blog.domain.post.service;

import com.project.blog.domain.post.entity.Post;
import com.project.blog.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getList () {
        return this.postRepository.findAll();
    }

    public void create (String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();
        this.postRepository.save(post);
    }

}
