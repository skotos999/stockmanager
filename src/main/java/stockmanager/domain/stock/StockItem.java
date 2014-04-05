package stockmanager.domain.stock;


public class StockItem {

    private Stockable item;
    private long qty;

    public StockItem(Stockable item, long qty) {
        this.item = item;
        this.qty = qty;
    }

    public long getQty() {
        return qty;
    }

    public long subtract(long qty) {
        long result = this.qty - qty;
        if (result >= 0) return result;
        return 0;
    }
}
