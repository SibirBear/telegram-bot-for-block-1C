package info.fermercentr.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static ConfigTelegramSettings configTelegramSettings;
    private static Config config;

    private static Config read(Properties properties) {
        try(InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = properties.getProperty("TOKEN");
        String botName = properties.getProperty("BOTNAME");
        configTelegramSettings = new ConfigTelegramSettings(token, botName);

        return config;
    }

    public static Config init() {
        Properties properties = new Properties();
        config = read(properties);
        return config;
    }

    public static ConfigTelegramSettings getConfigTelegramSettings() {
        return configTelegramSettings;
    }

}
