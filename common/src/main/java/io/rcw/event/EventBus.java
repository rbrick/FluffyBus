package io.rcw.event;


import io.rcw.event.bus.DefaultEventBus;

import java.util.Map;

public interface EventBus {
    void register(Class<?> clazz);
    void register(Object instance);

    BusConfiguration configure();

    Caller caller();

    Map<Class<?>, ClassInvocationHandler> getEventMap();

    static EventBus create() {
        return new DefaultEventBus();
    }
}
