package org.egetapidb.post.service;

import java.util.List;
import java.util.UUID;

import org.egetapidb.developer.service.DeveloperService;
import org.egetapidb.post.model.Post;
import org.egetapidb.user.model.User;

import io.quarkus.security.UnauthorizedException;
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

    @Inject
    DeveloperService developerService;

    public List<Post> findAll(UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
        List<Post> posts = em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
        return posts;
    }

    public Post findPost(Long id, UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
        return em.find(Post.class, id);
    }

    public List<Post> findPostbyUser(Long userId, UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }

        jakarta.persistence.TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE p.userId = :userId",
                Post.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post createPost(Post post, Long userId, UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
        User user = em.find(User.class, userId);
        post.setUserId(user.getId());
        em.persist(post);
        return post;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deletePost(Long userId, Long id, UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
        User user = em.find(User.class, userId);
        if (user.getId().equals(userId)) {
            em.remove(em.getReference(Post.class, id));
        }

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changePost(Long userId, Long postId, Post newPost) {
        Post post = em.find(Post.class, postId);
        User user = em.find(User.class, userId);
        if (user.getId().equals(userId)) {
            post.setTitle(newPost.getTitle());
            post.setText(newPost.getText());
            em.merge(post);
        } 
        }

    public Long countAllPosts(UUID apiKey) {
        if (!developerService.isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
        return em.createQuery("SELECT COUNT(p) FROM Post p", Long.class).getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post updateLike(Post post, Long id) {
        post.increaseLikes();
        em.merge(post);
        return post;

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Post updateDislike(Post post, Long id) {
        post.increaseDislikes();
        em.merge(post);
        return post;

    }

}
