package tbooop.model.pickupables.items.api;

/**
 * Rapresents prices for every item
 * in the game.
 */
public enum PickupablePrices {
    /**Price for GoldenHeart item.*/
    GOLDENHEART_PRICE(15),
    /**Price for the Belt item. */
    BELT_PRICE(10),
    /**Price for FireMind item. */
    FIREMIND_PRICE(20),
    /**Price for Iron Bar item. */
    IRONBAR_PRICE(15);

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
