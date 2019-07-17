package ssp.common.application.event;

import java.util.function.Consumer;

public interface EventBus
{
    <E extends Event> void publish(E event);
    
    void subscribe(Class<? extends Event> eventType, Consumer<Event> subscriber);
}
