import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Basket implements Serializable {

    private static final int SHOP_SIZE = 4;

    private final String[] products;

    private final int[] prices;

    protected int[] basket;

    protected int[] summary;

    protected int sum;

    protected static File textFile;

    public static File binFile;

    public Basket(String[] products, int[] prices, int[] basket) {
        this.products = products;
        this.prices = prices;
        this.basket = basket;
        this.summary = new int[SHOP_SIZE];
    }

    public int[] addToCart(int productNum, int amount) {
        basket[productNum] += amount;
        try {
            saveTxt(new File("E:\\IDEA\\Projects\\Stream in out. Serialization", "Basket.txt"));
            saveBin(new File("E:\\IDEA\\Projects\\Stream in out. Serialization", "Basket.bin"));
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
        try {
            boolean creation = textFile.createNewFile();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
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

    public static void fileTermination() {
        try {
            boolean termination = textFile.delete();
        } catch (NullPointerException error) {
            System.out.println("Операция завершена!");
        }
    }

    public void saveBin(File file) {
        Basket.binFile = file;
        Basket basketBin = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Йогурт",},
                new int[]{50, 80, 60, 10}, basket);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            boolean creation = binFile.createNewFile();
            oos.writeObject(basketBin);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    static Basket loadFromBinFile() {
        Basket basketBin = null;
        try (FileInputStream fis = new FileInputStream("E:\\IDEA\\Projects\\Stream in out. Serialization\\Basket.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            basketBin = (Basket) ois.readObject();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return basketBin;
    }
}
