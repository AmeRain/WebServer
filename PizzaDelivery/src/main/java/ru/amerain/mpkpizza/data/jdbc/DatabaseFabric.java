package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.data.DataManager;


public class DatabaseFabric implements DataFabric {
    public DataManager getData() {
        return new JdbcDataManager();
    }
}
