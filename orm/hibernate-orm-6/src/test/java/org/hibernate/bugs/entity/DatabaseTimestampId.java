package org.hibernate.bugs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Embeddable
public class DatabaseTimestampId implements Serializable {
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID          applicationId;
    private LocalDateTime timestamp;

    public DatabaseTimestampId() {
    }

    public DatabaseTimestampId(UUID applicationId, LocalDateTime timestamp) {
        this.applicationId = applicationId;
        this.timestamp = timestamp;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseTimestampId that = (DatabaseTimestampId) o;
        return Objects.equals(applicationId, that.applicationId) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, timestamp);
    }
}
