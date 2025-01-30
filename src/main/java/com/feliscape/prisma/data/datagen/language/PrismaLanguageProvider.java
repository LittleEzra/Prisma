package com.feliscape.prisma.data.datagen.language;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.registry.PrismaItems;
import com.feliscape.prisma.registry.PrismaMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class PrismaLanguageProvider extends LanguageProvider {
    public PrismaLanguageProvider(PackOutput output, String locale) {
        super(output, Prisma.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        this.addItem(PrismaItems.BOREALITE_CRYSTAL, "Borealite Crystal");
        this.addItem(PrismaItems.AUSTRALITE_CRYSTAL, "Australite Crystal");
        this.addItem(PrismaItems.PRISMATIC_DUST, "Prismatic Dust");

        this.addItem(PrismaItems.CRYSTAL_MELON_SLICE, "Crystal Melon Slice");

        this.addBlockAndItem(PrismaBlocks.AUSTRALITE_BLOCK, "Block of Australite");
        this.addBlockAndItem(PrismaBlocks.BOREALITE_BLOCK, "Block of Borealite");
        this.addBlockAndItem(PrismaBlocks.PRISMA_LEAVES, "Prisma Leaves");

        this.addBlockAndItem(PrismaBlocks.WHITE_RUGGED_WOOL, "White Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.LIGHT_GRAY_RUGGED_WOOL, "Light Gray Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.GRAY_RUGGED_WOOL, "Gray Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.BLACK_RUGGED_WOOL, "Black Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.BROWN_RUGGED_WOOL, "Brown Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.RED_RUGGED_WOOL, "Red Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.ORANGE_RUGGED_WOOL, "Orange Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.YELLOW_RUGGED_WOOL, "Yellow Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.LIME_RUGGED_WOOL, "Lime Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.GREEN_RUGGED_WOOL, "Green Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.CYAN_RUGGED_WOOL, "Cyan Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.LIGHT_BLUE_RUGGED_WOOL, "Light Blue Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.BLUE_RUGGED_WOOL, "Blue Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.PURPLE_RUGGED_WOOL, "Purple Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.MAGENTA_RUGGED_WOOL, "Magenta Rugged Wool");
        this.addBlockAndItem(PrismaBlocks.PINK_RUGGED_WOOL, "Pink Rugged Wool");

        this.addBlockAndItem(PrismaBlocks.PRISMA_LOG, "Prisma Log");
        this.addBlockAndItem(PrismaBlocks.STRIPPED_PRISMA_LOG, "Stripped Prisma Log");
        this.addBlockAndItem(PrismaBlocks.PRISMA_WOOD, "Prisma Wood");
        this.addBlockAndItem(PrismaBlocks.STRIPPED_PRISMA_WOOD, "Stripped Prisma Wood");
        this.addBlockAndItem(PrismaBlocks.PRISMA_PLANKS, "Prisma Planks");
        this.addBlockAndItem(PrismaBlocks.PRISMA_STAIRS, "Prisma Stairs");
        this.addBlockAndItem(PrismaBlocks.PRISMA_SLAB, "Prisma Slab");
        this.addBlockAndItem(PrismaBlocks.PRISMA_FENCE, "Prisma Fence");
        this.addBlockAndItem(PrismaBlocks.PRISMA_FENCE_GATE, "Prisma Fence Gate");
        this.addBlockAndItem(PrismaBlocks.PRISMA_BUTTON, "Prisma Button");
        this.addBlockAndItem(PrismaBlocks.PRISMA_PRESSURE_PLATE, "Prisma Pressure Plate");
        this.addBlockAndItem(PrismaBlocks.PRISMA_DOOR, "Prisma Door");
        this.addBlockAndItem(PrismaBlocks.PRISMA_TRAPDOOR, "Prisma Trapdoor");

        this.addBlockAndItem(PrismaBlocks.AUSTRALITE_LANTERN, "Australite Lantern");
        this.addBlockAndItem(PrismaBlocks.BOREALITE_LANTERN, "Borealite Lantern");

        this.addBlock(PrismaBlocks.PRISMA_SIGN, "Prisma Sign");
        this.addBlock(PrismaBlocks.PRISMA_WALL_SIGN, "Prisma Wall Sign");
        this.addBlock(PrismaBlocks.PRISMA_HANGING_SIGN, "Prisma Hanging Sign");
        this.addBlock(PrismaBlocks.PRISMA_WALL_HANGING_SIGN, "Prisma Wall Hanging Sign");

        this.addItem(PrismaItems.PRISMA_SIGN, "Prisma Sign");
        this.addItem(PrismaItems.PRISMA_HANGING_SIGN, "Prisma Hanging Sign");

        this.addBlockAndItem(PrismaBlocks.PRISMA_SAPLING, "Prisma Sapling");
        this.addBlock(PrismaBlocks.POTTED_PRISMA_SAPLING, "Potted Prisma Sapling");
        this.addAdvancement("eat_crystal_melon",
                "Mouthful of Precious Stones",
                "Get through a Crystal Melon Slice"
                );
        this.addAdvancement("get_aurora_crystal",
                "A Piece of Night",
                "Obtain an Aurora Crystal"
                );
        this.addMobEffect(PrismaMobEffects.CLAIRVOYANCE, "Clairvoyance");
    }

    public void addBlockAndItem(Supplier<? extends Block> key, String name) {
        this.addBlock(key, name);
        this.addItem(key.get()::asItem, name);
    }

    private void addItemTooltip(Supplier<? extends Item> key, String name) {
        add(key.get().getDescriptionId() + ".tooltip", name);
    }
    private void addMobEffect(Supplier<? extends MobEffect> key, String name) {
        add(key.get().getDescriptionId(), name);
    }
    private void addMobEffect(Holder<? extends MobEffect> key, String name) {
        add(key.value().getDescriptionId(), name);
    }
    private void addSubtitle(Supplier<SoundEvent> key, String name) {
        add("subtitle.{}.{}".formatted(Prisma.MOD_ID, key.get().location().getPath()), name);
    }
    private void addAdvancement(String id, String title, String description) {
        add("advancements.%s.%s.title".formatted(Prisma.MOD_ID, id), title);
        add("advancements.%s.%s.description".formatted(Prisma.MOD_ID, id), description);
    }
}
