import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;


@DisplayName("Тестирование класса Basket")
public class BasketTest {

    @Test
    @DisplayName("Добавление в корзину")
    void addToCart() {
        Basket basket = new Basket
                (new String[]{"Овощи", "Конфеты"}, new int[]{50, 100}, new int[]{0, 0});
        basket.addToCart(1, 1);
        Assertions.assertArrayEquals(new int[]{0, 1}, basket.getBasket());
    }

    @Test
    @DisplayName("Сохранение в файл")
    void saveTxt() {
        Basket basket = new Basket
                (new String[]{"Овощи", "Конфеты"}, new int[]{50, 100}, new int[]{0, 0});
        File textFile = new File("BasketTest.txt");
        basket.addToCart(0, 1);
        int count = 0;
        try {
            basket.saveTxt(textFile);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            while (reader.ready()) {
                for (char symbol : reader.readLine().toCharArray()) {
                    if (symbol != ' ' & symbol != '\n') {
                        count++;
                    }
                }
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
//        Use it to find the number of chars in the file. No spaces and line shifters.
//        System.out.println(count);
        Assertions.assertEquals(8, count);
        textFile.delete();
    }
}
