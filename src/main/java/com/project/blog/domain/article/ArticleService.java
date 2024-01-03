package com.project.blog.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;


    public List<Article> getList () {
        return this.articleRepository.findAll();
    }

    public void create(String title, String content, MultipartFile thumbnail) {
        String thumbnailRelPath = "article/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

        Article article = Article.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailRelPath)
                .build();
        this.articleRepository.save(article);

    }
}
