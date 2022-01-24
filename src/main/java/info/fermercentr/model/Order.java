package info.fermercentr.model;

public class Order {

    private String idClient;
    private Steps currentStep;
    private boolean actionBlock;
    private String date;
    private String time;

    public Order(String idClient, Steps currentStep, boolean actionBlock, String date, String time) {
        this.idClient = idClient;
        this.currentStep = currentStep;
        this.actionBlock = actionBlock;
        this.date = date;
        this.time = time;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
