package cc.dreamcode.creator.config;

import cc.dreamcode.menu.bukkit.BukkitMenuBuilder;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import com.cryptomorin.xseries.XMaterial;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Configuration(child = "creator.yml")
public class CreatorConfig extends OkaeriConfig {

    @Comment
    @Comment("Menu creatora")
    @CustomKey("itemCreatorMenu")
    public BukkitMenuBuilder itemCreatorMenu = new BukkitMenuBuilder(
            "&a&lItem creator",
            3,
            new MapBuilder<Integer, ItemStack>()
                    .put(10, ItemBuilder.of(XMaterial.OAK_SIGN.parseMaterial())
                            .setName("Zmiana nazwy")
                            .setLore("Nazwa {name}", "", "Kliknij aby zmienic")
                            .toItemStack())
                    .put(11, ItemBuilder.of(XMaterial.BOOK.parseMaterial())
                            .setName("Zmiana opisu")
                            .setLore("", "Kliknij aby otworzyc kreator opisu")
                            .toItemStack())
                    .put(12, ItemBuilder.of(XMaterial.VINE.parseMaterial())
                            .setName("Zmiana itemka")
                            .setLore("Nazwa itemka: {material}", "", "Kliknij aby otworzyc kreator itemkow")
                            .toItemStack())
                    .put(13, ItemBuilder.of(XMaterial.PAPER.parseMaterial())
                            .setName("Zmiana model daty")
                            .setLore("Custom model data: {cmd}", "", "Kliknij aby zmienic custom model date")
                            .toItemStack())
                    .put(14, ItemBuilder.of(XMaterial.ENCHANTED_BOOK.parseMaterial())
                            .setName("Zmiana enchantów")
                            .setLore("Kliknij aby zmienic enchanty")
                            .toItemStack())
                    .put(15, ItemBuilder.of(XMaterial.ITEM_FRAME.parseMaterial())
                            .setName("Item Flagi")
                            .setLore("Kliknij aby zmienic item flagi")
                            .toItemStack())
                    .build()
    );

    public int itemCreatorMenuSaveSlot = 18;

    public ItemStack saveChangesItem = ItemBuilder.of(XMaterial.GREEN_WOOL.parseMaterial())
            .setName("Zapisz")
            .setLore("", "Kliknij aby zapisać zmiany")
            .toItemStack();

    @Comment
    @Comment("Menu w ktorym ustawia sie enchanty")
    @CustomKey("enchantEditor")
    public BukkitMenuBuilder enchantEditor = new BukkitMenuBuilder(
            "&6&lWybor enchantow",
            6,
            new MapBuilder<Integer, ItemStack>()
                    .put(45, ItemBuilder.of(XMaterial.RED_WOOL.parseMaterial())
                            .setName("&cPowrot do porzedniej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(53, ItemBuilder.of(XMaterial.GREEN_WOOL.parseMaterial())
                            .setName("&aPrzejdz do nastepnej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .build()
    );

    @Comment
    @Comment("Numer slota w ktorym jest nastepna strona edytora enchant")
    @CustomKey("enchantEditorNextPage")
    public int enchantEditorNextPage = 53;

    @Comment
    @Comment("Numer slota w ktorym jest poprzednia strona edytora enchant")
    @CustomKey("enchantEditorPreviousPage")
    public int enchantEditorPreviousPage = 45;

    public int enchantEditorSaveSlot = 49;

    @Comment
    @Comment("Menu w ktorym ustawia sie itemflagi")
    @CustomKey("itemFlagEditor")
    public BukkitMenuBuilder itemFlagEditor = new BukkitMenuBuilder(
            "&6&lWybor item flag",
            6,
            new MapBuilder<Integer, ItemStack>()
                    .put(45, ItemBuilder.of(XMaterial.RED_WOOL.parseMaterial())
                            .setName("&cPowrot do porzedniej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(53, ItemBuilder.of(XMaterial.GREEN_WOOL.parseMaterial())
                            .setName("&aPrzejdz do nastepnej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .build()
    );

    public int itemFlagEditorSaveSlot = 49;

    @Comment
    @Comment("Numer slota w ktorym jest nastepna strona edytora flag")
    @CustomKey("itemFlagEditorNextPage")
    public int itemFlagEditorNextPage = 53;

    @Comment
    @Comment("Numer slota w ktorym jest poprzednia strona edytora flag")
    @CustomKey("itemFlagEditorPreviousPage")
    public int itemFlagEditorPreviousPage = 45;

    @Comment
    @Comment("Menu w ktorym ustawia sie materiał")
    @CustomKey("materialEditor")
    public BukkitMenuBuilder materialEditor = new BukkitMenuBuilder(
            "&6&lWybor itemka",
            6,
            new MapBuilder<Integer, ItemStack>()
                    .put(45, ItemBuilder.of(XMaterial.RED_WOOL.parseMaterial())
                            .setName("&cPowrot do porzedniej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(48, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(49, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(50, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(53, ItemBuilder.of(XMaterial.GREEN_WOOL.parseMaterial())
                            .setName("&aPrzejdz do nastepnej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .build()
    );

    @Comment
    @Comment("Numer slota w ktorym jest nastepna strona edytora materialu")
    @CustomKey("materialEditorNextPage")
    public int materialEditorNextPage = 53;

    @Comment
    @Comment("Numer slota w ktorym jest poprzednia strona edytora materialu")
    @CustomKey("materialEditorPreviousPage")
    public int materialEditorPreviousPage = 45;

    @Comment
    @Comment("Menu lore editora")
    @CustomKey("loreEditor")
    public BukkitMenuBuilder loreEditor = new BukkitMenuBuilder(
            "&e&lLore editor",
            6,
            new MapBuilder<Integer, ItemStack>()
                    .put(45, ItemBuilder.of(XMaterial.RED_WOOL.parseMaterial())
                            .setName("&cPowrot do porzedniej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .put(46, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(47, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(48, ItemBuilder.of(XMaterial.GREEN_DYE.parseMaterial())
                            .setName("Dodaj linię")
                            .setLore("", "Kliknij aby dodac pustą linie")
                            .toItemStack())
                    .put(49, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(51, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(52, ItemBuilder.of(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial())
                            .setName(" ")
                            .toItemStack())
                    .put(53, ItemBuilder.of(XMaterial.GREEN_WOOL.parseMaterial())
                            .setName("&aPrzejdz do nastepnej strony.")
                            .setLore("&7Kliknij, aby zmienic strone.")
                            .toItemStack())
                    .build()
    );

    public int loreEditorSaveSlot = 50;


    public ItemStack loreLine = ItemBuilder.of(Material.PAPER).setName("&f&lLinijka {index}").setLore("&8» &fWartość:", "{value}", "&aKliknij lewym aby edytowac linie", "&cKliknij prawym aby usunac linie").toItemStack();

    @Comment
    @Comment("Numer slota w ktorym jest nastepna strona edytora lore")
    @CustomKey("loreEditorNextPage")
    public int loreEditorNextPage = 53;

    @Comment
    @Comment("Numer slota w ktorym jest poprzednia strona edytora lore")
    @CustomKey("loreEditorPreviousPage")
    public int loreEditorPreviousPage = 45;

    @Comment
    @Comment("Numer slota w ktorym jest dodawanie linijek lore")
    @CustomKey("loreAddSlot")
    public int loreAddSlot = 48;

}
