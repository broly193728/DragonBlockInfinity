package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.block.BlockNamekSapling;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DBIBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, DragonBlockInfinity.MOD_ID);

    // ===== NAMEK BLOCKS =====
    public static final RegistryObject<Block> NAMEK_DIRT = BLOCKS.register("namek_dirt",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> NAMEK_GRASS = BLOCKS.register("namek_grass",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<RotatedPillarBlock> NAMEK_LOG = BLOCKS.register("namek_log",
        () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<LeavesBlock> NAMEK_LEAVES = BLOCKS.register("namek_leaves",
        () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> NAMEK_SAPLING = BLOCKS.register("namek_sapling",
        () -> new BlockNamekSapling());

    public static final RegistryObject<Block> NAMEK_TOP = BLOCKS.register("namek_top",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> NAMEK_SIDE = BLOCKS.register("namek_side",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    // ===== PLUS BLOCKS =====
    public static final RegistryObject<Block> PLUS_WHITE = BLOCKS.register("plus_white",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK)));

    public static final RegistryObject<Block> PLUS_BLACK = BLOCKS.register("plus_black",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));

    public static final RegistryObject<Block> PLUS_RED = BLOCKS.register("plus_red",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));

    public static final RegistryObject<Block> PLUS_YELLOW = BLOCKS.register("plus_yellow",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));
}
