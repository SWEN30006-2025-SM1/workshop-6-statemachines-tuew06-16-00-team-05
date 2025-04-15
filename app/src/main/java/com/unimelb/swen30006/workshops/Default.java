package com.unimelb.swen30006.workshops;

public class Default {
    private double penalty;
    private int gracePeriod;
    private DefaultState curState;
    private Account account;

    public Default(){}
    public void enterDefault(boolean missedPayment){
        if(missedPayment){
            curState = DefaultState.PENDING_PAYMENT_PLAN
            penalty = -20;
        } else{
            curState = DefaultState.GRACE_PERIOD
        }
    }
    public void failedToPayUnderGrace(){
        if(curState == DefaultState.GRACE_PERIOD){
            curState = DefaultState.PENDING_PAYMENT_PLAN;
        }
    }
    public void failedPaymentPlanResponse(){
        if(curState == DefaultState.PENDING_PAYMENT_PLAN){
            curState = DefaultState.UNHEALTHY_DEBT;
        }
    }
    public void refusedPaymentPlan(){
        if(curState == DefaultState.PENDING_PAYMENT_PLAN){
            curState = DefaultState.UNHEALTHY_DEBT;
        }
    }

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
