package com.project.blog.domain.post.entity;

import com.project.blog.domain.member.entity.Member;
import com.project.blog.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post extends BaseEntity {
    private String title;
    private  String content;
    @ManyToOne
    private Member author;
}
