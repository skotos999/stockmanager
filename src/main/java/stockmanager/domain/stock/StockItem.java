package stockmanager.domain.stock;


public class StockItem {

    private Stockable item;
    private long qty;

    public StockItem(Stockable item, long qty) {
        this.item = item;
        this.qty = qty;
    }

}
