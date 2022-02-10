package info.fermercentr.service;

import info.fermercentr.service.constants.SendMessageText;
import info.fermercentr.store.SessionData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

public class SendMessageBotService {

    private ButtonsBotService button = new ButtonsBotService();

    //Простое сообщение
    private SendMessage createSimpleMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        return sendMessage;
    }

    //Простое сообщение с кнопками
    private SendMessage createMessageWithKeyboard(Update update, String text, ReplyKeyboardMarkup keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

    //Сообщения для STEPS
    public SendMessage startMessage(Update update) {
        return createSimpleMessage(update, "Добрый день, " + update.getMessage().getFrom().getFirstName() + "!");
    }

    public SendMessage accessDenied(Update update) {
        return createSimpleMessage(update, SendMessageText.ACCESS_DENIED + " " + update.getMessage().getChatId());
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
        return createSimpleMessage(update, SendMessageText.DATE_MESSAGE);
    }

    public SendMessage timeMessage(Update update) {
        return createSimpleMessage(update, SendMessageText.TIME_MESSAGE);
    }

    public SendMessage invalidDate(Update update) {
        return createSimpleMessage(update, SendMessageText.DATE_INVALID);
    }

    public SendMessage invalidTime(Update update) {
        return createSimpleMessage(update, SendMessageText.TIME_INVALID);
    }

    public SendMessage enterClientIdError(Update update) {
        return createSimpleMessage(update, SendMessageText.ENTER_CLIENT_ID_ERROR);
    }

    public SendMessage resultMessage(Update update, long userId, SessionData sd) {
        return createMessageWithKeyboard(update,
                "Вы ввели:\n" + sd.getOrder(userId).toString() + "\n\nОтправить?",
                button.createSelectMessage());
    }

    public SendMessage sendingMayTakeTime(Update update) {
        return createSimpleMessage(update, SendMessageText.SENDING_MAY_TAKE_SOME_TIME);
    }

    public SendMessage successSending(Update update) {
        return createSimpleMessage(update, SendMessageText.SUCCESS_SENDING);
    }

    public SendMessage transferError(Update update) {
        return createSimpleMessage(update, SendMessageText.TRANSFER_ERROR);
    }

    public SendMessage cancelMessage(Update update) {
        return createSimpleMessage(update, SendMessageText.CANCEL);
    }

    public SendMessage endMessage(Update update) {
        return createMessageWithKeyboard(update,
                SendMessageText.END_STEPS, button.createStartButtonAlt());
    }

    public SendMessage clientName(Update update, String idClient, String name) {
        return createSimpleMessage(update, "Вы выбрали: " + idClient + " " + name);
    }

    public SendMessage errorMessage(Update update) {
        return createSimpleMessage(update, SendMessageText.ERROR);
    }
}
