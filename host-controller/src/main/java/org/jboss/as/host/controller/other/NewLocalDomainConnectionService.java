/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.host.controller.other;

import java.util.concurrent.CancellationException;

import org.jboss.as.controller.Cancellable;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ResultHandler;
import org.jboss.as.domain.controller.FileRepository;
import org.jboss.as.domain.controller.NewDomainController;
import org.jboss.as.domain.controller.NewHostControllerClient;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.value.InjectedValue;

/**
 * @author Emanuel Muckenhuber
 */
class NewLocalDomainConnectionService implements NewDomainControllerConnection, Service<NewDomainControllerConnection> {

    final InjectedValue<NewDomainController> domainController = new InjectedValue<NewDomainController>();
    private String name;

    /** {@inheritDoc} */
    @Override
    public synchronized ModelNode register(final NewHostController hostController) {
        assert hostController != null : "null HC";
        final NewDomainController domainController = this.domainController.getValue();
        this.name = hostController.getName();
        final NewHostControllerClient client = new NewLocalHostControllerClient(hostController);
        domainController.addClient(client);
        return domainController.getModel();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void unregister() {
        final NewDomainController domainController = this.domainController.getValue();
        domainController.removeClient(name);
    }

    /** {@inheritDoc} */
    @Override
    public synchronized FileRepository getRemoteFileRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void start(StartContext context) throws StartException {
        //
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void stop(StopContext context) {
        //
    }

    /** {@inheritDoc} */
    @Override
    public synchronized NewDomainControllerConnection getValue() throws IllegalStateException, IllegalArgumentException {
        return this;
    }

    InjectedValue<NewDomainController> getDomainController() {
        return domainController;
    }

    static class NewLocalHostControllerClient implements NewHostControllerClient {
        private final NewHostController controller;
        NewLocalHostControllerClient(final NewHostController controller) {
            this.controller = controller;
        }

        /** {@inheritDoc} */
        @Override
        public Cancellable execute(ModelNode operation, ResultHandler handler) {
            return this.controller.execute(operation, handler);
        }

        /** {@inheritDoc} */
        @Override
        public ModelNode execute(ModelNode operation) throws CancellationException, OperationFailedException {
            return this.controller.execute(operation);
        }

        /** {@inheritDoc} */
        @Override
        public String getId() {
            return controller.getName();
        }

        /** {@inheritDoc} */
        @Override
        public boolean isActive() {
            return true;
        }

    }

}
