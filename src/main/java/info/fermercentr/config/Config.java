package info.fermercentr.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static ConfigTelegramSettings configTelegramSettings;
    private static ConfigDB configDB;
    private static Config config;

    private static final Logger LOG = LogManager.getLogger(Config.class);

    private static Config read(final Properties properties) {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            LOG.info("[App Config] - Init configuration...");
            properties.load(is);
        } catch (IOException e) {
            LOG.error("[App Config] - Error loading configuration! " + e.getMessage());
            e.printStackTrace();
        }

        String token = properties.getProperty("TOKEN");
        String botName = properties.getProperty("BOTNAME");
        String groupId = properties.getProperty("GROUP");
        String host = properties.getProperty("HOST");
        String user = properties.getProperty("USER");
        String psw = properties.getProperty("PSW");
        String procedure = properties.getProperty("PROCEDURE");

        configTelegramSettings = new ConfigTelegramSettings(token, botName, groupId);
        configDB = new ConfigDB(host, user, psw, procedure);

        return config;
    }

    public static Config init() {
        Properties properties = new Properties();
        config = read(properties);
        LOG.info("[App Config] - Init configuration successful!");
        return config;
    }

    public static ConfigTelegramSettings getConfigTelegramSettings() {
        return configTelegramSettings;
    }

    public static ConfigDB getConfigDB() {
        return configDB;
    }

}
