package info.fermercentr.service;

import info.fermercentr.service.constants.Buttons;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;

import static info.fermercentr.service.ButtonsBotBuilder.*;

public class ButtonsBotService {

    public ReplyKeyboardMarkup createBlockMessage() {
        return setKeyboardMessage(
                createRowButtonList(
                        createRowButtons(Arrays.asList(Buttons.BLOCK, Buttons.UNBLOCK))));
    }

    public ReplyKeyboardMarkup createSelectMessage() {
        return setKeyboardMessage(
                createRowButtonList(
                        createRowButtons(Arrays.asList(Buttons.OK, Buttons.CANCEL))));
    }

}
