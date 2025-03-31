package com.guy7cc.voxelodyssey.core.common;

import com.guy7cc.voxelodyssey.core.util.ReflectionUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class GeneralPluginLifecycleHandler {
    private final JavaPlugin plugin;
    private List<PluginLifecycleListener> list = new ArrayList<>();

    public GeneralPluginLifecycleHandler(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void add(PluginLifecycleListener listener){
        list.add(listener);
    }

    public void add(Collection<PluginLifecycleListener> listeners){
        listeners.forEach(this::add);
    }

    public void collect(Object obj){
        List<Object> list = ReflectionUtil.collectFields(obj, o -> o instanceof PluginLifecycleListener, plugin.getLogger());
        add(list.stream().map(o -> (PluginLifecycleListener) o).toList());
    }

    public void collectStatic(Class<?> clazz){
        List<Object> list = ReflectionUtil.collectStaticFields(clazz, o -> o instanceof PluginLifecycleListener, plugin.getLogger());
        add(list.stream().map(o -> (PluginLifecycleListener) o).toList());
    }

    public void executeLifecycleMethod(String methodName) {
        list.stream()
                .sorted(Comparator.comparingInt(listener -> getOrderValue(listener, methodName)))
                .forEach(listener -> invokeMethod(listener, methodName));
    }

    private int getOrderValue(PluginLifecycleListener listener, String methodName) {
        try {
            Method method = listener.getClass().getMethod(methodName, JavaPlugin.class);
            ExecutionOrder order = method.getAnnotation(ExecutionOrder.class);
            return order != null ? order.value().ordinal() : ExecutionOrder.Order.MIDDLE.ordinal();
        } catch (NoSuchMethodException e) {
            return ExecutionOrder.Order.MIDDLE.ordinal();
        }
    }

    private void invokeMethod(PluginLifecycleListener listener, String methodName) {
        try {
            Method method = listener.getClass().getMethod(methodName, JavaPlugin.class);
            method.invoke(listener, plugin);
        } catch (Exception e) {
            plugin.getLogger().log(
                    Level.SEVERE,
                    "Faild to invoke " + methodName,
                    e
            );
        }
    }

    public void onPluginEnabled() {
        executeLifecycleMethod("onPluginEnabled");
    }

    public void onFirstTick() {
        executeLifecycleMethod("onFirstTick");
    }

    public void onPluginDisabled() {
        executeLifecycleMethod("onPluginDisabled");
    }
}
