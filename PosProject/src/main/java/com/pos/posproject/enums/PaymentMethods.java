package com.pos.posproject.enums;

/**
 *
 * @author Rori
 */
public enum PaymentMethods {
    CARD(0),
    CASH(1);
    
    private final int id;

    private PaymentMethods(int id) {
        this.id = id;
    }
    
    public int getValue()
    {
        return id;
    }
}
