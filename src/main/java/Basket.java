import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Basket {

    protected String[] products;

    protected int[] prices;

    protected int[] basket;

    protected int[] summary;

    protected int sum;

    protected File textFile;

    public Basket(String[] products, int[] prices, int[] basketFile) {
        this.products = products;
        this.prices = prices;
        basket = basketFile;
        this.summary = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum] += amount;
    }

    public int [] getBasket() {
        return basket;
    }

    public void printCart() {
        sum = 0;
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

    public void saveTxt(File textFile) throws IOException {
        this.textFile = textFile;
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int i = 0; i < products.length; i++) {
                if (basket[i] > 0) {
                    out.print(products[i] + " " + basket[i] + " "
                            + prices[i] + "\n");
                }
            }
        }
    }

    static Basket loadFromTxtFile(File textFile) {
        List<String> products = new ArrayList<>();
        List<String> basket1 = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try (FileReader in = new FileReader(textFile, StandardCharsets.UTF_8)) {
            while (in.ready()) {
                for (int i = 0; i < 1; i++) {
                    char c = (char) in.read();
                    if (c != 0) {
                        sb.append(c);
                    }
                }
            }
        } catch (IOException error) {
            error.getMessage();
        }
        String text1 = sb.toString();
        String[] lines = text1.split("\n");
        List<String> text = new ArrayList<>(Arrays.asList(lines));
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            String[] lineArray = line.split(" ");
            for (int j = 0; j < line.length(); ) {
                products.add(lineArray[j]);
                for (int k = 1; k < line.length(); ) {
                    basket1.add(lineArray[k]);
                    for (int l = 2; l < line.length() - 1; ) {
                        prices.add(lineArray[l]);
                        break;
                    }
                    break;
                }
                break;
            }
        }
        if (text1.equals("")) {
            return null;
        } else {
            String[] productsArr = products.toArray(new String[products.size()]);
            int[] basketArr = basket1.stream().mapToInt(Integer::parseInt).toArray();
            int[] pricesArr = prices.stream().mapToInt(Integer::parseInt).toArray();
            return new Basket(productsArr, pricesArr, basketArr);
        }
    }
}
