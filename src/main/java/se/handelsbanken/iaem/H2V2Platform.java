package se.handelsbanken.iaem;

import org.eclipse.persistence.platform.database.H2Platform;
import org.eclipse.persistence.queries.ValueReadQuery;

/**
 * EclipseLink platform for H2 2.x.
 * H2 2.x removed CALL IDENTITY() — replaced with CALL LAST_INSERT_ID().
 * DDL is handled via an explicit SQL script (create-h2.sql) which uses AUTO_INCREMENT
 * instead of the H2 1.x BIGINT IDENTITY syntax.
 */
public class H2V2Platform extends H2Platform {

    @Override
    public ValueReadQuery buildSelectQueryForIdentity() {
        return new ValueReadQuery("CALL LAST_INSERT_ID()");
    }
}
