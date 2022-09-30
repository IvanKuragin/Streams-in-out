import java.io.*;

public class Basket implements Serializable {

    private final String[] products;

    private final int[] prices;

    protected int[] basket;

    protected int[] summary;

    protected int sum;
    
    protected File binFile;

    public Basket(String[] products, int[] prices, int[] basketFile) {
        this.products = products;
        this.prices = prices;
        basket = basketFile;
        this.summary = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum] += amount;
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

    public void saveBin(File file) {
        this.binFile = file;
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    static Basket loadFromBinFile(File file) {
        Basket basketBin = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            basketBin = (Basket) ois.readObject();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        return basketBin;
    }
}
