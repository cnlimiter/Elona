package cn.evole.mods.elona.api.sql.database;

import cn.evole.mods.elona.api.sql.base.SQLConnection;
import cn.evole.mods.elona.api.sql.base.Table;
import lombok.Getter;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Name: Elona / Database
 * Author: cnlimiter
 * CreateTime: 2023/9/3 21:56
 * Description:
 */

public abstract class Database {
    @Getter
    protected final String name;
    @Getter
    protected final String modId;
    private final HashMap<String, Table> tables = new HashMap<>();
    protected SQLConnection sqlConnection;

    public Database(String modId, String name) {
        this.modId = modId;
        this.name = name;
    }

    public abstract String getConnectionUrl();

    public Properties getConnectionProperties() {
        return new Properties();
    }

    public abstract String getTableCreationQuery(String tableName, String columns);

    public void open() {
        if (sqlConnection == null) sqlConnection = new SQLConnection(getConnectionUrl(), getConnectionProperties());
    }

    public void close() {
        if (sqlConnection != null) sqlConnection.close();
        sqlConnection = null;
    }

    public abstract void beginTransaction();

    public void endTransaction() {
        sqlConnection.endTransaction();
    }

    public Table createTable(String name) {
        return new Table(modId, name, this, sqlConnection);
    }

    public void addTable(Table table) {
        tables.put(table.getName(), table);
    }

    public Table getTable(String name) {
        return tables.get(modId + "_" + name);
    }

    public ArrayList<Table> getTables() {
        return new ArrayList<>(tables.values());
    }

    public PreparedStatement executeCommand(String sql, boolean autoClose, Object... params) {
        return sqlConnection.executeCommand(sql, autoClose, params);
    }
}
