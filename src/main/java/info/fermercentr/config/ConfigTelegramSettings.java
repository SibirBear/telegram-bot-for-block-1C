package info.fermercentr.config;

public final class ConfigTelegramSettings {

    private final String token;
    private final String botName;
    private final String group;

    public ConfigTelegramSettings(String ptoken, String pbotName, String pgroup) {
        this.token = ptoken;
        this.botName = pbotName;
        this.group = pgroup;
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }

    public String getGroup() {
        return group;
    }
}
