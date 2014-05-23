package stockmanager.domain.products;

import stockmanager.domain.resources.Resource;
import stockmanager.domain.stock.Stockable;

public interface Product extends Stockable {

    public Double getCostIn(Resource unit);

    public Resource getResourceByPreference(int preferenceIndex);

    public int getPriority();

    public int getSalePrice();

    public void setPriority(int priority);
}
