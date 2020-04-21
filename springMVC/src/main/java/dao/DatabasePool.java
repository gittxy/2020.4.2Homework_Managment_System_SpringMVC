package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePool {

    private static HikariDataSource hikariDataSource;

    //双重锁
    public static HikariDataSource getHikariDataSource(){

        if(null != hikariDataSource){
            return hikariDataSource;
        }

        synchronized (DatabasePool.class) {
            if (null == hikariDataSource) {
                HikariConfig hikariConfig = new HikariConfig();
                String driverName = "com.mysql.cj.dao.Driver";
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("mysqlroot");
                hikariConfig.setDriverClassName(driverName);
                hikariConfig.setJdbcUrl("dao:mysql://127.0.0.1:3306/school?serverTimezone=UTC");

                hikariDataSource = new HikariDataSource(hikariConfig);
                return hikariDataSource;
            }
        }
        return null;
    }
}
