package com.eripe14.backups.config;

import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.platform.persistence.StorageConfig;
import cc.dreamcode.utilities.builder.MapBuilder;
import cc.dreamcode.utilities.bukkit.builder.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.util.Map;

@Configuration(child = "config.yml")
@Header("## Dream-Template (Main-Config) ##")
public class PluginConfig extends OkaeriConfig {

    @Comment
    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    @CustomKey("debug")
    public boolean debug = true;

    @Comment("Jak ma wygladac glowne menu z backupami gracza (administracyjne)")
    public String backupsTitle = "&7Backupy ({amount})";

    @Comment("Liczba wierszy gui")
    public int backupsRows = 6;

    @Comment("Co ile backupy maja sie usywac")
    public Duration backupRemoveTime = Duration.ofDays(7);

    @Comment("Jak ma wygladac bossbar")
    public String bossBarTitle = "&7Odbierz backupy!";
    public float bossBarProgress = 1.0F;
    public BarColor bossBarColor = BarColor.GREEN;
    public BarStyle bossBarOverlay = BarStyle.SOLID;

    @Comment("Okresl jak ma wygladac ustawienie przedmiotow w gui (slot, item)")
    public Map<Integer, ItemStack> backupsStructure = new MapBuilder<Integer, ItemStack>()
            .put(0, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(1, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(2, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(3, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(4, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(5, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(6, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(7, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(8, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(9, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(17, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(18, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(26, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(27, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(35, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(36, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(44, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(45, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(46, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(47, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(48, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(50, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(51, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(53, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .build();

    @Comment("Jak ma wygladac przedmiot w gui ktory reprezentuje backupa")
    public ItemStack backupItem = ItemBuilder.of(Material.WRITABLE_BOOK)
            .setName("&8» &a&lBACKUP")
            .setLore(
                    "",
                    "&8» &fZabójca: &7{killer}",
                    "&8» &fPrzyczyna śmierci: &7{reason}",
                    "&8» &fDoświadczenie: &7{experience}",
                    "&8» &fOstatni przywracający: &7{last_granter}",
                    "&8» &fPing: &7{ping}",
                    "&8» &fData stworzenia: &7{created_at}",
                    "",
                    "&8» &fKliknij &7LPM&f, aby zarządzać!",
                    ""
            )
            .toItemStack();

    @Comment("Jak maja wygladac itemy od zmiany stron")
    public int previousItemSlot = 46;
    public ItemStack previousPageItem = ItemBuilder.of(Material.SPECTRAL_ARROW)
            .setName("&8« &c&lPoprzednia strona")
            .setLore(" ", "&8» &7Kliknij, aby zmienić stronę na &c&npoprednią&f!", " ")
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    public int forwardItemSlot = 52;
    public ItemStack forwardPageItem = ItemBuilder.of(Material.SPECTRAL_ARROW)
            .setName("&8» &a&lNastępna strona")
            .setLore(" ", "&8» &7Kliknij, aby zmienić stronę na &a&nnastępną&f!", " ")
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    @Comment("Jak ma wygladac item od sortowania gui z backupami")
    public int sortBackupsItemSlot = 49;
    public ItemStack sortBackupsItem = ItemBuilder.of(Material.BREWING_STAND)
            .setName("&8» &6&lSortowanie")
            .setLore(
                    " ",
                    "&8» &7Aktualnie: &e{filter_mode}",
                    " ",
                    "&8» &7Kliknij, aby zmienić!"
            )
            .toItemStack();

    @Comment("Format czasu")
    public String timeFormat = "dd.MM.yyyy HH:mm:ss";

    @Comment("Item ktory cofa do poprzedniego gui")
    public ItemStack goBackItem = ItemBuilder.of(Material.RED_DYE)
            .setName("&cPowrót")
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    @Comment("Jak ma wygladac menu z zarzadaniem backupem")
    public String backupManageTitle = "&7Zarządzanie backupem";
    public int backupManageRows = 5;

    public boolean fillEmptySlots = true;

    @Comment("Slot na ktorym bedzie item do cofania")
    public int backupManageGoBackSlot = 36;

    @Comment("Slot na ktorym bedzie item do przyznawania backupa")
    public int backupManageGrantSlot = 44;

    @Comment("Item od przyznawania")
    public ItemStack grantBackupItem = ItemBuilder.of(Material.LIME_DYE)
            .setName("&8» &a&lPrzywróć bakup")
            .setLore(
                    "&8» &7Kliknij, aby przywrócić tego backupa!"
            )
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    @Comment("Item ktory bedzie wypelnial puste sloty")
    public ItemStack backupManageFiller = ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE)
            .setName(" ")
            .setLore(" ")
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    @Comment("Item ktory pokazuje zawartosc eq gracza")
    public int backupManageInventorySlot = 10;
    public ItemStack backupManageInventoryItem = ItemBuilder.of(Material.CHEST)
            .setName("&8» &e&lPodgląd ekwipunku")
            .setLore(" ", "&8» &7Kliknij, aby wyświetlić pogląd!", " ")
            .toItemStack();

    @Comment("Item ktory pokazuje zawartosc enderchesta gracza")
    public int backupManageEnderChestSlot = 13;
    public ItemStack backupManageEnderChestItem = ItemBuilder.of(Material.CHEST)
            .setName("&8» &d&lPodgląd enderchesta")
            .setLore(" ", "&8» &7Kliknij, aby wyświetlić pogląd!", " ")
            .toItemStack();

    @Comment("Item ktory pokazuje zbroje gracza")
    public int backupManageArmorSlot = 16;
    public ItemStack backupManageArmorItem = ItemBuilder.of(Material.CHEST)
            .setName("&8» &b&lPodgląd zbroi")
            .setLore(" ", "&8» &7Kliknij, aby wyświetlić pogląd!", " ")
            .toItemStack();

    @Comment("Item ktory pokazuje informacje o backupie")
    public int backupInfoSlot = 31;
    public ItemStack backupInfoItem = ItemBuilder.of(Material.WRITABLE_BOOK)
            .setName("&8» &f&lInformacje")
            .setLore(
                    " ",
                    "&8» &fIdentyfikator: &7{uuid}",
                    "&8» &fZabójca: &7{killer}",
                    "&8» &fPrzyczyna śmierci: &7{reason}",
                    "&8» &fDoświadczenie: &7{experience}",
                    "&8» &fOstatni przywracający: &7{last_granter}",
                    "&8» &fPing: &7{ping}",
                    "&8» &fData stworzenia: &7{created_at}",
                    " "
            )
            .toItemStack();

    @Comment("Ustawienia gui gdzie wyswietla sie zawartosc eq gracza")
    public String inventoryContentTitle = "&e&lZawartość ekwipunku";
    public int inventoryContentRows = 6;

    @Comment("Ustawienie itemow w gui, gdzie wyswietla sie zawartosc eq gracza")
    public Map<Integer, ItemStack> inventoryContentStructure = new MapBuilder<Integer, ItemStack>()
            .put(0, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(1, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(2, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(3, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(4, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(5, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(6, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(7, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(8, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(9, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName("").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(17, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName("").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(18, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(26, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(27, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(35, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(36, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(44, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(45, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(46, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(47, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(48, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(50, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(51, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(52, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(53, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .build();

    @Comment("Item do powrotu do poprzedniego gui (z menu gdzie jest zawartosc eq gracza)")
    public int inventoryGoBackSlot = 49;

    @Comment("Ustawienia gui gdzie wyswietla sie zawartosc enderchesta gracza")
    public String enderChestTitle = "&d&lZawartość enderchesta";
    public int enderChestRows = 6;

    @Comment("Ustawienie itemow w gui, gdzie wyswietla sie zawartosc enderchesta gracza")
    public Map<Integer, ItemStack> enderChestContentStructure = new MapBuilder<Integer, ItemStack>()
            .put(0, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(1, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(2, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(3, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(4, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(5, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(6, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(7, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(8, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(9, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(17, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(18, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(26, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(27, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(35, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(36, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(44, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(45, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(46, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(47, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(48, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(50, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(51, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(52, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(53, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .build();

    @Comment("Item do powrotu do poprzedniego gui (z menu gdzie jest zawartosc enderchesta gracza)")
    public int enderChestGoBackSlot = 49;

    @Comment("Ustawie gui gdzie jest wyswietlany armor gracza")
    public String armorTitle = "&b&lArmor";
    public int armorRows = 2;
    public int armorGoBackSlot = 17;

    public boolean armorFillEmptySlots = true;

    @Comment("Item ktory bedzie wypelnial puste sloty")
    public ItemStack armorFiller = ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE)
            .setName(" ")
            .setLore(" ")
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    @Comment("Okresl jak ma wygladac ustawienie przedmiotow w gui gdzie odbiera sie backupy (slot, item)")
    public String receiveBackupsTitle = "&7Odbierz backupy";
    public int receiveBackupsRows = 6;

    public ItemStack receiveBackupItem = ItemBuilder.of(Material.LIME_DYE)
            .setName("&8» &a&lODBIERZ BACKUP")
            .setLore(
                    " ",
                    "&8» &a&lInformacje:",
                    " ",
                    "&8» &fPrzed obiorem &a&nmusisz &fmieć pusty ekwpiunek!",
                    " ",
                    "&8» &7Kliknij, aby odebrać backup!"
            )
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .toItemStack();

    public Map<Integer, ItemStack> receiveBackupsStructure = new MapBuilder<Integer, ItemStack>()
            .put(0, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(1, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(2, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(3, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(4, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(5, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(6, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(7, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(8, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(9, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(17, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(18, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(26, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(27, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(35, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(36, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(44, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())

            .put(45, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(46, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(47, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(48, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(49, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(50, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(51, ItemBuilder.of(Material.WHITE_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(52, ItemBuilder.of(Material.PINK_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .put(53, ItemBuilder.of(Material.MAGENTA_STAINED_GLASS_PANE).setName(" ").addFlags(ItemFlag.HIDE_ATTRIBUTES).toItemStack())
            .build();

    @Comment
    @Comment("Ponizej znajduja sie dane do logowania bazy danych:")
    @CustomKey("storage-config")
    public StorageConfig storageConfig = new StorageConfig("backups");
}