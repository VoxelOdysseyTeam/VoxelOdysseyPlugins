package com.guy7cc.voxelodyssey.dev.banner;

import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.registry.Registry;
import com.guy7cc.voxelodyssey.dev.VODevPlugin;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class Banners {
    public static final Registry<BannerFactory> REGISTRY = new Registry<>(VODevPlugin::getPlugin);
    public static final BannerFactory WORLD2_UNDERGROUND = register(new BannerFactory(
            Key.vo("world2_underground"), () -> {
                ItemStack is = new ItemStack(Material.RED_BANNER);
                BannerMeta meta = (BannerMeta) is.getItemMeta();
                meta.addPattern(new Pattern(DyeColor.BLACK, PatternType.RHOMBUS));
                meta.addPattern(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_TOP));
                meta.addPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_BOTTOM));
                meta.addPattern(new Pattern(DyeColor.BLACK, PatternType.GRADIENT));
                meta.addPattern(new Pattern(DyeColor.GRAY, PatternType.FLOWER));
                meta.addPattern(new Pattern(DyeColor.BLACK, PatternType.CURLY_BORDER));
                is.setItemMeta(meta);
                return is;
            }
    ));

    private Banners(){

    }

    private static BannerFactory register(BannerFactory factory){
        REGISTRY.register(factory);
        return factory;
    }
}
