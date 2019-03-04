/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-2
 * CSCI 502
 *****/
/**********
Tip Calculator class-Encapsulates calculation logic
**********/
class TipCalculator {
    /**
     * Private data members
     * billAmount- stores bill amount given by user
     * tipPercentage-stores tip percentage givrn by user
     * partySize-stores party size given by user
     * */
    private double billAmount;
    private int tipPercentage,partySize;
    /*****
    *Default constructor for class tip calculator initializes values
    *****/
    public TipCalculator() {
        billAmount = 0;
        tipPercentage = 20;
        partySize = 1;
    }
    /***
     *public getters and setters for bill amount
     *****/
    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }
    /***
     *public getters and setters for percentage
     *****/
    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }
    /***
     *public getters and setters for party size
     *****/
    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }
    /***
     * getTotalBill() method-computes and returns total bill amount (bill+tip)
     *****/
    public double getTotalBill(){
        double totalBillamt;
        totalBillamt=this.getBillAmount()*(1+(this.getTipPercentage()/100.00));
        return  totalBillamt;
    }
    /***
     *getTndividualShare() method-computes and returns value of an equal share of the total bill(totalbill divided by partysize)
     *****/
    public double getIndividualShare(){
        double individualShare;
        individualShare=getTotalBill()/this.getPartySize();
        return  individualShare;
    }

}
