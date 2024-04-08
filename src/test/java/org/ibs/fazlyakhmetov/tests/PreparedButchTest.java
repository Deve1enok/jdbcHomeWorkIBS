package org.ibs.fazlyakhmetov.tests;

import org.ibs.fazlyakhmetov.tests.utils.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PreparedButchTest extends BaseTest {

    /**
     * В данном тесте используем statement и preparedStatement с addButch(пакет запросов)
     * Тест включает в себя запросы select,insert,delete
     */

    @Test
    @DisplayName("Проверка стандартных/новых записей таблицы с пакетом запросов на вставку")
    public void testWithBatch() throws SQLException {

        statement = connection.createStatement();

        String selectAll = "SELECT * FROM food ORDER BY food_id ASC";

        ResultSet defaultTable = statement.executeQuery(selectAll);

        System.out.printf("%s%n", "Проверка таблицы на наличие дефолтных значений");
        while (defaultTable.next()) {
            int food_id = defaultTable.getInt("food_id");
            String food_name = defaultTable.getString("food_name");
            String food_type = defaultTable.getString("food_type");
            boolean exotic = defaultTable.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }

        List<Food> foodList = List.of(
                new Food("Арбуз", "FRUIT", 0),
                new Food("Ананас", "FRUIT", 1),
                new Food("Манго", "FRUIT", 1),
                new Food("Тыква", "VEGETABLE", 0),
                new Food("Свекла", "VEGETABLE", 0),
                new Food("Мандарин", "FRUIT", 0),
                new Food("Гранат", "FRUIT", 0),
                new Food("Дыня", "FRUIT", 0),
                new Food("Киви", "FRUIT", 1),
                new Food("Лук", "VEGETABLE", 0)
        );
        String insert =
                "INSERT INTO food(food_name, food_type, food_exotic) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(insert);

        for (Food food : foodList) {
            preparedStatement.setString(1, food.getFruitName());
            preparedStatement.setString(2, food.getFruitType());
            preparedStatement.setInt(3, food.getExoticType());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        ResultSet tableAfterUpdate = statement.executeQuery(selectAll);

        System.out.printf("%n%s%n", "Проверяем таблицу на добавление списка товаров");
        while (tableAfterUpdate.next()) {
            int food_id = tableAfterUpdate.getInt("food_id");
            String food_name = tableAfterUpdate.getString("food_name");
            String food_type = tableAfterUpdate.getString("food_type");
            boolean exotic = tableAfterUpdate.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }

        String delete = "DELETE FROM food WHERE food_id > 4 AND food_id < 50";

        statement.executeUpdate(delete);

        System.out.printf("%n%s%n", "Проверяем таблицу на удаление товаров");
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
