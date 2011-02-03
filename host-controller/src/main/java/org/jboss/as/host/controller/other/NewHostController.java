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

import org.jboss.as.controller.ModelController;
import org.jboss.as.domain.client.api.ServerStatus;
import org.jboss.msc.service.ServiceName;

/**
 * @author Emanuel Muckenhuber
 */
public interface NewHostController extends ModelController {

    ServiceName SERVICE_NAME = ServiceName.JBOSS.append("host", "controller");

    /**
     * Get the host name.
     *
     * @return the host name
     */
    String getName();

    /**
     * Start a local server.
     *
     * @param serverName the server name to start
     * @return the server state
     */
    ServerStatus startServer(final String serverName);

    /**
     * Restart a local server.
     *
     * @param serverName the server name
     * @return the server state
     */
    ServerStatus restartServer(final String serverName);

    /**
     * Stop a local server.
     *
     * @param serverName the server name to stop
     * @return the server state
     */
    ServerStatus stopServer(final String serverName);

}
