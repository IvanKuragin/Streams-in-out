public class Log {
    protected int productNum;
    protected int amount;

    public Log(int product, int amount) {
        productNum = product;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "productNum" + "," + "amount";
    }
}
