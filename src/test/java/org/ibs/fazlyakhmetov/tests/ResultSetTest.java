package org.ibs.fazlyakhmetov.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetTest extends BaseTest {
    /**
     * В этом тест проверяем работу курсора по таблице
     * Выводим все строки поочередно, начиная с конца.
     *
     * @throws SQLException
     */

    @Test
    @DisplayName("Проверка записей с помощью курсора и команд last,previous")
    public void cursorTest() throws SQLException {
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        String selectAll = "SELECT * FROM food";

        ResultSet resultSet = statement.executeQuery(selectAll);
        System.out.printf("%s%n", "Выводим последний элемент в списке");
        resultSet.last();
        System.out.printf("%d %s %s %b%n",
                resultSet.getInt("food_id"),
                resultSet.getString("food_name"),
                resultSet.getString("food_type"),
                resultSet.getBoolean("food_exotic")
        );

        System.out.printf("%s%n", "Выводим предпоследний элемент в списке (поднимаемся на один выше)");
        resultSet.previous();
        System.out.printf("%d %s %s %b%n",
                resultSet.getInt("food_id"),
                resultSet.getString("food_name"),
                resultSet.getString("food_type"),
                resultSet.getBoolean("food_exotic")
        );
        System.out.printf("%s%n", "Выводим предпоследний элемент в списке (поднимаемся на один выше)");
        resultSet.previous();
        System.out.printf("%d %s %s %b%n",
                resultSet.getInt("food_id"),
                resultSet.getString("food_name"),
                resultSet.getString("food_type"),
                resultSet.getBoolean("food_exotic")
        );
        System.out.printf("%s%n", "Выводим предпоследний элемент в списке (поднимаемся на один выше)");
        resultSet.previous();
        System.out.printf("%d %s %s %b%n",
                resultSet.getInt("food_id"),
                resultSet.getString("food_name"),
                resultSet.getString("food_type"),
                resultSet.getBoolean("food_exotic")
        );
    }
}
