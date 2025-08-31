package cc.dreamcode.creator.composed;

import cc.dreamcode.creator.BukkitCreator;
import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.creator.config.CreatorMessageConfig;
import cc.dreamcode.creator.controller.CreatorListener;
import cc.dreamcode.creator.menu.EnchantEditorMenu;
import cc.dreamcode.creator.menu.ItemFlagEditorMenu;
import cc.dreamcode.creator.menu.LoreEditorMenu;
import cc.dreamcode.creator.menu.MaterialEditorMenu;
import cc.dreamcode.creator.util.ItemUtil;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuSetup;
import cc.dreamcode.utilities.builder.ListBuilder;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ItemCreatorMenu implements BukkitMenuSetup {

    public ItemStack itemStack;
    public Consumer<ItemStack> returnConsumer;
    private final CreatorConfig creatorConfig;
    private final CreatorListener creatorListener;
    private final CreatorMessageConfig messageConfig;

    @Override
    public BukkitMenu build() {
        BukkitMenu bukkitMenu = creatorConfig.itemCreatorMenu.buildWithItems(MapBuilder.of(
                "cmd", ItemUtil.getCustomModelData(itemStack.getItemMeta()).orElse(0),
                "name", itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : "",
                "material", itemStack.getType().name()
        ));
        bukkitMenu.setItem(0, ItemBuilder.of(itemStack).toItemStack());
        bukkitMenu.setItem(creatorConfig.itemCreatorMenuSaveSlot, ItemBuilder.of(creatorConfig.saveChangesItem).fixColors().toItemStack(),event -> returnConsumer.accept(itemStack));
        bukkitMenu.setItem(11, ItemBuilder.of(creatorConfig.itemCreatorMenu.getItems().get(11)).startLoreWith(itemStack.getItemMeta().hasLore() ? itemStack.getItemMeta().getLore() : new ArrayList<>()).fixColors().toItemStack());
        bukkitMenu.setInventoryClickEvent(event -> {
            Player player = (Player)event.getWhoClicked();
           switch (event.getSlot()) {
               case 10:
                   messageConfig.typeInChat.send(player);
                   creatorListener.createHandler(player, msg -> {
                       ItemBuilder.manipulate(itemStack).setName(msg);
                       build().open(player);
                   });
                   player.closeInventory();
                   break;
               case 11:
                   LoreEditorMenu loreEditorMenu = BukkitCreator.getINSTANCE().getDreamBukkitPlatform().registerInjectable(LoreEditorMenu.class);
                   loreEditorMenu
                           .setBaseList(itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() ? itemStack.getItemMeta().getLore() : ListBuilder.of())
                           .setBaseValue("")
                           .setReturnConsumer(lore -> {
                               if (!lore.isEmpty()) {
                                   ItemBuilder.manipulate(itemStack).setLore(lore);
                               }
                               build().open(player);
                           });
                   loreEditorMenu.build(player).openFirstPage(player);
                   break;
               case 12:
                   MaterialEditorMenu materialEditorMenu = BukkitCreator.getINSTANCE().getDreamBukkitPlatform().registerInjectable(MaterialEditorMenu.class);
                   materialEditorMenu
                           .setReturnConsumer(material -> {
                               ItemBuilder.manipulate(itemStack).setType(material);
                               build().open(player);
                           });
                   materialEditorMenu.build().openFirstPage(player);
                   break;
               case 13:
                   player.closeInventory();
                   messageConfig.typeInChat.send(player);
                   creatorListener.createHandler(player, msg -> {
                       try {
                           int cmd = Integer.parseInt(msg);
                           ItemBuilder.manipulate(itemStack).withCustomMeta(itemMeta -> {
                               ItemUtil.setCustomModelData(itemMeta, cmd);
                               return itemMeta;
                           });
                           build().open(player);
                       } catch (Exception ex) {
                           messageConfig.invalidValue.send(player);
                       }
                       build().open(player);
                   });
                   break;
               case 14:
                   EnchantEditorMenu enchantEditorMenu = BukkitCreator.getINSTANCE().getDreamBukkitPlatform().registerInjectable(EnchantEditorMenu.class);
                   enchantEditorMenu
                           .setItemStack(itemStack)
                           .setReturnConsumer(itemStack1 -> {
                               ItemBuilder.manipulate(itemStack).setType(itemStack1);
                               build().open(player);
                           });
                   enchantEditorMenu.build().openFirstPage(player);
                   break;
               case 15:
                   ItemFlagEditorMenu itemFlagEditorMenu = BukkitCreator.getINSTANCE().getDreamBukkitPlatform().registerInjectable(ItemFlagEditorMenu.class);
                   itemFlagEditorMenu
                           .setItemMeta(itemStack.getItemMeta())
                           .setReturnConsumer(itemMeta -> {
                              ItemBuilder.manipulate(itemStack).withCustomMeta(meta -> itemMeta);
                               build().open(player);
                           });
                   itemFlagEditorMenu.build().openFirstPage(player);
                   break;
           }
        });
        return bukkitMenu;
    }

    public ItemCreatorMenu setReturnConsumer(Consumer<ItemStack> returnConsumer) {
        this.returnConsumer = returnConsumer;
        return this;
    }

    public ItemCreatorMenu setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }
}
