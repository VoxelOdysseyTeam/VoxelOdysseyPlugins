package com.guy7cc.voxelodyssey.core.command;

import com.guy7cc.voxelodyssey.core.item.VOItem;
import com.guy7cc.voxelodyssey.core.registry.Registry;
import com.guy7cc.voxelodyssey.core.registry.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public interface CommandArg<T> {
    String getName();

    boolean matches(String arg);

    T get(String arg);

    @Nullable
    List<String> onTabComplete(String arg);

    static CommandArg<String> literal(String name){
        return new LiteralArg(name);
    }

    static CommandArg<String> literal(String name, String option){
        return new LiteralArg(name, option);
    }

    static CommandArg<String> literal(String name, Set<String> options) {
        return new LiteralArg(name, options);
    }

    static CommandArg<String> literal(String name, Supplier<Set<String>> options){
        return new LiteralArg(name, options);
    }

    static CommandArg<Boolean> bool(String name){
        return new BooleanArg(name);
    }

    static CommandArg<Integer> rangedInt(String name, int min, int max) {
        return new RangedIntegerArg(name, min, max);
    }

    static CommandArg<Long> rangedLong(String name, long min, long max){
        return new RangedLongArg(name, min, max);
    }

    static CommandArg<Float> rangedFloat(String name, float min, float max){
        return new RangedFloatArg(name, min, max);
    }

    static CommandArg<Double> rangedDouble(String name, double min, double max) {
        return new RangedDoubleArg(name, min, max);
    }

    static CommandArg<VOItem> voitem(String name){
        return new VOItemArg(name);
    }

    static <T extends RegistryObject> CommandArg<T> registry(String name, Registry<T> registry, String namespace){
        return new RegistryArg<>(name, registry, namespace);
    }
}
