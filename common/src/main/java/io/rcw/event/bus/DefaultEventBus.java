package io.rcw.event.bus;

import io.rcw.event.BusConfiguration;
import io.rcw.event.Caller;
import io.rcw.event.ClassInvocationHandler;
import io.rcw.event.EventBus;
import io.rcw.event.caller.DefaultCaller;

import java.util.HashMap;
import java.util.Map;

public final class DefaultEventBus implements EventBus {
    private Map<Class<?>, ClassInvocationHandler> handlerMap = new HashMap<>();
    private BusConfiguration busConfiguration = new BusConfiguration();

    private Caller caller = new DefaultCaller(this);

    @Override
    public void register(Class<?> clazz) {
        /*
        if (busConfiguration.getInjector() != null) {
            ClassInvocationHandler handler = new ClassInvocationHandler(clazz, busConfiguration.getInjector());
            handlerMap.put(clazz, handler);
        } else { */
        try {
            ClassInvocationHandler handler = new ClassInvocationHandler(clazz);
            handlerMap.put(clazz, handler);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
//        }
    }

    @Override
    public void register(Object instance) {
        ClassInvocationHandler handler = new ClassInvocationHandler(instance);
        handlerMap.put(instance.getClass(), handler);
    }

    @Override
    public BusConfiguration configure() {
        return busConfiguration;
    }

    @Override
    public Caller caller() {
        return caller;
    }

    @Override
    public Map<Class<?>, ClassInvocationHandler> getEventMap() {
        return handlerMap;
    }
}
