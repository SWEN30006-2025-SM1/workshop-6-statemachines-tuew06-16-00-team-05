package com.unimelb.swen30006.workshops;

public class Default {
    private double penalty;
    private int gracePeriod;
    private DefaultState curState;
    private Account account;

    public Default(){}

    public void acceptsPaymentPlan(){
        if (curState == DefaultState.PENDING_PAYMENT_PLAN){
            curState = DefaultState.HEALTHY_DEBT;
        }
    }

    public void requiresPayment(boolean didPay, int amount){
        if (curState != DefaultState.HEALTHY_DEBT){
            return;
        }

        if (didPay){
            account.updateFund(amount);
        } else {
            curState = DefaultState.UNHEALTHY_DEBT;
        }
    }

    public void paysOverdueBill(){
        account.payOverdueBill();
    }

    public void collectionAgencyFound(){
        if (curState != DefaultState.UNHEALTHY_DEBT){
            return;
        }

        // TODO
        account.toUnderCollection();
    }
}
