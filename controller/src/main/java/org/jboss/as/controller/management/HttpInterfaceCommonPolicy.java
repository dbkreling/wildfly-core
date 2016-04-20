/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

package org.jboss.as.controller.management;

import java.util.List;

import org.xnio.OptionMap;

/**
 * Policy information for the HTTP management interface that is common across both standalone and domain mode.
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
public interface HttpInterfaceCommonPolicy {

    /**
     * Get the name of the security realm to secure the HTTP interface, or {@code null} if one has not been defined.
     *
     * @return Get the name of the security realm to secure the HTTP interface.
     */
    String getSecurityRealm();

    /**
     * Is the management console enabled, is set to {@code false} the console should not be made available.
     *
     * @return {@code true} if the management console should be made available, {@code false} otherwise.
     */
    boolean isConsoleEnabled();

    /**
     * Is upgrading to a Remoting connection over the HTTP interface enabled.
     *
     * @return {@code true} if HTTP Upgrade to the native protocol is enabled, {@code false} otherwise.
     */
    boolean isHttpUpgradeEnabled();

    /**
     * Get the connector options based on the current configuration.
     *
     * @return the connector options based on the current configuration.
     */
    OptionMap getConnectorOptions();

    /**
     * Get the list of origins that the server should accept requests from, if none set then all forms of cross origin resource sharing are disabled.
     *
     * An empty {@link List} or {@code null} both signal that cross origin resource sharing should be disabled.
     *
     * @return The list of origins that the server should accept requests from.
     */
    List<String> getAllowedOrigins();

}

