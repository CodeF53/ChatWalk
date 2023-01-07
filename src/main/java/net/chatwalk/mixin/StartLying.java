package net.chatwalk.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.chatwalk.LiterallyJustOneBoolean.LieAboutMovingForward;

@Mixin(value = Minecraft.class, priority = 1500)
public abstract class StartLying {
	@Inject(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;openChatScreen(Ljava/lang/String;)V"))
	void moveChatBoi(CallbackInfo ci) {
		// if the player is holding W
		if (Minecraft.getInstance().options.keyUp.isDown()) {
			// lie and tell the server that we are still moving forward despite having chat open
			LieAboutMovingForward = true;
		}
	}
}
