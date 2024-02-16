package org.egetapidb.post.service;

import java.util.List;
import java.util.UUID;

import org.egetapidb.developer.service.DeveloperService;
import org.egetapidb.post.model.Post;
import org.egetapidb.user.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class PostService {

    @Inject
    EntityManager em;

    @Inject
    DeveloperService developerService;


    public List<Post> findAll(UUID apiKey) {
        developerService.validateApiKey(apiKey);
        List<Post> posts = em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
        return posts;
    }

    public Post findPost(Long postId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        Post post = em.find(Post.class, postId);

        if (post == null) {
            throw new NotFoundException("Inlägget med det angivna id:t hittades inte.");
        }
        return post;
    }

    public List<Post> findPostbyUser(Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        jakarta.persistence.TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE p.userId = :userId",
                Post.class);
        query.setParameter("userId", userId);

        List<Post> posts = query.getResultList();

        return posts;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post createPost(Post post, Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);
       
        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        post.setUserId(user.getUserId());
        em.persist(post);
        return post;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deletePost(Long userId, Long postId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        Post post = em.find(Post.class, postId);

        if (post == null || !post.getUserId().equals(userId)) {
            throw new NotFoundException("Inlägget med det angivna id:t hittades inte för den angivna användaren.");
        }

        em.remove(post);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changePost(Long userId, Long postId, Post newPost, UUID apiKey) {
        developerService.validateApiKey(apiKey);
        
        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        Post post = em.find(Post.class, postId);

        if (post == null || !post.getUserId().equals(userId)) {
            throw new NotFoundException("Inlägget med det angivna id:t hittades inte för den angivna användaren.");
        }

        post.setTitle(newPost.getTitle());
        post.setText(newPost.getText());
        em.merge(post);
    }

    public Long countAllPosts(UUID apiKey) {
        developerService.validateApiKey(apiKey);

        return em.createQuery("SELECT COUNT(p) FROM Post p", Long.class).getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post updateLike(Post post, Long postId, Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        post.getWhoLiked().add(userId);
        post.increaseLikes();
        em.merge(post);
        return post;

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post updateDislike(Post post, Long postId, Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }

        post.getWhoDisliked().add(userId);
        post.increaseDislikes();
        em.merge(post);
        return post;
    }
}
