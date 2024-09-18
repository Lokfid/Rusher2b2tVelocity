package org.lokfid;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

/**
 * Velo rusherhack plugin
 *
 * @author Lokfid
 */
public class VeloPlugin extends Plugin {
	
	@Override
	public void onLoad() {
		this.getLogger().info("Velocity+ loaded!");

		final VeloModule veloModule = new VeloModule();
		RusherHackAPI.getModuleManager().registerFeature(veloModule);
	}
	
	@Override
	public void onUnload() {
		this.getLogger().info("Velocity+ unloaded!");
	}
	
}