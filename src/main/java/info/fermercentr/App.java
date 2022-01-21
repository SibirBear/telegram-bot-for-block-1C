package info.fermercentr;

import info.fermercentr.config.Config;
import info.fermercentr.core.CoreBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {

    public static void main(String[] args) {

        Config.init();

        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CoreBot());
        } catch (TelegramApiException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

}
