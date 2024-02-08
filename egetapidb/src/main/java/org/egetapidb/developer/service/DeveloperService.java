package org.egetapidb.developer.service;

import java.util.List;
import java.util.UUID;

import org.egetapidb.developer.model.Developer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class DeveloperService {

    @Inject
    EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public Developer createDev(Developer developer) {
        UUID apiKey = UUID.randomUUID();
        developer.setApiKey(apiKey);
        em.persist(developer);
        return developer;
    }

    public boolean isApiKeyValid(UUID apiKey) {
        try {
            Developer developer = em.createQuery("SELECT d FROM Developer d WHERE d.apiKey = :apiKey", Developer.class)
                    .setParameter("apiKey", apiKey)
                    .getSingleResult();
            return developer != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<Developer> findAll() {
        List<Developer> developers = em.createQuery("SELECT d FROM Developer d", Developer.class).getResultList();
        return developers;
    }
}