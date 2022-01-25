package info.fermercentr.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonsBotBuilder {

    //Создание одного ряда с кнопками
    public static KeyboardRow createRowButtons(List<String> buttonsName) {
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String buttonName : buttonsName) {
            keyboardRow.add(new KeyboardButton(buttonName));
        }

        return keyboardRow;
    }

    //Создание списка с рядами кнопок
    public static List<KeyboardRow> createRowButtonList(KeyboardRow... keyboardRows) {
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        Collections.addAll(keyboardRowsList, keyboardRows);

        return keyboardRowsList;
    }

    //Создание клавиатуры для сообщения из списка с рядами кнопок
    public static ReplyKeyboardMarkup setKeyboardMessage(List<KeyboardRow> keyboardRow) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRow);
        return replyKeyboardMarkup;
    }


}
