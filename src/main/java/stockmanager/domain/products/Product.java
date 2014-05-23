package stockmanager.domain.products;

import stockmanager.domain.resources.Resource;
import stockmanager.domain.stock.Stockable;


public interface Product extends Stockable {

    /**
     * Returns the cost of product in the specified resource.
     * @param type a type of resource
     * @return the cost of product in the specified resource
     */
    public Double getCostIn(Resource type);

    /**
     * Returns the type of resource at the specified index.
     * @param preferenceIndex index of the preferenced resource
     * @return the type of resource at the specified index
     */
    public Resource getResourceByPreference(int preferenceIndex);

    /**
     * Returns the priority of the product.
     * @return the priority of the product
     */
    public int getPriority();

    /**
     * Returs the sale price of the product.
     * @return the sale price of the product
     */
    public int getSalePrice();

    /**
     * Sets the priority of the product.
     * @param priority the priority of the product
     */
    public void setPriority(int priority);
}
