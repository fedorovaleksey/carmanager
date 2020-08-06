package org.carproject.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionBuilder {
    Connection getConnection() throws IOException, SQLException;
}
