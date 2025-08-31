package cc.dreamcode.creator.config;

import cc.dreamcode.notice.bukkit.BukkitNotice;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.Headers;

@Configuration(child = "creator-messages.yml")
@Headers({
        @Header("Dostepne type: (DO_NOT_SEND, CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
})
public class CreatorMessageConfig extends OkaeriConfig {

    public BukkitNotice typeInChat = BukkitNotice.chat("&8» &fPodaj ustawianą wartość. &7(string, number (1 - integer, 1.0 - double), true/false - boolean. Inne typy nie są wspierane");

    public BukkitNotice invalidValue = BukkitNotice.chat("&8» &cPodaj poprawną wartość.");
}
