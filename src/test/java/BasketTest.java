import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


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
        try {
            basket.saveTxt(textFile);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
//        Use it to find out the file length
//        System.out.println(textFile.length());
        Assertions.assertEquals(16, textFile.length());
        textFile.delete();
    }
}
