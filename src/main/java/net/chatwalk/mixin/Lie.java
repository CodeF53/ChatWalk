package net.chatwalk.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.chatwalk.LiterallyJustOneBoolean.LieAboutMovingForward;

@Mixin(value = KeyboardInput.class, priority = 999)
public class Lie {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;isDown()Z", ordinal = 0))
    private boolean lie(KeyMapping instance) {
        if (LieAboutMovingForward) {
            if (Minecraft.getInstance().screen instanceof ChatScreen) {
                // lie about moving forward
                return true;
            } else {
                // chat isn't open, turn off lying
                LieAboutMovingForward = false;
            }
        }
        return instance.isDown();
    }
}
