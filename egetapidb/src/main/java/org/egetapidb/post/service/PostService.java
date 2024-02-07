package org.egetapidb.post.service;

import java.util.List;

import org.egetapidb.post.model.Post;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class PostService {

    @Inject
    EntityManager em;

    public List<Post> findAll() {
        List<Post> posts = em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
        return posts;

    }

    public Post findPost(Long id) {
        return em.find(Post.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post createPost(Post post) {
        em.persist(post);
        return post;
    }

    public Long countAllPosts() {
        return em.createQuery("SELECT COUNT(p) FROM Post p", Long.class).getSingleResult();
    }

}
