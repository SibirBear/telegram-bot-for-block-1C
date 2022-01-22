package info.fermercentr.service.constants;

public enum Buttons {

    ONE("1️⃣"),
    TWO("2️⃣"),
    THREE("3️⃣"),
    FOUR("4️⃣"),
    FIVE("5️⃣"),
    SIX("6️⃣"),
    SEVEN("7️⃣"),
    EIGHT("8️⃣"),
    NINE("9️⃣"),
    ZERO("0️⃣"),
    ENTER("✅"),
    DELETE("⬅️");

    private final String buttonName;

    Buttons(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

}
