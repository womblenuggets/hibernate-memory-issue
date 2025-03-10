package org.hibernate.bugs.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class DatabaseStatistics implements Serializable {
    @EmbeddedId
    private DatabaseTimestampId id;
    private String              version;

    public DatabaseStatistics() {
    }

    public DatabaseTimestampId getId() {
        return id;
    }

    public void setId(DatabaseTimestampId id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
