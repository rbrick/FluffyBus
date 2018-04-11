package io.rcw.event.caller;

import io.rcw.event.Call;
import io.rcw.event.Caller;
import io.rcw.event.EventBus;
import io.rcw.event.EventConfiguration;

public final class DefaultCaller implements Caller {
    private EventBus bus;

    public DefaultCaller(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public Call newCall() {
        return new DefaultCall(new EventConfiguration(), bus);
    }
}
