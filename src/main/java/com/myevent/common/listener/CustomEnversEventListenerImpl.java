package com.myevent.common.listener;

import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import static org.springframework.data.history.RevisionMetadata.RevisionType.DELETE;
import static org.springframework.data.history.RevisionMetadata.RevisionType.INSERT;
import static org.springframework.data.history.RevisionMetadata.RevisionType.UPDATE;

public class CustomEnversEventListenerImpl implements PreInsertEventListener, PreUpdateEventListener, PreDeleteEventListener {

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        ContextLookup.setCurrentEntity(event.getEntity());
        ContextLookup.setCurrentRevisionType(INSERT);
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        ContextLookup.setCurrentEntity(event.getEntity());
        ContextLookup.setCurrentRevisionType(UPDATE);
        return false;
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        ContextLookup.setCurrentEntity(event.getEntity());
        ContextLookup.setCurrentRevisionType(DELETE);
        return false;
    }

}
