package stockmanager.domain.stock;

import java.util.List;

public enum Stock {
    INSTANCE;

    private List<StockItem> stockItems;

    private Stock() {

    }
}
