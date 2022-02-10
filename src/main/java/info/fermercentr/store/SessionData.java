package info.fermercentr.store;

import info.fermercentr.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionData {

    private final Map<Long, Order> sessionData = new HashMap<>();

    public void updateSessionData(final long userId, final Order order) {
        sessionData.put(userId, order);
    }

    public Order getOrder(final long userId) {
        return sessionData.get(userId);
    }

    public boolean contains(final long userId) {
        return sessionData.containsKey(userId);
    }

    public void remove(final long userId) {
        sessionData.keySet().removeIf(key -> key == userId);
    }

    @Override
    public String toString() {
        return sessionData.keySet().stream()
                .map(key -> key + "=" + sessionData.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
