package ru.netology.data;


import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private SQLHelper() {

    }
    @SneakyThrows
    public static Connection getConnection() {
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            return conn;
        }
    }

    @SneakyThrows
    public static String getVerificationCode(DataHelper.AuthInfo authInfo) {
        QueryRunner runner = new QueryRunner();
        String userLogin = authInfo.getLogin();
        String verCodeSQL = "SELECT code FROM auth_codes WHERE user_id = (SELECT id FROM users WHERE login = ?)";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        )
        {
            String result = runner.query(conn, verCodeSQL, new ScalarHandler<>(), userLogin);
            return result;
        }
    }

    @SneakyThrows
    public static void clearBD() {
        QueryRunner runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        )
        {
            runner.execute(conn, "DELETE from app.auth_codes;");
            runner.execute(conn, "DELETE from app.cards;");
            runner.execute(conn, "DELETE from app.users;");
            runner.execute(conn, "DELETE from app.card_transactions;");
        }
    }
}
