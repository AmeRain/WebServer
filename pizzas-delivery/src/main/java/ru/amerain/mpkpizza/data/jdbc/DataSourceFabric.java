package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.data.DataManager;


public interface DataSourceFabric {
   DataManager createDataSource();
}
