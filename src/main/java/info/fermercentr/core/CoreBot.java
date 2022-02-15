package info.fermercentr.core;

import info.fermercentr.DB.DataBaseMSSQL;
import info.fermercentr.config.Config;
import info.fermercentr.model.OData;
import info.fermercentr.model.Order;
import info.fermercentr.model.Steps;
import info.fermercentr.service.CheckDateTime;
import info.fermercentr.service.SendMessageBotService;
import info.fermercentr.service.SendToODATA;
import info.fermercentr.store.SessionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static info.fermercentr.DB.DataBaseService.getData;
import static info.fermercentr.core.CoreBotConstants.START;
import static info.fermercentr.core.CoreBotConstants.START_ALT;
import static info.fermercentr.service.constants.Buttons.BLOCK;
import static info.fermercentr.service.constants.Buttons.OK;
import static info.fermercentr.service.constants.Buttons.UNBLOCK;

public class CoreBot extends TelegramLongPollingBot {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final String token = Config.getConfigTelegramSettings().getToken();
    private final String botName = Config.getConfigTelegramSettings().getBotName();
    private final Long groupId = Long.parseLong(Config.getConfigTelegramSettings().getGroup());
    private final SendMessageBotService sendMessageBotService = new SendMessageBotService();
    private final SessionData sd = new SessionData();

    @Override
    public synchronized void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!groupId.equals(update.getMessage().getChatId()))  {
                executeMessage(sendMessageBotService.accessDenied(update));
                log.error("[Core] - User " + update.getMessage().getFrom().getId()
                        + " " + update.getMessage().getFrom().getFirstName()
                        + " trying to start Bot.");
            } else {
                long userId = update.getMessage().getFrom().getId();
                if (START.equals(update.getMessage().getText()) || START_ALT.equals(update.getMessage().getText())) {
                    executeMessage(sendMessageBotService.startMessage(update));
                    sd.updateSessionData(userId, new Order(Steps.STEP1));
                }

                if (sd.contains(update.getMessage().getFrom().getId())) {
                    scenarioMessageText(update);
                }
            }
        }
    }

    private void scenarioMessageText(Update update) {
        long userId = update.getMessage().getFrom().getId();

        Steps currentStep = sd.getOrder(userId).getCurrentStep();

        switch (currentStep) {
            case STEP1:
                stepOne(update);
                break;

            case STEP2:
                stepTwo(update, userId);
                break;

            case STEP3:
                stepThree(update, userId);
                break;

            case STEP4:
                stepFour(update, userId);
                break;

            case STEP5:
                stepFive(update, userId);
                break;

            case STEP6:
                stepSix(update, userId);
                break;

            default: break;

        }

    }

    private void stepOne(Update update) {
        executeMessage(sendMessageBotService.enterClientId(update));
        sd.getOrder(update.getMessage().getFrom().getId()).setCurrentStep(Steps.STEP2);
    }

    private void stepTwo(Update update, long userId) {
        String idClient = update.getMessage().getText();
        List<String> data = Collections.synchronizedList(new ArrayList<>());

        if (isInteger(idClient)) {
            data = getData(idClient);
        }

        if (data.size() > 0) {
            sd.getOrder(userId).setOdata(new OData(data.get(0), data.get(1), data.get(2), data.get(3)));
            sd.getOrder(userId).setIdClient(idClient);
            sd.getOrder(userId).setCurrentStep(Steps.STEP3);
            executeMessage(sendMessageBotService.clientName(update, idClient, data.get(3)));
            executeMessage(sendMessageBotService.blockMessage(update));
        } else {
            executeMessage(sendMessageBotService.enterClientIdError(update));
        }
    }

    private void stepThree(Update update, long userId) {
        String selected = update.getMessage().getText();

        switch (selected) {
            case BLOCK:
                sd.getOrder(userId).setActionBlock(true);
                sd.getOrder(userId).setCurrentStep(Steps.STEP4);
                executeMessage(sendMessageBotService.dateMessage(update));
                break;

            case UNBLOCK:
                sd.getOrder(userId).setActionBlock(false);
                sd.getOrder(userId).setCurrentStep(Steps.STEP6);
                executeMessage(sendMessageBotService.resultMessage(update, userId, sd));
                break;

            default:
                executeMessage(sendMessageBotService.errorMessage(update));
                executeMessage(sendMessageBotService.blockMessage(update));
                break;
        }
    }

    private void stepFour(Update update, long userId) {
        String date = update.getMessage().getText();
        if (CheckDateTime.validate(date)) {
            sd.getOrder(userId).setDate(date);
            sd.getOrder(userId).setCurrentStep(Steps.STEP5);
            executeMessage(sendMessageBotService.timeMessage(update));
        } else {
            executeMessage(sendMessageBotService.invalidDate(update));
        }
    }

    private void stepFive(Update update, long userId) {
        String time = update.getMessage().getText();
        if (CheckDateTime.validate(time, sd.getOrder(userId).getDate())) {
            sd.getOrder(userId).setTime(time);
            sd.getOrder(userId).setCurrentStep(Steps.STEP6);
            executeMessage(sendMessageBotService.resultMessage(update, userId, sd));
        } else {
            executeMessage(sendMessageBotService.invalidTime(update));
        }
    }

    private void stepSix(Update update, long userId) {
        String approve = update.getMessage().getText();
        if (approve.equals(OK)) {
            executeMessage(sendMessageBotService.sendingMayTakeTime(update));
            if (SendToODATA.send(userId, sd)) {
                DataBaseMSSQL.insertDB(userId, sd);
                executeMessage(sendMessageBotService.successSending(update));
                makeLogEntry(update, sd);
            } else {
                executeMessage(sendMessageBotService.transferError(update));
            }

        } else {
            executeMessage(sendMessageBotService.cancelMessage(update));
        }

        sd.remove(userId);
        executeMessage(sendMessageBotService.endMessage(update));
    }

    private void makeLogEntry(final Update update, final SessionData sd) {
        final String username = update.getMessage().getFrom().getFirstName();
        final Order order = sd.getOrder(update.getMessage().getFrom().getId());
        log.info("[Core] - User " + username
                + " successfully send to 1C client data: " + order.getIdClient()
                + " next: " + order);
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("[Core] - Error sending message to bot. " + e.getMessage());
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
