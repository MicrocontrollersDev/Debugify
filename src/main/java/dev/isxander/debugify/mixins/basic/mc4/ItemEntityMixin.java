package dev.isxander.debugify.mixins.basic.mc4;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@BugFix(id = "MC-4", category = FixCategory.BASIC, env = BugFix.Env.SERVER, description = "Item drops sometimes appear at the wrong location")
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void fixPosition(ItemEntity other, CallbackInfo ci) {
        this.setRequiresPrecisePosition(true);
    }
}
