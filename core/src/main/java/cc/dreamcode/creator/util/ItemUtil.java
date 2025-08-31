package cc.dreamcode.creator.util;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

@UtilityClass
public class ItemUtil {

    public static Optional<Integer> getCustomModelData(ItemMeta itemMeta) {
        try {
            Method methodHasCustomModelData = ItemMeta.class.getMethod("hasCustomModelData");
            boolean hasCustomModelData = (Boolean)methodHasCustomModelData.invoke(itemMeta);
            if (hasCustomModelData) {
                Method methodGetCustomModelData = ItemMeta.class.getMethod("getCustomModelData");
                int getCustomModelData = (Integer)methodGetCustomModelData.invoke(itemMeta);
                return Optional.of(getCustomModelData);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
        }
        return Optional.empty();
    }

    public static void setCustomModelData(ItemMeta itemMeta, int customModelData) {
        try {
            Method method = ItemMeta.class.getMethod("setCustomModelData", Integer.class);
            method.invoke(itemMeta, customModelData);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
        }
    }
}
