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
package org.openhab.io.homekit.internal.accessories;

import org.eclipse.smarthome.core.items.GenericItem;
import org.eclipse.smarthome.core.items.GroupItem;
import org.eclipse.smarthome.core.items.Item;
import org.eclipse.smarthome.core.items.ItemRegistry;
import org.openhab.io.homekit.internal.HomekitAccessoryUpdater;
import org.openhab.io.homekit.internal.HomekitTaggedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.hapjava.HomekitAccessory;

/**
 * Abstract class for HomekitAccessory implementations, this provides the
 * accessory metadata using information from the underlying Item.
 *
 * @author Andy Lintner - Initial contribution
 */
abstract class AbstractHomekitAccessoryImpl<T extends GenericItem> implements HomekitAccessory {

    private final int accessoryId;
    private final String itemName;
    private final String itemLabel;
    private final ItemRegistry itemRegistry;
    private final HomekitAccessoryUpdater updater;

    protected Logger logger = LoggerFactory.getLogger(AbstractHomekitAccessoryImpl.class);

    @SuppressWarnings("null")
    public AbstractHomekitAccessoryImpl(HomekitTaggedItem taggedItem, ItemRegistry itemRegistry,
            HomekitAccessoryUpdater updater, Class<T> expectedItemClass) {
        this.accessoryId = taggedItem.getId();
        this.itemName = taggedItem.getItem().getName();
        this.itemLabel = taggedItem.getItem().getLabel();
        this.itemRegistry = itemRegistry;
        this.updater = updater;
        Item baseItem = taggedItem.getItem();
        if (baseItem instanceof GroupItem && ((GroupItem) baseItem).getBaseItem() != null) {
            baseItem = ((GroupItem) baseItem).getBaseItem();
        }
        if (expectedItemClass != taggedItem.getItem().getClass()
                && !expectedItemClass.isAssignableFrom(baseItem.getClass())) {
            logger.warn("Item {} is of type {} instead of the expected {}", taggedItem.getItem().getName(),
                    baseItem.getClass().getName(), expectedItemClass.getName());
        }
    }

    @Override
    public int getId() {
        return accessoryId;
    }

    @Override
    public String getLabel() {
        return itemLabel;
    }

    @Override
    public String getManufacturer() {
        return "none";
    }

    @Override
    public String getModel() {
        return "none";
    }

    @Override
    public String getSerialNumber() {
        return "none";
    }

    @Override
    public void identify() {
        // We're not going to support this for now
    }

    protected ItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    protected String getItemName() {
        return itemName;
    }

    protected HomekitAccessoryUpdater getUpdater() {
        return updater;
    }

    protected GenericItem getItem() {
        return (GenericItem) getItemRegistry().get(getItemName());
    }
}
