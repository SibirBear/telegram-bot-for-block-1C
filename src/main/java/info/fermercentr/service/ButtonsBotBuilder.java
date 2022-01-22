package info.fermercentr.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonsBotBuilder {

    public static InlineKeyboardButton createInlineButton(String buttonText, String callback) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonText);
        inlineKeyboardButton.setCallbackData(callback);

        return inlineKeyboardButton;
    }

    public static List<InlineKeyboardButton> createInlineRowButton(InlineKeyboardButton... inlineButtons) {
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
        Collections.addAll(inlineKeyboardButtonList, inlineButtons);

        return inlineKeyboardButtonList;
    }

    @SafeVarargs
    public static InlineKeyboardMarkup createInlineKeyboard(List<InlineKeyboardButton>... rowButtonsList) {
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();
        Collections.addAll(inlineKeyboard, rowButtonsList);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboard);

        return inlineKeyboardMarkup;
    }


}
