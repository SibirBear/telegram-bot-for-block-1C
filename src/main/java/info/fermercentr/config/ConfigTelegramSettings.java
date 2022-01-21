package info.fermercentr.config;

public class ConfigTelegramSettings {

    private final String token;
    private final String botName;

    public ConfigTelegramSettings(String token, String botName) {
        this.token = token;
        this.botName = botName;
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }

}
