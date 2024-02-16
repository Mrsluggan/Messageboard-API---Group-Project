package org.egetapidb.user.service;

import org.egetapidb.developer.service.DeveloperService;
import org.egetapidb.user.model.User;


import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class UserService {

    @Inject
    EntityManager em;

    @Inject
    DeveloperService developerService;

    public List<User> findAll(UUID apiKey) {
        developerService.validateApiKey(apiKey);

        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return users;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public User create(User user, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        em.persist(user);
        return user;
    }

    public User findUser(Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }
        return user;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteUser(Long userId, UUID apiKey) {
        developerService.validateApiKey(apiKey);

        User user = em.find(User.class, userId);

        if (user == null) {
            throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
        }
        em.remove(user);
    }

    public Long countAllUsers(UUID apiKey) {
        developerService.validateApiKey(apiKey);

        return em.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changeUsername(Long userId, String newUser, UUID apiKey) {
        {
            developerService.validateApiKey(apiKey);

            User user = em.find(User.class, userId);

            if (user == null) {
                throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
            }

            user.setUsername(newUser);
            em.merge(user);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changeUserPassword(Long userId, String newUser, UUID apiKey) {
        {
            developerService.validateApiKey(apiKey);

            User user = em.find(User.class, userId);

            if (user == null) {
                throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
            }

            user.setUserPassword(newUser);
            em.merge(user);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void changeProfileImg(Long userId, String newUser, UUID apiKey) {
        {
            developerService.validateApiKey(apiKey);

            User user = em.find(User.class, userId);

            if (user == null) {
                throw new NotFoundException("Användaren med det angivna id:t hittades inte.");
            }

            user.setProfileImg(newUser);
            em.merge(user);
        }
    }
}
