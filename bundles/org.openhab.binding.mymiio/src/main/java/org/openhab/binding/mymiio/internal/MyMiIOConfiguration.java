/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.mymiio.internal;

/**
 * The {@link MyMiIOConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author zaoweiceng - Initial contribution
 */
public class MyMiIOConfiguration {

    /**
     * Sample configuration parameter. Replace with your own.
     */
    public String host;
    public String token;
    public String deviceId;
    public String model;
    public int refreshInterval;
    public int timeout;
}
