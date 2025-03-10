package org.hibernate.bugs.entity;

import com.blazebit.persistence.CTE;
import jakarta.persistence.*;

@CTE
@Entity
public class DatabaseStatusCte {
    @EmbeddedId
    private DatabaseTimestampId id;
}
