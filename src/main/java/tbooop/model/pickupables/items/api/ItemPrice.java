package tbooop.model.pickupables.items.api;

/**
 * Rapresents prices for every item
 * in the game.
 */
public enum ItemPrice {
    /**Price for GlassHeart item.*/
    GLASSHEART_PRICE(15),
    /**Price for the Zap item. */
    ZAP_PRICE(10),
    /**Price for SpicySauce item. */
    SPICYSAUCE_PRICE(20),
    /**Price for LockedRing item. */
    LOCKEDRING_PRICE(15),
    /**Price for Golden Apple item. */
    GOLDENAPPLE_PRICE(15);

    private int itemPrice;
    /**Getter for the value of every item price.
     * 
     * @return item price.
    */
    public int getItemPrice() {
        return itemPrice;
    }
    ItemPrice(final int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
