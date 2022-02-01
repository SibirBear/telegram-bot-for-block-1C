package info.fermercentr;

import info.fermercentr.config.Config;
import info.fermercentr.core.CoreBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.http.HttpServlet;

@Startup
@Singleton(name = "Telegram-bot-for-block-1C")
public final class App extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(App.class);

    @Override
    public void init() {
        Config.init();

        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CoreBot());
            LOG.info("[App] - App is starting!");
        } catch (TelegramApiException e) {
            LOG.error("[App] - Error starting app. " + e.getMessage());
        }

    }

    @Override
    public void destroy() {
        LOG.info("[App] - App is stopped!");
    }

}
