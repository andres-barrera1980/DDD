package co.edu.javeriana.ddd.shared.domain.model;

import co.edu.javeriana.ddd.shared.domain.events.DomainEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> recordedEvents = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(recordedEvents);
    }
}
