package info.fermercentr.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static ConfigTelegramSettings configTelegramSettings;
    private static ConfigDB configDB;
    private static Config config;

    private static Config read(final Properties properties) {
        try(InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String token = properties.getProperty("TOKEN");
        String botName = properties.getProperty("BOTNAME");
        String groupId = properties.getProperty("GROUP");
        String host = properties.getProperty("HOST");
        String user = properties.getProperty("USER");
        String psw = properties.getProperty("PSW");

        configTelegramSettings = new ConfigTelegramSettings(token, botName, groupId);
        configDB = new ConfigDB(host, user, psw);

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

    public static ConfigDB getConfigDB() {
        return configDB;
    }

}
