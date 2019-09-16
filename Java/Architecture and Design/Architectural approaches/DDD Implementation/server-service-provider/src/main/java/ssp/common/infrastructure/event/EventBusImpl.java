package ssp.common.infrastructure.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import ssp.common.application.event.Event;
import ssp.common.application.event.EventBus;

@Component
public class EventBusImpl implements EventBus
{
    private final Map<Class<? extends Event>, List<Consumer<Event>>> eventConsumersMap = new HashMap<>();
    
    @Override
    public synchronized void publish(Event event)
    {
        List<Consumer<Event>> consumers = eventConsumersMap.get(event.getClass());
        if (consumers == null || consumers.isEmpty())
        {
            return;
        }
        consumers.forEach(consumer -> consumer.accept(event));
    }
    
    @Override
    public synchronized void subscribe(Class<? extends Event> eventType, Consumer<Event> subscriber)
    {
        List<Consumer<Event>> consumers = eventConsumersMap.get(eventType);
        
        if (consumers == null)
        {
            consumers = new ArrayList<>();
            eventConsumersMap.put(eventType, consumers);
        }
        
        consumers.add(subscriber);
    }
}
