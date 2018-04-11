package io.rcw.event.caller;

import io.rcw.event.Call;
import io.rcw.event.ClassInvocationHandler;
import io.rcw.event.EventBus;
import io.rcw.event.EventConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public final class DefaultCall implements Call {

    private EventConfiguration configuration;
    private EventBus bus;

    public DefaultCall(EventConfiguration configuration, EventBus bus) {
        this.configuration = configuration;
        this.bus = bus;
    }

    @Override
    public EventConfiguration configure() {
        return configuration;
    }

    @Override
    public void call(Object... parameters) throws Exception {
       Map<Class<?>, ClassInvocationHandler> eventMap = bus.getEventMap();
       for (ClassInvocationHandler handler : eventMap.values()) {
           handler.filteredMethods(configuration).forEach(method -> {
               try {
                   method.invoke(handler.getInstance(), parameters);
               } catch (IllegalAccessException | InvocationTargetException e) {
                   e.printStackTrace();
               }
           });
       }
    }
}
