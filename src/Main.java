import java.io.File;
import java.util.Scanner;

public class Main {

    public static File binFile;

    public static String [] shopProducts = {"Хлеб", "Яблоки", "Молоко", "Йогурт"};

    public static int [] shopPrices = {50, 80, 60, 10};

    public static boolean isCreated;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            binFile = new File("E:\\IDEA\\Projects\\Stream in out. Serialization", "Basket.bin");
            isCreated = binFile.createNewFile();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        Basket basket;

        if (Basket.loadFromBinFile(binFile) == null) {
            basket = new Basket(shopProducts, shopPrices, new int[]{0, 0, 0, 0});
        } else {
            basket = Basket.loadFromBinFile(binFile);
            basket.printCart();
        }

        while (true) {
            System.out.println("Список возможных товаров для покупки:");
            for (int i = 0; i < shopProducts.length; i++) {
                System.out.println((i + 1) + ". " + shopProducts[i] + " " + shopPrices[i] + " руб./шт.");
            }
            System.out.println("Выберите товар и количество или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] custChoice = input.split(" ");
            if (custChoice.length != 2) {
                throw new RuntimeException("Пожалуйста, введите только 2 числа: номер продукта по списку и количество!");
            }
            try {
                int productNum = Integer.parseInt(custChoice[0]) - 1;
                if ((productNum + 1) > shopProducts.length || (productNum + 1) < 0) {
                    throw new RuntimeException("Введен некорректный номер товара! Пожалуйста, введите номер из списка.");
                }
                int amount = Integer.parseInt(custChoice[1]);
                if (amount < 0) {
                    throw new RuntimeException("Введено некорректное количество товара! Пожалуйста, укажите количество еще раз.");
                }
                basket.addToCart(productNum, amount);
            } catch (NumberFormatException error) {
                System.out.println("Вы ввели название товара! Пожалуйста, введите его номер по списку");
                continue;
            }
        }
        basket.printCart();
        binFile.delete();
    }
}
