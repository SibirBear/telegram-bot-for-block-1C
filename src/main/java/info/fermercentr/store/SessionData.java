package info.fermercentr.store;

import info.fermercentr.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public boolean remove(final long userId) {
        if (this.sessionData.containsKey(userId) || !this.sessionData.isEmpty()) {
            this.sessionData.remove(userId);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return sessionData.keySet().stream()
                .map(key -> key + "=" + sessionData.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
