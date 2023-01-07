package net.chatwalk.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import static net.chatwalk.LiterallyJustOneBoolean.LieAboutMovingForward;

@Mixin(value = KeyboardInput.class, priority = 1500)
public class Lie {
    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z", ordinal = 0))
    private boolean lie(boolean original) {
        if (LieAboutMovingForward) {
            if (Minecraft.getInstance().screen instanceof ChatScreen) {
                // lie about moving forward
                return true;
            } else {
                // chat isn't open, turn off lying
                LieAboutMovingForward = false;
            }
        }
        return original;
    }
}
