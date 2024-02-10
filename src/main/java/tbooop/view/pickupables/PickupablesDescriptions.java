package tbooop.view.pickupables;
/**List of descriptions for every Pickupable
 * game object.
 */
public enum PickupablesDescriptions {
    /**Description for Key pickup.*/
    KEY_DESCRIPTION("   Add a key   "),
    /**Description for Coin pickup.*/
    COIN_DESCRIPTION("Add a single coin"),
    /**Description for Heart pickup.*/
    HEART_DESCRIPTION("Add an heart"),
    /**Description for Bill pickup.*/
    BILL_DESCRIPTION("Add 10 coins"),
    /**Description for GoldenApple item.*/
    GOLDENAPPLE_DESCRIPTION("Increase player max health"),
    /**Description for GlassHeart item.*/
    GLASSHEART_DESCRIPTION("Add an heart"),
    /**Description for LockedRing item.*/
    LOCKEDRING_DESCRIPTION("Increase player damage"),
    /**Description for SpicySauce item.*/
    SPICYSAUCE_DESCRIPTION("Increase player's\nprojectiles velocity"),
    /**Description for Zap item.*/
    ZAP_DESCRIPTION("Increase player velocity");
    
    private String pickupableDescription;
    /**Getter for the description of every pickupable.
    * 
    * @return pickupable description.
    */
    public String getPickupableDescription() {
        return pickupableDescription;
    }
    PickupablesDescriptions(final String pickupableDescription) {
        this.pickupableDescription = pickupableDescription;
    }
}
