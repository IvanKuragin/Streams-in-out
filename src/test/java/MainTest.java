import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@DisplayName("Тестирование класса Main")
public class MainTest {

    @Test
    @DisplayName("Создание текстового файла")
    void main() {
        File textFile = new File("Basket.txt");
        boolean isCreated = false;
        int check = 0;
        try {
            isCreated = textFile.createNewFile();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        if (isCreated) {
            check = 1;
        }
        Assertions.assertEquals(1, check);
        textFile.delete();
    }
}
