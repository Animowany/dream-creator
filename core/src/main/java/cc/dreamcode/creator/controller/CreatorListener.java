package cc.dreamcode.creator.controller;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.tasker.core.Tasker;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CreatorListener implements Listener {

    private final Map<UUID, Consumer<String>> consumerMap = new WeakHashMap<>();
    private final Tasker tasker;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();
        if (consumerMap.containsKey(playerId)) {
            event.setCancelled(true);
            tasker.newChain().supplyAsync(() -> consumerMap.remove(playerId)).acceptSync(consumer -> consumer.accept(event.getMessage())).execute();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        consumerMap.remove(event.getPlayer().getUniqueId());
    }

    public void createHandler(Player player, Consumer<String> consumer) {
        consumerMap.put(player.getUniqueId(), consumer);
    }

}
