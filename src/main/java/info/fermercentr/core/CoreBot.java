package info.fermercentr.core;

import info.fermercentr.config.Config;
import info.fermercentr.service.SendMessageBotService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static info.fermercentr.core.CoreBotConstants.*;

public class CoreBot extends TelegramLongPollingBot {

    private final String token = Config.getConfigTelegramSettings().getToken();
    private final String botName = Config.getConfigTelegramSettings().getBotName();
    private final SendMessageBotService sendMessageBotService = new SendMessageBotService();

    @Override
    public synchronized void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getChatId() == -707127414) {
                if (START.equals(update.getMessage().getText())) {
                    executeMessage(sendMessageBotService.createSimpleMessage(update, "Добрый день, " + update.getMessage().getFrom().getFirstName() + "!"));
                    executeMessage(sendMessageBotService.createSimpleMessage(update, "Ваш id: " + update.getMessage().getFrom().getId()));
                    executeMessage(sendMessageBotService.createSimpleMessage(update, "Введите код клиента"));
                }
                scenarioMessageText(update);
            }
        }
    }

    private void scenarioMessageText(Update update) {

    }

    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeMessage(EditMessageText sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
