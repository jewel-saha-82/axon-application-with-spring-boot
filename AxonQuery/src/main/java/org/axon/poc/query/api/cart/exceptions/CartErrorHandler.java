package org.axon.poc.query.api.cart.exceptions;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class CartErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(Exception e, EventMessage<?> eventMessage, EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
