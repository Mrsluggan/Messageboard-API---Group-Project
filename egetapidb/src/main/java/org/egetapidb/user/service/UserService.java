package org.egetapidb.user.service;

import org.egetapidb.user.model.User;
import java.util.List;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class UserService {

    @Inject
    EntityManager em;

    UUID uuid;

    public List<User> findAll() {
      List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
      return users;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public User create(User user) {
        user.setApikey(UUID.randomUUID());
        em.persist(user);
        return user;
    }

    

}
