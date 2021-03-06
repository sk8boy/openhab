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

import static org.openhab.binding.mymiio.internal.MyMiIOBindingConstants.*;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link MyMiIOHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author zaoweiceng - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.mymiio", service = ThingHandlerFactory.class)
public class MyMiIOHandlerFactory extends BaseThingHandlerFactory {

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        Logger log = LoggerFactory.getLogger(MyMiIOHandlerFactory.class);
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();
        log.info("start MyMiIOHandlerFactory + {} + {} + {}", thingTypeUID.toString(), thingTypeUID.getId(), thingTypeUID.getBindingId());
        if (thingTypeUID.equals(THING_TYPE_BASIC)){
            return new MyMiIOHandler(thing);
        }
        if (thingTypeUID.equals(THING_TYPE_MIIO)) {
            return new MiIoGenericHandler(thing);
        }
        return null;
    }
}
