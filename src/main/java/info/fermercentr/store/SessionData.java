package info.fermercentr.store;

import info.fermercentr.model.Order;
import info.fermercentr.model.Steps;

import java.util.HashMap;
import java.util.Map;

public class SessionData {

    private Map<Long, Order> sessionData = new HashMap<>();

    public void updateSessionData(final long userId, final Order order) {
        sessionData.put(userId, order);
    }

    public Order getOrder(final long userId) {
        return sessionData.get(userId);
    }

    public boolean contains(final long userId) {
        return sessionData.containsKey(userId);
    }

}
