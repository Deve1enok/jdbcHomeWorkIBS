package org.ibs.fazlyakhmetov.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementAndPreparedTest extends BaseTest {

    /**
     * В данном тесте используем statement и preparedStatement
     * Тест включает в себя запросы select,insert,delete
     */

    @Test
    @DisplayName("Проверка стандартных/новых записей таблицы с запросами на вставку/удаление")
    public void simpleTest() throws SQLException {
        statement = connection.createStatement();

        String selectAll = "SELECT * FROM food";

        ResultSet defaultTable = statement.executeQuery(selectAll);

        System.out.printf("%s%n", "Проверка таблицы на наличие дефолтных значений");
        while (defaultTable.next()) {
            int food_id = defaultTable.getInt("food_id");
            String food_name = defaultTable.getString("food_name");
            String food_type = defaultTable.getString("food_type");
            boolean exotic = defaultTable.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }

        String insert =
                "INSERT INTO food(food_name, food_type, food_exotic) VALUES (?, ?, ?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setString(1, "Вишня");
        preparedStatement.setString(2, "FRUIT");
        preparedStatement.setInt(3, 0);
        preparedStatement.executeUpdate();

        ResultSet tableAfterUpdate = statement.executeQuery(selectAll);

        System.out.printf("%n%s%n", "Проверяем таблицу на добавление товара");
        while (tableAfterUpdate.next()) {
            int food_id = tableAfterUpdate.getInt("food_id");
            String food_name = tableAfterUpdate.getString("food_name");
            String food_type = tableAfterUpdate.getString("food_type");
            boolean exotic = tableAfterUpdate.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }

        String delete = "DELETE FROM food WHERE food_id = 10";

        statement.executeUpdate(delete);

        System.out.printf("%n%s%n", "Проверяем таблицу на удаление товара");

        ResultSet tableAfterDeleteString = statement.executeQuery(selectAll);

        while (tableAfterDeleteString.next()) {
            int food_id = tableAfterDeleteString.getInt("food_id");
            String food_name = tableAfterDeleteString.getString("food_name");
            String food_type = tableAfterDeleteString.getString("food_type");
            boolean exotic = tableAfterDeleteString.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }
    }
}