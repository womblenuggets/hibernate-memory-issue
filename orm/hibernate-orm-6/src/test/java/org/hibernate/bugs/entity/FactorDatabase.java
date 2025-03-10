package org.hibernate.bugs.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;


@Entity
public class FactorDatabase implements Serializable {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID applicationId;
    private String name;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID partitionId;

    public FactorDatabase(UUID applicationId,
                          String name,
                          UUID partitionId) {
        this.applicationId = applicationId;
        this.name = name;
        this.partitionId = partitionId;
    }

    public FactorDatabase() {
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(UUID partitionId) {
        this.partitionId = partitionId;
    }
}
