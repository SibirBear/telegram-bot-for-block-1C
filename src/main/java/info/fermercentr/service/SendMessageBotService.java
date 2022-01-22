package info.fermercentr.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static java.lang.Math.toIntExact;

public class SendMessageBotService {

    ButtonsBotService button = new ButtonsBotService();

    public SendMessage createSimpleMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage createMessageWithKeyboard(Update update, String text) {
        SendMessage sendMessage = createSimpleMessage(update, text);
        sendMessage.setReplyMarkup(button.keyboardNineNumber());
        return sendMessage;
    }

    public EditMessageText editSimpleMessage(Update update, String text) {
        EditMessageText message = new EditMessageText();
        message.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
        message.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        message.setText(text);
        return message;
    }

    public EditMessageText editMessageWithKeyboard(Update update, String text, InlineKeyboardMarkup keyboard) {
        EditMessageText message = editSimpleMessage(update, text);
        message.setReplyMarkup(keyboard);
        return message;
    }

    public EditMessageText editNinekeyboard(Update update, String result) {
        return editMessageWithKeyboard(update,
                "Введите ЦФУ, нажав на кнопки ниже\nВы ввели: " + result,
                button.keyboardNineNumber());
    }

    public EditMessageText editNinekeyboardEnd(Update update, String result) {
        return editSimpleMessage(update,
                "Вы указали ЦФУ: " + result);
    }

}
