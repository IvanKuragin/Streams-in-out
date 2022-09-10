import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private static final int SHOP_SIZE = 4;

    private final String[] products;

    private final int[] prices;

    protected int[] basket;

    protected int[] summary;

    protected int sum;

    public static File textFile;

    public Basket(String[] products, int[] prices, int[] basket) {
        this.products = products;
        this.prices = prices;
        this.basket = basket;
        this.summary = new int[SHOP_SIZE];
    }

    public int[] addToCart(int productNum, int amount) {
        basket[productNum] += amount;
        try {
            saveTxt(Main.textFile);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        return basket;
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (basket[i] > 0) {
                System.out.println(products[i] + " " + basket[i] + " шт. "
                        + prices[i] + " руб./шт. " + (basket[i] * prices[i]) + " руб. в сумме");
            }
        }
        for (int i = 0; i < products.length; i++) {
            if (basket[i] > 0) {
                summary[i] = (basket[i] * prices[i]);
            }
        }
        for (int sum1 : summary) {
            sum += sum1;
        }
        System.out.println("Итого: " + sum + " руб.");
    }

    void getProductList() {
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб./шт.");
        }
    }

    String[] getProducts() {
        return products;
    }

    public void saveTxt(File textFile) throws IOException {
        Basket.textFile = textFile;
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int amount : basket) {
                out.print(amount + " ");
            }
        }
    }

    static Basket loadFromTxtFile() {
        int[] basket = new int[SHOP_SIZE];
        try (FileReader in = new FileReader("Basket.txt", StandardCharsets.UTF_8)) {
            List<Integer> list = new ArrayList<>();
            while (in.ready()) {
                char c = (char) in.read();
                int c1 = Character.getNumericValue(c);
                if (c1 >= 0) {
                    list.add(c1);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                basket[i] = list.get(i);
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
        return new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Йогурт",},
                new int[]{50, 80, 60, 10}, basket);
    }
}
