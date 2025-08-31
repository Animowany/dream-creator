package cc.dreamcode.creator.menu;

import cc.dreamcode.creator.config.CreatorConfig;
import cc.dreamcode.menu.bukkit.base.BukkitMenu;
import cc.dreamcode.menu.bukkit.base.BukkitMenuPaginated;
import cc.dreamcode.menu.bukkit.setup.BukkitMenuPaginatedSetup;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XItemFlag;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EnchantEditorMenu implements BukkitMenuPaginatedSetup {

    public ItemStack itemStack;
    public Consumer<ItemStack> returnConsumer;
    private final CreatorConfig creatorConfig;

    @Override
    public BukkitMenuPaginated build() {
        BukkitMenu materialChooser = creatorConfig.enchantEditor.buildWithItems();
        materialChooser.setItem(creatorConfig.enchantEditorSaveSlot, ItemBuilder.of(creatorConfig.saveChangesItem).fixColors().toItemStack(),event -> returnConsumer.accept(itemStack));
        BukkitMenuPaginated bukkitMenuPaginated = materialChooser.toPaginated();
        bukkitMenuPaginated.setPreviousPageSlot(creatorConfig.enchantEditorPreviousPage, e -> {});
        bukkitMenuPaginated.setNextPageSlot(creatorConfig.enchantEditorNextPage, e -> {});
        for (XEnchantment value : XEnchantment.values()) {
            if (value != null && value.isSupported()) {
                Enchantment enchantment = value.get();
                if (enchantment.canEnchantItem(itemStack)) {
                    bukkitMenuPaginated.addStorageItem(ItemBuilder.of(XMaterial.ENCHANTED_BOOK.parseMaterial()).setName("&f&l" + value.name()).setLore("&7Level: &f" + itemStack.getEnchantmentLevel(enchantment)).fixColors().toItemStack(), event -> {
                        switch (event.getClick()) {
                            case SHIFT_LEFT:
                                increaseEnchantLevel(itemStack, enchantment, 5);
                                break;
                            case SHIFT_RIGHT:
                                decreaseEnchantLevel(itemStack, enchantment, 5);
                                break;
                            case LEFT:
                                increaseEnchantLevel(itemStack, enchantment, 1);
                                break;
                            case RIGHT:
                                decreaseEnchantLevel(itemStack, enchantment, 1);
                                break;
                            case DROP:
                                itemStack.removeEnchantment(enchantment);
                                break;
                        }
                        bukkitMenuPaginated.dispose();
                        build().openPage(event.getWhoClicked());
                    });
                }
            }
        }
        return bukkitMenuPaginated;
    }

    public EnchantEditorMenu setReturnConsumer(Consumer<ItemStack> returnConsumer) {
        this.returnConsumer = returnConsumer;
        return this;
    }

    public EnchantEditorMenu setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public void increaseEnchantLevel(ItemStack itemStack, Enchantment enchantment, int amount) {
        if (itemStack.containsEnchantment(enchantment)) {
            itemStack.addUnsafeEnchantment(enchantment, itemStack.getEnchantmentLevel(enchantment) + amount);
        } else {
            itemStack.addUnsafeEnchantment(enchantment, amount);
        }
    }

    public void decreaseEnchantLevel(ItemStack itemStack, Enchantment enchantment, int amount) {
        if (itemStack.containsEnchantment(enchantment)) {
            int enchantLevel = itemStack.getEnchantmentLevel(enchantment);
            if (enchantLevel - amount <= 0) {
                itemStack.removeEnchantment(enchantment);
            } else {
                itemStack.addUnsafeEnchantment(enchantment, itemStack.getEnchantmentLevel(enchantment) - amount);
            }
        }
    }
}
