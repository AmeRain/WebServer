package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.data.DataManager;


public class DbDataSourceFabric implements DataSourceFabric {
    public DataManager createDataSource() {
        return new JdbcDataManager();
    }
}
