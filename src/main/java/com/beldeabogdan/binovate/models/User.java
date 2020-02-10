package com.beldeabogdan.binovate.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @JsonProperty(
            value = "id",
            access = JsonProperty.Access.READ_ONLY
    )
    private Integer id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
