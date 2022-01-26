package info.fermercentr.config;

public class ConfigTelegramSettings {

    private final String token;
    private final String botName;
    private final String group;

    public ConfigTelegramSettings(String token, String botName, String group) {
        this.token = token;
        this.botName = botName;
        this.group = group;
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
