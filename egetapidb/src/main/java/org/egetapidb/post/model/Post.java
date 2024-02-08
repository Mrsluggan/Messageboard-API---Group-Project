package org.egetapidb.post.model;

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
    private Long idPost;

    @NotEmpty(message = "Du måste ange en titel")
    @Size(min = 1, max = 100)
    private String title;
  
    @NotEmpty(message = "Du måste ange en text")
    @Size(min = 1, max = 500)
    private String text;
    private Long userId;

    public Post() {
    }

    public Post(String title, String text, long i) {
        this.title = title;
        this.text = text;
        this.idPost = i;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

}