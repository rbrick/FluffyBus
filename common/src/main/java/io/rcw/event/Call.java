package io.rcw.event;

public interface Call {
    EventConfiguration configure();
    void call(Object... parameters) throws Exception;
}
