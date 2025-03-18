package com.myevent.common.listener;

import org.hibernate.event.spi.PostCollectionRemoveEvent;
import org.hibernate.event.spi.PostCollectionRemoveEventListener;
import org.hibernate.event.spi.PostCollectionUpdateEvent;
import org.hibernate.event.spi.PostCollectionUpdateEventListener;
import org.hibernate.event.spi.PreCollectionRemoveEvent;
import org.hibernate.event.spi.PreCollectionRemoveEventListener;
import org.hibernate.event.spi.PreCollectionUpdateEvent;
import org.hibernate.event.spi.PreCollectionUpdateEventListener;

import static org.springframework.data.history.RevisionMetadata.RevisionType.DELETE;
import static org.springframework.data.history.RevisionMetadata.RevisionType.UPDATE;


public class CustomEnversCollectionEventListenerImpl implements PreCollectionUpdateEventListener, PostCollectionUpdateEventListener, PreCollectionRemoveEventListener, PostCollectionRemoveEventListener {

    @Override
    public void onPostRemoveCollection(PostCollectionRemoveEvent event) {
        ContextLookup.setCurrentEntity(event.getAffectedOwnerOrNull());
        ContextLookup.setCurrentRevisionType(DELETE);
    }

    @Override
    public void onPostUpdateCollection(PostCollectionUpdateEvent event) {
        ContextLookup.setCurrentEntity(event.getAffectedOwnerOrNull());
        ContextLookup.setCurrentRevisionType(UPDATE);
    }

    @Override
    public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
        ContextLookup.setCurrentEntity(event.getAffectedOwnerOrNull());
        ContextLookup.setCurrentRevisionType(DELETE);
    }

    @Override
    public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
        ContextLookup.setCurrentEntity(event.getAffectedOwnerOrNull());
        ContextLookup.setCurrentRevisionType(UPDATE);
    }

}
