package org.lokfid;

import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.network.protocol.common.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import org.rusherhack.client.api.accessors.packet.IMixinClientboundExplodePacket;
import org.rusherhack.client.api.events.network.EventPacket;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.core.event.subscribe.Subscribe;

/**
 * Velo rusherhack module
 *
 * @author Lokfid
 */
public class VeloModule extends ToggleableModule {
	int grimTicks;
	boolean flag;

	public VeloModule() {
		super("Velocity+", "Velocity for 2b2t", ModuleCategory.CLIENT);
	}


	@Subscribe
	public void onPacketReceive(EventPacket.Receive e) {
	if(mc.player != null) {
			if (mc.player.isFallFlying()) {
				return;
			}
		}

			if (e.getPacket() instanceof ClientboundBundlePacket bundle) {
				bundle.subPackets().forEach(bundlepacket -> {
					if (bundlepacket instanceof ClientboundSetEntityMotionPacket pac) {
						if (pac.getId() == mc.player.getId()) {
							e.setCancelled(true);
							grimTicks = 6;
						}
					}
				});

		}
		if (e.getPacket() instanceof ClientboundPingPacket && grimTicks > 0) {
			e.setCancelled(true);
			grimTicks--;
		}
		//I have no idea if it will work 100% of times
		if (e.getPacket() instanceof ClientboundExplodePacket explosion) {
			((IMixinClientboundExplodePacket) explosion).setKnockbackX(0);
			((IMixinClientboundExplodePacket) explosion).setKnockbackY(0);
			((IMixinClientboundExplodePacket) explosion).setKnockbackZ(0);
			flag = true;
		}
	}

	@Override
	public void onEnable() {
		grimTicks = 0;
	}
}