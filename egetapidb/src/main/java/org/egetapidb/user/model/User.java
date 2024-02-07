package org.egetapidb.user.model;

import java.util.List;
import java.util.UUID;

import org.egetapidb.post.model.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Du måste ange ett namn")
    @Column
    private String username;
    private UUID apikey;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getApikey() {
        return apikey;
    }

    public void setApikey(UUID apikey) {
        this.apikey = apikey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}