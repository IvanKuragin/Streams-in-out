import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;


public class Basket {

    protected String[] products;

    protected int[] prices;

    protected int[] basket;

    protected int[] summary;

    protected int sum;

    protected File jsonFile;

    public Basket() {
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }

    public void setBasket(int[] basketFile) {
        basket = basketFile;
    }

    public void setSummary(int[] summary) {
        this.summary = summary;
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum] += amount;
    }

    public int[] getBasket() {
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

    public void saveJson(File jsonFile) throws IOException {
        this.jsonFile = jsonFile;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        try (PrintWriter out = new PrintWriter(jsonFile)) {
            out.println(json);
        }
    }

    static Basket loadFromJsonFile(File jsonFile) {
        Basket basket = null;
        if (ShopXmlReader.loadBoolean && ShopXmlReader.saveBoolean) {
            String line;
            String jsonText;
            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonText = sb.toString();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                basket = gson.fromJson(jsonText, Basket.class);
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        }
        return basket;
    }
}
