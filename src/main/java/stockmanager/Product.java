package stockmanager;

public enum Product {Cow, Sheep, Pig

    private int salePrice;
    private int costInWheat;
    private int costInCorn;
    private int priority;

    private Product(){

    }

    public int getSalePrice() {
        return salePrice;
    }
}
