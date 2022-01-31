package info.fermercentr.service;

import info.fermercentr.service.constants.Buttons;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static info.fermercentr.service.ButtonsBotBuilder.createRowButtonList;
import static info.fermercentr.service.ButtonsBotBuilder.createRowButtons;
import static info.fermercentr.service.ButtonsBotBuilder.setKeyboardMessage;

public class ButtonsBotService {

    public ReplyKeyboardMarkup createBlockMessage() {
        return setKeyboardMessage(
                createRowButtonList(
                        createRowButtons(Arrays.asList(Buttons.BLOCK, Buttons.UNBLOCK))));
    }

    public ReplyKeyboardMarkup createSelectMessage() {
        return setKeyboardMessage(
                createRowButtonList(
                        createRowButtons(Arrays.asList(Buttons.CANCEL, Buttons.OK))));
    }

    public ReplyKeyboardMarkup createStartButtonAlt() {
        List<String> oneButton = new ArrayList<>();
        oneButton.add(Buttons.START);
        return setKeyboardMessage(
                createRowButtonList(
                        createRowButtons(oneButton)));
    }

}
