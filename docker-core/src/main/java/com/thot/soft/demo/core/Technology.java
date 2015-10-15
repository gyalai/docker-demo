package com.thot.soft.demo.core;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = Technology.COLLECTION, noClassnameStored = true)
public class Technology {

    public static final String COLLECTION = "TECHNOLOGY";

    @Id
    private String id;

    @Property("TECHNOLOGY_NAME")
    private String name;

    @Property("DESCRIPTION")
    private String description;

    @Property("URL")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
