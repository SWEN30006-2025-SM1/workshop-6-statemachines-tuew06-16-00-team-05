package com.unimelb.swen30006.workshops;

import java.util.Date;

public class Account {
    private State state;
    private Date lastActive;
    private double funds;
    private double creditLimit;
    private Default defaultState;

    public Account() {
        this.state = State.PENDING;
        this.lastActive = new Date();
        this.funds = 0.0;
        this.creditLimit = 0.0;
        this.defaultState = null;
    }

    public void onboard() {
        this.state = State.PENDING;
    }

    public boolean activateCreditCard() {
        switch (this.state) {
            case PENDING: {
                this.state = State.ACTIVE;
                this.lastActive = new Date();
                this.funds = 0;
                this.creditLimit = 1000.0;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private void newCardIssued() {

    }

    public void customerReportLostStolen() {
        switch (this.state) {
            case ACTIVE: {
                this.state = State.PENDING;
                this.newCardIssued();
                break;
            }
        }
    }

    private boolean creditCardUsageOverLimit() {
        return this.funds < -this.creditLimit;
    }

    public void creditCardUsage(int cost) {
        this.funds -= cost;
        switch (this.state) {
            case ACTIVE: {
                if (this.creditCardUsageOverLimit()) {
                    this.state = State.SUSPENDED;
                    break;
                }
            }
        }
    }

    private boolean availableFundMoreThanZero() {
        return this.funds > 0;
    }

    private void updateFund(double fund) {
        this.funds += fund;
    }

    public void fund(double fund) {
        this.updateFund(fund);
        switch (this.state) {
            case SUSPENDED: {
                if (this.availableFundMoreThanZero()) {
                    this.state = State.ACTIVE;
                }
                break;
            }
        }
    }

    public void failedToPayBill() {
        switch (this.state) {
            case ACTIVE: {
                this.state = State.DEFAULT;
                this.defaultState = new Default();
                // TODO: implement default behavior
                break;
            }
        }
    }

    public void payOverdueBill() {
        switch (this.state) {
            case DEFAULT: {
                this.state = State.ACTIVE;
                break;
            }
        }
    }

    public void foundCollectionAgency() {
        switch (this.state) {
            case DEFAULT: {
                this.state = State.UNDER_COLLECTION;
                break;
            }
        }
    }

    public void debtCollectedOrWrittenOff() {
        switch (this.state) {
            case UNDER_COLLECTION: {
                this.state = State.CLOSED;
                break;
            }
        }
    }

    public void noOutstandingBills() {
        switch (this.state) {
            case ACTIVE: {
                Date currentDate = new Date();
                long sixMonth = 6 * 30l * 24 * 60 * 60 * 1000;
                if (currentDate.after(new Date((this.lastActive.getTime() + sixMonth)))) {
                    this.state = State.INACTIVE;
                }

                break;
            }
        }
    }

    public void contactCompany() {
        switch (this.state) {
            case INACTIVE: {
                this.state = State.ACTIVE;
                break;
            }
        }
    }

    public void checkInactive() {
        switch (this.state) {
            case INACTIVE: {
                this.state = State.CLOSED;
            }
        }
    }
}
