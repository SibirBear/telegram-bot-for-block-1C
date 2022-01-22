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
    private boolean sendResult = false;
    private String result = "";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (START.equals(update.getMessage().getText())) {
                executeMessage(sendMessageBotService.createSimpleMessage(update, "Hello, " + update.getMessage().getFrom().getFirstName() + "!"));
                executeMessage(sendMessageBotService.createMessageWithKeyboard(update, "Введите ЦФУ, нажав на кнопки ниже\nВы ввели: "));
           /* } else {
                if (update.getMessage().getChatId() == -707127414) {
                    executeMessage(sendMessageBotService.createSimpleMessage(update, "Хозяин написал!!!!"));
                } else {
                    executeMessage(sendMessageBotService.createSimpleMessage(update, update.getMessage().getText()));
                }*/
            }
        }

        if (update.hasCallbackQuery()) {

            switch (update.getCallbackQuery().getData()) {
                case ONE:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + ONE));
                    result += ONE;
                    break;
                case TWO:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + TWO));
                    result += TWO;
                    break;
                case THREE:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + THREE));
                    result += THREE;
                    break;
                case FOUR:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + FOUR));
                    result += FOUR;
                    break;
                case FIVE:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + FIVE));
                    result += FIVE;
                    break;
                case SIX:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + SIX));
                    result += SIX;
                    break;
                case SEVEN:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + SEVEN));
                    result += SEVEN;
                    break;
                case EIGHT:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + EIGHT));
                    result += EIGHT;
                    break;
                case NINE:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + NINE));
                    result += NINE;
                    break;
                case ZERO:
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result + ZERO));
                    result += ZERO;
                    break;
                case DELETE:
                    result = removeLastChar(result);
                    executeMessage(sendMessageBotService.editNinekeyboard(update, result));
                    break;
                case ENTER:
                    executeMessage(sendMessageBotService.editNinekeyboardEnd(update, result));
                    break;
            }
        }

    }

    private String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
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
