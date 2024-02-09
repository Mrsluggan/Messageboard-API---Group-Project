package org.egetapidb.developer.bean;

import java.util.UUID;

import org.egetapidb.developer.model.Developer;
import org.egetapidb.developer.service.DeveloperService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DeveloperBean {

    private String email;
    private UUID apiKey;

    @Inject
    private DeveloperService developerService;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void newDeveloper() {
        Developer developer = new Developer();
        developer.setEmail(email);
        developerService.createDev(developer);
        apiKey = developer.getApiKey();
    }

    public UUID getApiKey() {
        return apiKey;
    }

    public void setApiKey(UUID apiKey) {
        this.apiKey = apiKey;
    }
}