package cn.evole.mods.elona.api.save.sql.database;

import lombok.Getter;

import java.util.Properties;

/**
 * Name: Elona / AuthenticatedDatabase
 * Author: cnlimiter
 * CreateTime: 2023/9/3 22:10
 * Description:
 */

@Getter
public abstract class AuthenticatedDatabase extends Database {

    protected final String address;
    protected final String port;
    protected final String username;
    protected final String password;

    public AuthenticatedDatabase(String modId, String name, String address, String port, String username, String password) {
        super(modId, name);
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        open();
    }

    @Override
    public Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.put("user", username);
        properties.put("password", password);
        return properties;
    }

    @Override
    public void beginTransaction() {
        sqlConnection.beginTransaction(false);
    }
}
