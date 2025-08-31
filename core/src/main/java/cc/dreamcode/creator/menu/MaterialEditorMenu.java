package cc.dreamcode.creator.menu;

import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.base.BukkitMenuPaginated;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuPaginatedSetup;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Objects;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MaterialEditorMenu implements BukkitMenuPaginatedSetup {

    public Consumer<Material> returnConsumer;
    private final CreatorConfig creatorConfig;

    @Override
    public BukkitMenuPaginated build() {
        BukkitMenu materialChooser = creatorConfig.materialEditor.buildWithItems();
        BukkitMenuPaginated bukkitMenuPaginated = materialChooser.toPaginated();
        bukkitMenuPaginated.setPreviousPageSlot(creatorConfig.materialEditorPreviousPage, e -> {});
        bukkitMenuPaginated.setNextPageSlot(creatorConfig.materialEditorNextPage, e -> {});
        for (XMaterial value : XMaterial.values()) {
            if (value != null && value.isSupported()) {
                try {
                    bukkitMenuPaginated.addStorageItem(ItemBuilder.of(value.parseMaterial()).setName("&f&l" + value.name()).fixColors().toItemStack(), event -> {
                        Objects.requireNonNull(returnConsumer, "No consumer on creator").accept(event.getCurrentItem().getType());
                    });
                } catch (Exception ignored) {}
            }
        }
        return bukkitMenuPaginated;
    }

    public MaterialEditorMenu setReturnConsumer(Consumer<Material> returnConsumer) {
        this.returnConsumer = returnConsumer;
        return this;
    }
}
