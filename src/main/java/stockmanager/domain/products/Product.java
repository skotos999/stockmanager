package stockmanager.domain.products;

import stockmanager.domain.resources.Resource;
import stockmanager.domain.Cost;

public interface Product {//Cow(45, new HashMap<Resource, Cost>()), Sheep() , Pig;


    public Cost getCostIn(Resource unit);

    public int getResourcePreferenceFor(Resource resource) ;

    public Resource getResourcebyPreference(int preferenceIndex);
}
