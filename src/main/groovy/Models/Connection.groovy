package Models

import groovy.sql.Sql

class Connection {
    static String url = "jdbc:postgresql://localhost/postgres"
    static String user = "postgres"
    static String password = "postgres"
    static String driver = "org.postgresql.Driver"

    static def sql = Sql.newInstance(url, user, password, driver)
}