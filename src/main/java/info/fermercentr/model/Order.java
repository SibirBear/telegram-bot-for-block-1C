package info.fermercentr.model;

public final class Order {

    private String idClient;
    private Steps currentStep;
    private boolean actionBlock;
    private String date;
    private String time;
    private OData odata;

    public Order(Steps pCurrentStep) {
        this.currentStep = pCurrentStep;
    }

    public String getIdClient() {
        return idClient;
    }

    public Steps getCurrentStep() {
        return currentStep;
    }

    public boolean isActionBlock() {
        return actionBlock;
    }

    public OData getOdata() {
        return odata;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setIdClient(String pIdClient) {
        this.idClient = pIdClient;
    }

    public void setCurrentStep(Steps pCurrentStep) {
        this.currentStep = pCurrentStep;
    }

    public void setActionBlock(boolean pActionBlock) {
        this.actionBlock = pActionBlock;
    }

    public void setOdata(OData pOdata) {
        this.odata = pOdata;
    }

    public void setDate(String pDate) {
        this.date = pDate;
    }

    public void setTime(String pTime) {
        this.time = pTime;
    }

    @Override
    public String toString() {
        return  "Клиент = " + idClient + "\n"
                + (actionBlock ? "Заблокировать" : "Разблокировать") + "\n"
                + "Дата = " + date + "\n" + "Время = " + time;
    }
}
