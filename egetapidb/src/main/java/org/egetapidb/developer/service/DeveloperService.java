package org.egetapidb.developer.service;

import java.util.List;
import java.util.UUID;

import org.egetapidb.developer.model.Developer;

import io.quarkus.security.UnauthorizedException;
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

        String devEmail = developer.getDevEmail();

        Developer existingDeveloper = em
                .createQuery("SELECT d FROM Developer d WHERE d.devEmail = :email", Developer.class)
                .setParameter("email", devEmail)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (existingDeveloper != null) {

            throw new UnauthorizedException("Inte giltligt!");
        } else {

            UUID apiKey = UUID.randomUUID();
            developer.setApiKey(apiKey);
            em.persist(developer);
            return developer;
        }
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

    public void validateApiKey(UUID apiKey) {
        if (!isApiKeyValid(apiKey)) {
            throw new UnauthorizedException("Inte giltligt!");
        }
    }
}