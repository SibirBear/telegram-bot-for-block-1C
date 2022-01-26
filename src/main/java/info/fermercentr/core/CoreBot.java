package info.fermercentr.core;

import info.fermercentr.config.Config;
import info.fermercentr.model.OData;
import info.fermercentr.model.Order;
import info.fermercentr.model.Steps;
import info.fermercentr.service.CheckDateTime;
import info.fermercentr.service.DataBaseService;
import info.fermercentr.service.SendMessageBotService;
import info.fermercentr.store.SessionData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static info.fermercentr.core.CoreBotConstants.*;
import static info.fermercentr.model.Steps.STEP5;
import static info.fermercentr.service.constants.Buttons.BLOCK;
import static info.fermercentr.service.constants.Buttons.UNBLOCK;

public class CoreBot extends TelegramLongPollingBot {

    private final String token = Config.getConfigTelegramSettings().getToken();
    private final String botName = Config.getConfigTelegramSettings().getBotName();
    private final SendMessageBotService sendMessageBotService = new SendMessageBotService();
    private final SessionData sd = new SessionData();
    private final DataBaseService dbs = new DataBaseService();

    @Override
    public synchronized void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getChatId() == -707127414) {
                if (START.equals(update.getMessage().getText())) {
                    executeMessage(sendMessageBotService.startMessage(update));
                    executeMessage(sendMessageBotService.createSimpleMessage(update, "Ваш id: " + update.getMessage().getFrom().getId()));
                }
                scenarioMessageText(update);
            } else {
                executeMessage(sendMessageBotService.accessDenied(update));
            }
        }
    }

    private void scenarioMessageText(Update update) {
        long userId = update.getMessage().getFrom().getId();

        if (!sd.contains(userId)) {
            sd.updateSessionData(userId, new Order(Steps.STEP1));
        }

        Steps currentStep = sd.getOrder(userId).getCurrentStep();

        switch(currentStep) {
            case STEP1:
                executeMessage(sendMessageBotService.enterClientId(update));
                sd.getOrder(userId).setCurrentStep(Steps.STEP2);
                break;

            case STEP2:
                String idClient = update.getMessage().getText();
                List<String> data = dbs.getData(idClient);
                if (data.size() > 0) {
                    sd.getOrder(userId).setOdata(new OData(data.get(0), data.get(1), data.get(2)));
                    sd.getOrder(userId).setIdClient(idClient);
                    sd.getOrder(userId).setCurrentStep(Steps.STEP3);
                    executeMessage(sendMessageBotService.blockMessage(update));
                } else {
                    executeMessage(sendMessageBotService.enterClientIdError(update));
                }
                break;

            case STEP3:
                String selected = update.getMessage().getText();
                switch (selected) {
                    case BLOCK:
                        sd.getOrder(userId).setActionBlock(true);
                        sd.getOrder(userId).setCurrentStep(Steps.STEP4);
                        executeMessage(sendMessageBotService.dateMessage(update));
                        break;

                    case UNBLOCK:
                        sd.getOrder(userId).setActionBlock(false);
                        sd.getOrder(userId).setCurrentStep(Steps.STEP4);
                        executeMessage(sendMessageBotService.dateMessage(update));
                        break;
                }
                break;

            case STEP4:
                String date = update.getMessage().getText();
                if (CheckDateTime.validate(date)) {
                    sd.getOrder(userId).setDate(date);
                    if (sd.getOrder(userId).isActionBlock()) {
                        sd.getOrder(userId).setCurrentStep(STEP5);
                        executeMessage(sendMessageBotService.timeMessage(update));
                    } else {
                        sd.getOrder(userId).setCurrentStep(Steps.STEP6);
                        executeMessage(sendMessageBotService.resultMessage(update, userId, sd));
                        //подтверждение
                    }

                } else {
                    executeMessage(sendMessageBotService.invalidDate(update));
                }
                break;

            case STEP5:
                String time = update.getMessage().getText();
                if (CheckDateTime.validate(time, sd.getOrder(userId).getDate())) {
                    sd.getOrder(userId).setTime(time);
                    sd.getOrder(userId).setCurrentStep(Steps.STEP6);
                    executeMessage(sendMessageBotService.resultMessage(update, userId, sd));
                    //подтверждение
                } else {
                    executeMessage(sendMessageBotService.invalidTime(update));
                }
                break;

            case STEP6:
                executeMessage(sendMessageBotService.createSimpleMessage(update, "КОНЕЦ СКРИПТА"));
                break;
        }
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
