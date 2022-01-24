package info.fermercentr.store;

import info.fermercentr.model.Order;

import java.util.HashMap;
import java.util.Map;

public class SessionData {

    private Map<Long, Order> sessionData = new HashMap<>();

    public void updateSessionData(final long chatId, final Order order) {
        sessionData.put(chatId, order);
    }

    public Order getOrder(final long chatId) {
        return sessionData.get(chatId);
    }

}
