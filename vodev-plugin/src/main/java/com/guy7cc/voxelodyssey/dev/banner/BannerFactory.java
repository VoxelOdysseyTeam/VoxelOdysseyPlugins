package com.guy7cc.voxelodyssey.dev.banner;

import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class BannerFactory extends AbstractRegistryObject {
    private final Supplier<ItemStack> supplier;

    public BannerFactory(Key key, Supplier<ItemStack> supplier) {
        super(key);
        this.supplier = supplier;
    }

    public ItemStack supply(){
        return supplier.get();
    }
}
