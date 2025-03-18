package com.myevent.common.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.history.RevisionMetadata.RevisionType;
import org.springframework.stereotype.Component;

@Component
public class ContextLookup implements ApplicationContextAware {

    private static ApplicationContext sApplicationContext;

    @Getter
    @Setter
    private static RevisionType currentRevisionType;

    @Getter
    @Setter
    private static Object currentEntity;

    @Override
    public void setApplicationContext( ApplicationContext aApplicationContext ) throws BeansException {
        setContext( aApplicationContext );
    }

    public static void setContext( ApplicationContext aApplicationContext ) {
        sApplicationContext = aApplicationContext;
    }

    protected static ApplicationContext getApplicationContext() {
        return sApplicationContext;
    }

    public static Object getBean( String aName ) {
        if ( sApplicationContext != null ) {
            return sApplicationContext.getBean( aName );
        }
        return null;
    }

    public static <T> T getBean( Class<T> aClass ) {
        if ( sApplicationContext != null ) {
            return sApplicationContext.getBean( aClass );
        }
        return null;
    }

}
