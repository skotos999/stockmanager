package stockmanager.service;

import stockmanager.domain.products.Product;

public class ProductionOrder {

    private Product product;
    private long qty;//TODO why did u use long for qty?

    public ProductionOrder(Product product, long qty){
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty -= qty;
    }
}
