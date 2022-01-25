package info.fermercentr.model;

public class Order {

    private String idClient;
    private Steps currentStep;
    private boolean actionBlock;
    private String date;
    private String time;
    private OData odata;

    public Order(Steps currentStep) {
        this.currentStep = currentStep;
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

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setCurrentStep(Steps currentStep) {
        this.currentStep = currentStep;
    }

    public void setActionBlock(boolean actionBlock) {
        this.actionBlock = actionBlock;
    }

    public void setOdata(OData odata) {
        this.odata = odata;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return  "Клиент = " + idClient + "\n" +
                //"Блокировать = " + actionBlock + "\n" +
                (actionBlock ? "Заблокировать" : "Разблокировать") + "\n" +
                "Дата = " + date + "\n" +
                "Время = " + time;
    }
}
