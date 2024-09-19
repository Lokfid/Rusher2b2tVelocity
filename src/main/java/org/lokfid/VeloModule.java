package org.lokfid;

import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.network.protocol.common.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import org.rusherhack.client.api.accessors.packet.IMixinClientboundExplodePacket;
import org.rusherhack.client.api.events.client.EventUpdate;
import org.rusherhack.client.api.events.network.EventPacket;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.core.event.stage.Stage;
import org.rusherhack.core.event.subscribe.Subscribe;

/**
 * Velo rusherhack module
 *
 * @author Lokfid
 */
public class VeloModule extends ToggleableModule {

	public VeloModule() {
		super("Velocity+", "Velocity for 2b2t", ModuleCategory.CLIENT);
	}


	@Subscribe
	public void onPacketReceive(EventPacket.Receive e) {
		if (mc.player != null && mc.player.isFallFlying()) return;
		if (e.getPacket() instanceof ClientboundBundlePacket bundle) {
			bundle.subPackets().forEach(bundlepacket -> {
				if (bundlepacket instanceof ClientboundSetEntityMotionPacket pac) {
					if (pac.getId() == mc.player.getId()) {
						e.setCancelled(true);
					}
				}
				//I have no idea if it will work 100% of times
				else if (bundlepacket instanceof ClientboundExplodePacket explosion) {
					((IMixinClientboundExplodePacket) explosion).setKnockbackX(0);
					((IMixinClientboundExplodePacket) explosion).setKnockbackY(0);
					((IMixinClientboundExplodePacket) explosion).setKnockbackZ(0);
				}
			});

		}
		//if that causes some bad shit its not my fault
		//it works decently even without it
	/*	if (e.getPacket() instanceof ClientboundPingPacket && grimTicks > 0) {
			e.setCancelled(true);
			grimTicks--;
		}
	 */
	}


		@Override
		public void onEnable () {
		}
	}

