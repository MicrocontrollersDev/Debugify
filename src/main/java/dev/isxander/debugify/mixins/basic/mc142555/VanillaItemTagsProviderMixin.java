package dev.isxander.debugify.mixins.basic.mc142555;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;

// TODO: FIX
@BugFix(id = "MC-142555", category = FixCategory.BASIC, env = BugFix.Env.SERVER, description = "You cannot tempt or tame cats or ocelots using tropical fish")
@Mixin(VanillaItemTagsProvider.class)
public class VanillaItemTagsProviderMixin {
    @Definition(id = "tag", method = "Lnet/minecraft/data/tags/VanillaItemTagsProvider;tag(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/data/tags/TagAppender;")
    @Definition(id = "CAT_FOOD", field = "Lnet/minecraft/tags/ItemTags;CAT_FOOD:Lnet/minecraft/tags/TagKey;")
    @Definition(id = "add", method = "Lnet/minecraft/data/tags/TagAppender;add([Ljava/lang/Object;)Lnet/minecraft/data/tags/TagAppender;")
    @Expression("?.tag(CAT_FOOD).add(@(?))")
    @ModifyExpressionValue(method = "addTags", at = @At("MIXINEXTRAS:EXPRESSION"))
    private Item[] addTropicalFishToCat(Item[] original) {
        Item[] newCatFood = Arrays.copyOf(original, original.length + 1);
        newCatFood[newCatFood.length - 1] = Items.TROPICAL_FISH;
        return newCatFood;
    }

    @Definition(id = "tag", method = "Lnet/minecraft/data/tags/VanillaItemTagsProvider;tag(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/data/tags/TagAppender;")
    @Definition(id = "add", method = "Lnet/minecraft/data/tags/TagAppender;add([Ljava/lang/Object;)Lnet/minecraft/data/tags/TagAppender;")
    @Definition(id = "OCELOT_FOOD", field = "Lnet/minecraft/tags/ItemTags;OCELOT_FOOD:Lnet/minecraft/tags/TagKey;")
    @Expression("?.tag(OCELOT_FOOD).add(@(?))")
    @ModifyExpressionValue(method = "addTags", at = @At("MIXINEXTRAS:EXPRESSION"))
    private Item[] addTropicalFishToOcelot(Item[] original) {
        Item[] newOcelotFood = Arrays.copyOf(original, original.length + 1);
        newOcelotFood[newOcelotFood.length - 1] = Items.TROPICAL_FISH;
        return newOcelotFood;
    }
}
