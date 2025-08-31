package cc.dreamcode.creator;

import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.creator.config.CreatorMessageConfig;
import cc.dreamcode.creator.controller.CreatorListener;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.ConfigurationResolver;
import cc.dreamcode.platform.bukkit.component.ListenerResolver;
import cc.dreamcode.platform.component.ComponentService;
import lombok.Getter;

public class BukkitCreator {

    @Getter
    private static BukkitCreator INSTANCE;

    @Getter
    private DreamBukkitPlatform dreamBukkitPlatform;

    public void setup(DreamBukkitPlatform dreamBukkitPlatform, ComponentService componentService) {
        INSTANCE = this;
        this.dreamBukkitPlatform = dreamBukkitPlatform;
        componentService.registerResolver(ConfigurationResolver.class);
        componentService.registerComponent(CreatorConfig.class);
        componentService.registerComponent(CreatorMessageConfig.class);

        componentService.registerResolver(ListenerResolver.class);
        componentService.registerComponent(CreatorListener.class);
    }
}
