package com.myevent.common.listener;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;
import static org.hibernate.event.spi.EventType.POST_COLLECTION_UPDATE;
import static org.hibernate.event.spi.EventType.PRE_COLLECTION_UPDATE;
import static org.hibernate.event.spi.EventType.PRE_DELETE;
import static org.hibernate.event.spi.EventType.PRE_INSERT;
import static org.hibernate.event.spi.EventType.PRE_UPDATE;

@Component
@RequiredArgsConstructor
public class EnversListenerConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        EnversService enversService = sessionFactory.getServiceRegistry().getService(EnversService.class);

        if (nonNull(registry)) {
            registry.getEventListenerGroup(PRE_INSERT).appendListener(new CustomEnversEventListenerImpl());
            registry.getEventListenerGroup(PRE_UPDATE).appendListener(new CustomEnversEventListenerImpl());
            registry.getEventListenerGroup(PRE_DELETE).appendListener(new CustomEnversEventListenerImpl());

            registry.getEventListenerGroup(POST_COLLECTION_UPDATE).appendListener(new CustomEnversCollectionEventListenerImpl());
            registry.getEventListenerGroup(PRE_COLLECTION_UPDATE).appendListener(new CustomEnversCollectionEventListenerImpl());
        }
    }
}
