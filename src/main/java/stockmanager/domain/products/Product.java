package stockmanager.domain.products;

import stockmanager.domain.resources.Resource;
import stockmanager.domain.Cost;
import stockmanager.domain.stock.Stockable;

public interface Product extends Stockable {//Cow(45, new HashMap<Resource, Cost>()), Sheep() , Pig;


    public Double getCostIn(Resource unit);

    public int getResourcePreferenceFor(Resource resource) ;

    public Resource getResourceByPreference(int preferenceIndex);

    public int getPriority();

    public int getSalePrice();

    public void setPriority(int priority);
}
