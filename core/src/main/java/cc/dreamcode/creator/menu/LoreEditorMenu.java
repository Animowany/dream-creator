package cc.dreamcode.creator.menu;

import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.creator.config.CreatorMessageConfig;
import cc.dreamcode.creator.controller.CreatorListener;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.base.BukkitMenuPaginated;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuPaginatedPlayerSetup;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class LoreEditorMenu implements BukkitMenuPaginatedPlayerSetup {

    private final CreatorListener creatorListener;
    private final CreatorConfig creatorConfig;
    private final CreatorMessageConfig messageConfig;

    public String baseValue;
    public List<String> baseLore;
    public Consumer<List<String>> returnConsumer;
    @Override
    public BukkitMenuPaginated build(@NotNull HumanEntity humanEntity) {
        BukkitMenu loreEditor = creatorConfig.loreEditor.buildWithItems();
        loreEditor.setItem(creatorConfig.loreEditorSaveSlot, ItemBuilder.of(creatorConfig.saveChangesItem).fixColors().toItemStack(),event -> returnConsumer.accept(baseLore));

        loreEditor.setInventoryClickEvent(click -> {
           if (click.getSlot() == creatorConfig.loreAddSlot) {
               baseLore.add(baseValue);
               build(humanEntity).openPage(humanEntity);
           }
        });
        BukkitMenuPaginated listEditorPaginated = loreEditor.toPaginated();
        listEditorPaginated.setPreviousPageSlot(creatorConfig.loreEditorPreviousPage, e -> {});
        listEditorPaginated.setNextPageSlot(creatorConfig.loreEditorNextPage, e -> {});
        for (int i = 0; i < baseLore.size(); i++) {
            Object obj = baseLore.get(i);
            int finalI = i;
            listEditorPaginated.addStorageItem(ItemBuilder.of(creatorConfig.loreLine).fixColors(MapBuilder.of(
                    "index", i,
                    "value", String.valueOf(obj)
            )).toItemStack(), click -> {
                if (click.getClick() == ClickType.LEFT) {
                    click.getWhoClicked().closeInventory();
                    messageConfig.typeInChat.send(click.getWhoClicked());
                    creatorListener.createHandler((Player) click.getWhoClicked(), message -> {
                        try {
                            baseLore.set(finalI, message);
                        } catch (Exception ex) {
                            messageConfig.invalidValue.send(click.getWhoClicked());
                        }
                        listEditorPaginated.dispose();
                        build(humanEntity).openPage(humanEntity);
                    });
                } else if (click.getClick() == ClickType.RIGHT) {
                    baseLore.remove(obj);
                    build(humanEntity).openPage(humanEntity);
                }
            });
        }
        return listEditorPaginated;
    }

    public LoreEditorMenu setReturnConsumer(Consumer<List<String>> returnConsumer) {
        this.returnConsumer = returnConsumer;
        return this;
    }

    public LoreEditorMenu setBaseValue(String baseValue) {
        this.baseValue = baseValue;
        return this;
    }

    public LoreEditorMenu setBaseList(List<String> baseList) {
        this.baseLore = baseList;
        return this;
    }

    public void reload(Player player) {
        build(player).openPage(player);
    }

}
