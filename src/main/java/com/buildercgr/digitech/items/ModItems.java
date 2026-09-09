package com.buildercgr.digitech.items;

import com.buildercgr.digitech.Digitech;
import com.buildercgr.digitech.items.custom.Camera;
import com.buildercgr.digitech.items.custom.CaseItem;
import com.buildercgr.digitech.items.custom.pendrive;
import com.buildercgr.digitech.items.custom.phone;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.item.Item;

public class ModItems{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Digitech.MODID);

    public static final DeferredItem <Item> MICROCHIP = ITEMS.registerItem(
            "microchip",
            Item::new,
            new Item.Properties()
    );


    public static final DeferredItem <Item> PLASTIC = ITEMS.registerItem(
            "plastic",
            Item::new,
            new Item.Properties()
    );


    public static final DeferredItem<Item> PHONE = ITEMS.register(
            "phone",  () -> new phone (new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> RED_CASE = ITEMS.register(
            "red_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 1)
    );

    public static final DeferredItem<Item> BLUE_CASE = ITEMS.register(
            "blue_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 2)
    );

    public static final DeferredItem<Item> GREEN_CASE = ITEMS.register(
            "green_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 3)
    );

    public static final DeferredItem<Item> PINK_CASE = ITEMS.register(
            "pink_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 4)
    );

    public static final DeferredItem<Item> YELLOW_CASE = ITEMS.register(
            "yellow_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 5)
    );

    public static final DeferredItem<Item> ORANGE_CASE = ITEMS.register(
            "orange_case", () -> new CaseItem (new Item.Properties().stacksTo(1), 6)
    );

    public static final DeferredItem<Item> DIGITECH_OS_DISK = ITEMS.registerItem(
            "digitech_os_disk",
            Item::new,
            new Item.Properties().stacksTo(1)
    );

    public static final DeferredItem<Item> CAMERA = ITEMS.register(
            "camera", ()-> new Camera(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> PENDRIVE = ITEMS.register(
            "pendrive", () -> new pendrive(new Item.Properties().stacksTo(1))
    );
}
