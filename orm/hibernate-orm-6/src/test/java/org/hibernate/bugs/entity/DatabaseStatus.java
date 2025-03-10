package org.hibernate.bugs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class DatabaseStatus implements Serializable {
    @EmbeddedId
    private DatabaseTimestampId id;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID                partitionId;

    public DatabaseStatus() {
    }

    public DatabaseTimestampId getId() {
        return id;
    }

    public void setId(DatabaseTimestampId id) {
        this.id = id;
    }

    public UUID getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(UUID partitionId) {
        this.partitionId = partitionId;
    }
}