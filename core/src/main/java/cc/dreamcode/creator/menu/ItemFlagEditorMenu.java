package cc.dreamcode.creator.menu;

import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.base.BukkitMenuPaginated;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuPaginatedSetup;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ItemFlagEditorMenu implements BukkitMenuPaginatedSetup {

    public ItemMeta itemMeta;
    public Consumer<ItemMeta> returnConsumer;
    private final CreatorConfig creatorConfig;

    @Override
    public BukkitMenuPaginated build() {
        BukkitMenu itemFlagEditor = creatorConfig.itemFlagEditor.buildWithItems();
        itemFlagEditor.setItem(creatorConfig.itemFlagEditorSaveSlot, ItemBuilder.of(creatorConfig.saveChangesItem).fixColors().toItemStack(),event -> returnConsumer.accept(itemMeta));
        BukkitMenuPaginated bukkitMenuPaginated = itemFlagEditor.toPaginated();
        bukkitMenuPaginated.setPreviousPageSlot(creatorConfig.itemFlagEditorPreviousPage, e -> {});
        bukkitMenuPaginated.setNextPageSlot(creatorConfig.itemFlagEditorNextPage, e -> {});
        for (XItemFlag value : XItemFlag.values()) {
            if (value != null && value.isSupported()) {
                try {
                    boolean hasFlag = itemMeta.hasItemFlag(value.get());
                    bukkitMenuPaginated.addStorageItem(ItemBuilder.of(XMaterial.ITEM_FRAME.parseMaterial()).setName("&f&l" + value.name()).setLore("&7Wartość: &f" + hasFlag).fixColors().toItemStack(), event -> {
                        if (hasFlag) {
                            itemMeta.removeItemFlags(value.get());
                        } else {
                            itemMeta.addItemFlags(value.get());
                        }
                        build().openPage(event.getWhoClicked());
                    });
                } catch (Exception ignored) {}
            }
        }
        return bukkitMenuPaginated;
    }

    public ItemFlagEditorMenu setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public ItemFlagEditorMenu setReturnConsumer(Consumer<ItemMeta> returnConsumer) {
        this.returnConsumer = returnConsumer;
        return this;
    }
}
