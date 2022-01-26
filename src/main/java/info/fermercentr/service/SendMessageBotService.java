package info.fermercentr.service;

import info.fermercentr.service.constants.SendMessageText;
import info.fermercentr.store.SessionData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

public class SendMessageBotService {

    ButtonsBotService button = new ButtonsBotService();

    public SendMessage createSimpleMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(text);
        return sendMessage;
    }

    //Простое сообщение с меню
    private SendMessage createMessageWithKeyboard(Update update, String text, ReplyKeyboardMarkup keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

    public SendMessage startMessage(Update update) {
        return createSimpleMessage(update, "Добрый день, " + update.getMessage().getFrom().getFirstName() + "!");
    }

    public SendMessage accessDenied(Update update) {
        return createSimpleMessage(update, SendMessageText.ACCESS_DENIED);
    }

    public SendMessage enterClientId(Update update) {
        return createSimpleMessage(update,
                update.getMessage().getFrom().getFirstName()
                        + ", " + SendMessageText.ENTER_CLIENT_ID);
    }

    public SendMessage blockMessage(Update update) {
        return createMessageWithKeyboard(update,
                SendMessageText.BLOCK_MESSAGE, button.createBlockMessage());
    }

    public SendMessage dateMessage(Update update) {
        SendMessage message = createSimpleMessage(update, SendMessageText.DATE_MESSAGE);
        message.setReplyMarkup(new ReplyKeyboardRemove(true));
        return message;
    }

    public SendMessage timeMessage(Update update) {
        return createSimpleMessage(update, SendMessageText.TIME_MESSAGE);
    }

    public SendMessage resultMessage(Update update, long userId, SessionData sd) {
        return createSimpleMessage(update, "Вы ввели:\n" + sd.getOrder(userId).toString());
    }

    public SendMessage invalidDate(Update update) {
        return createSimpleMessage(update, SendMessageText.DATE_INVALID);
    }

    public SendMessage invalidTime(Update update) {
        return createSimpleMessage(update, SendMessageText.TIME_INVALID);
    }
}
