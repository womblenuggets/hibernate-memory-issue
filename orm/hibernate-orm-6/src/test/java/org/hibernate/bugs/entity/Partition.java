package org.hibernate.bugs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Partition implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID partitionId;
    private String name;
    private LocalDateTime lastUpdated;

    public Partition() {
    }

    public Partition(UUID partitionId, String name, LocalDateTime lastUpdated) {
        this.partitionId = partitionId;
        this.name = name;
        this.lastUpdated = lastUpdated;
    }

    public UUID getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(UUID partitionId) {
        this.partitionId = partitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
