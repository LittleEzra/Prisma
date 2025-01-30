package com.feliscape.prisma.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class CrystalLanternBlock extends Block implements SimpleWaterloggedBlock {
    public static final MapCodec<LanternBlock> CODEC = simpleCodec(LanternBlock::new);

    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape AABB = Block.box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0);
    protected static final VoxelShape HANGING_AABB = Block.box(6.0, 10.0, 6.0, 10.0, 14.0, 10.0);

    @Override
    public MapCodec<LanternBlock> codec() {
        return CODEC;
    }

    public CrystalLanternBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pBlockPlaceContext) {
        FluidState fluidstate = pBlockPlaceContext.getLevel().getFluidState(pBlockPlaceContext.getClickedPos());

        for (Direction direction : pBlockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.valueOf(direction == Direction.UP));
                if (blockstate.canSurvive(pBlockPlaceContext.getLevel(), pBlockPlaceContext.getClickedPos())) {
                    return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
                }
            }
        }

        return null;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pCollisionContext) {
        return pState.getValue(HANGING) ? HANGING_AABB : AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HANGING, WATERLOGGED);
    }

    @Override
    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pBlockPos) {
        Direction direction = getConnectedDirection(pState).getOpposite();
        return Block.canSupportCenter(pLevel, pBlockPos.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    protected BlockState updateShape(
            BlockState pState,
            LevelReader pLevel,
            ScheduledTickAccess pTickAccess,
            BlockPos pBLockPos,
            Direction pDirection,
            BlockPos pBlockPos,
            BlockState pBlockState,
            RandomSource pRandom
    ) {
        if (pState.getValue(WATERLOGGED)) {
            pTickAccess.scheduleTick(pBLockPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return getConnectedDirection(pState).getOpposite() == pDirection && !pState.canSurvive(pLevel, pBLockPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pLevel, pTickAccess, pBLockPos, pDirection, pBlockPos, pBlockState, pRandom);
    }

    @Override
    protected FluidState getFluidState(BlockState p_153492_) {
        return p_153492_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_153492_);
    }

    @Override
    protected boolean isPathfindable(BlockState p_153469_, PathComputationType p_153472_) {
        return false;
    }
}
