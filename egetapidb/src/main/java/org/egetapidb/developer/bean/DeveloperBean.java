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

    private String devEmail;
    private UUID apiKey;

    @Inject
    private DeveloperService developerService;

    public String getDevEmail() {
        return devEmail;
    }

    public void setDevEmail(String devEmail) {
        this.devEmail = devEmail;
    }

    public void newDeveloper() {
        Developer developer = new Developer();
        developer.setDevEmail(devEmail);
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