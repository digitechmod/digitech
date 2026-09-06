package com.buildercgr.digitech.items.custom;

import com.buildercgr.digitech.items.ModItems;
import com.buildercgr.digitech.dataComponents.ModDataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class CaseItem extends Item {
    private int CaseID = 0;

    public CaseItem(Properties p_41383_, int CaseID) {
        super(p_41383_);
        this.CaseID = CaseID;
    }
    public static Item getCaseById(int id) {
        return switch (id) {
            case 1 -> ModItems.RED_CASE.get();
            case 2 -> ModItems.BLUE_CASE.get();
            case 3 -> ModItems.GREEN_CASE.get();
            case 4 -> ModItems.PINK_CASE.get();
            case 5 -> ModItems.YELLOW_CASE.get();
            case 6 -> ModItems.ORANGE_CASE.get();
            default -> ModItems.PHONE.get();
        };
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!level.isClientSide && player != null) {
            for (int i = 0; i < player.getInventory().items.size(); i++) {
                ItemStack invStack = player.getInventory().items.get(i);
                if (invStack.is(ModItems.PHONE.get())) {
                    Integer currentCase = invStack.get(ModDataComponents.PHONECASE.get());

                    if (currentCase != null && currentCase != 0) {
                        ItemStack oldCase = new ItemStack(getCaseById(currentCase));
                        player.getInventory().add(oldCase);
                    }

                    invStack.set(ModDataComponents.PHONECASE.get(), CaseID);
                    player.getInventory().setItem(i, invStack);

                    stack.shrink(1);
                    if (stack.isEmpty()) {
                        player.setItemInHand(context.getHand(), ItemStack.EMPTY);
                    }

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }


}
