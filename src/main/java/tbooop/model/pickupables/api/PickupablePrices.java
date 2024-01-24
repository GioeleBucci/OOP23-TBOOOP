package tbooop.model.pickupables.api;

/**
 * Rapresents prices for every item
 * in the game.
 */
public enum PickupablePrices {
    /**Price for GoldenHeart item.*/
    GOLDENHEART_PRICE(15);

    private int itemPrice;
    /**Getter for the value of every item price.
     * 
     * @return item price.
    */
    public int getItemPrice() {
        return itemPrice;
    }
    PickupablePrices(final int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
