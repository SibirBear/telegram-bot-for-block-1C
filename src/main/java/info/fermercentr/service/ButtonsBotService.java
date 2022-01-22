package info.fermercentr.service;

import info.fermercentr.core.CoreBotConstants;
import info.fermercentr.service.constants.Buttons;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static info.fermercentr.service.ButtonsBotBuilder.*;

public class ButtonsBotService {

    public InlineKeyboardMarkup keyboardNineNumber() {
        return createInlineKeyboard(
                createInlineRowButton(
                        createInlineButton(Buttons.ONE.getButtonName(), CoreBotConstants.ONE),
                        createInlineButton(Buttons.TWO.getButtonName(), CoreBotConstants.TWO),
                        createInlineButton(Buttons.THREE.getButtonName(), CoreBotConstants.THREE)
                ),
                createInlineRowButton(
                        createInlineButton(Buttons.FOUR.getButtonName(), CoreBotConstants.FOUR),
                        createInlineButton(Buttons.FIVE.getButtonName(), CoreBotConstants.FIVE),
                        createInlineButton(Buttons.SIX.getButtonName(), CoreBotConstants.SIX)
                ),
                createInlineRowButton(
                        createInlineButton(Buttons.SEVEN.getButtonName(), CoreBotConstants.SEVEN),
                        createInlineButton(Buttons.EIGHT.getButtonName(), CoreBotConstants.EIGHT),
                        createInlineButton(Buttons.NINE.getButtonName(), CoreBotConstants.NINE)
                ),
                createInlineRowButton(
                        createInlineButton(Buttons.ENTER.getButtonName(), CoreBotConstants.ENTER),
                        createInlineButton(Buttons.ZERO.getButtonName(), CoreBotConstants.ZERO),
                        createInlineButton(Buttons.DELETE.getButtonName(), CoreBotConstants.DELETE)
                )
        );
    }

}
