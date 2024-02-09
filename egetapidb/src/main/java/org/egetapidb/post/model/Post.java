package org.egetapidb.post.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long postId;

    @NotEmpty(message = "Du måste ange en titel")
    @Size(min = 1, max = 100)
    private String title;

    @NotEmpty(message = "Du måste ange en text")
    @Size(min = 1, max = 500)
    private String text;
    private Long userId;

    private int likes;
    private int dislikes;

    private Set<Long> whoLiked = new HashSet<>();
    private Set<Long> whoDisliked = new HashSet<>();

    public void increaseLikes() {
        this.likes++;

    }

    public void increaseDislikes() {
        this.dislikes++;

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getWhoLiked() {
        return whoLiked;
    }

    public void setWhoLiked(Set<Long> whoLiked) {
        this.whoLiked = whoLiked;
    }

    public Set<Long> getWhoDisliked() {
        return whoDisliked;
    }

    public void setWhoDisliked(Set<Long> whoDisliked) {
        this.whoDisliked = whoDisliked;
    }

}