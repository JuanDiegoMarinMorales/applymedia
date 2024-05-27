package com.example.subscription.enums;

/*
 * Enum to indicates the Customer state
 * and save him/her in our database
 */
public enum ActionTypeStatus {

	// ACTIVE - user is subscribed and 3rd party should provide service to user
	// SUSPENDED - in case of charging failures , subscription is suspended
	// INACTIVE - subscription finished, 3rd party should not provide service to
	// subscriber

	SUB_BILLING_NO(1), SUB_BILLING_YES(2), BILLING_FAILED(3),UNSUB(0);
	private Integer number;

	private ActionTypeStatus(int number) {
        this.number = number;
    }

    // Método getter para obtener el valor del atributo
    public int getNumber() {
        return number;
    }
}