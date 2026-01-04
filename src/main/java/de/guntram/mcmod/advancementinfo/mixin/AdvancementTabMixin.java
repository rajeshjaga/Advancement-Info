package de.guntram.mcmod.advancementinfo.mixin;

import static de.guntram.mcmod.advancementinfo.AdvancementInfo.config;
import de.guntram.mcmod.advancementinfo.AdvancementInfo;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.advancement.AdvancementTab;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementTab.class)
public class AdvancementTabMixin {

    @Shadow @Final
    private AdvancementsScreen screen;

    private int currentInfoWidth;

    @Inject(method="render", at = @At("HEAD"))
    private void updateLayout(DrawContext context, int x, int y, CallbackInfo ci) {
        if(screen != null) {
            currentInfoWidth = config.infoWidth.calculate(screen.width);
        }
    }

    // Modify ALL 234 constants in the entire class (not just specific methods)
    @ModifyConstant(method="*", constant=@Constant(intValue = 234), require = 0)
    private int modifyAll234(int orig) {
        return screen.width - config.marginX*2 - 2*9 - currentInfoWidth;
    }

    // Modify ALL 113 constants in the entire class
    @ModifyConstant(method="*", constant=@Constant(intValue = 113), require = 0)
    private int modifyAll113(int orig) {
        return screen.height - config.marginY*2 - 3*9;
    }

    // Origin positioning
    @ModifyConstant(method="render", constant=@Constant(intValue = 117), require = 1)
    private int getAdvTreeXOrig(int orig) {
        return screen.width/2 - config.marginX - currentInfoWidth/2;
    }

    @ModifyConstant(method="render", constant=@Constant(intValue = 56), require = 1)
    private int getAdvTreeYOrig(int orig) {
        return screen.height/2 - config.marginY;
    }

    // Texture repeats
    @ModifyConstant(method="render", constant=@Constant(intValue = 15), require = 1)
    private int getXTextureRepeats(int orig) {
        return (screen.width-config.marginX*2 - currentInfoWidth) / 16 + 1;
    }

    @ModifyConstant(method="render", constant=@Constant(intValue = 8), require = 1)
    private int getYTextureRepeats(int orig) {
        return (screen.height-config.marginY*2) / 16 + 1;
    }

    @Inject(method="drawWidgetTooltip", at=@At("HEAD"))
    private void forgetMouseOver(DrawContext context, int i, int j, int y, int k, CallbackInfo ci) {
        AdvancementInfo.mouseOver = null;
    }
}