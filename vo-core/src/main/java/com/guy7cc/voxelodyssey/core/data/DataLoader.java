package com.guy7cc.voxelodyssey.core.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.guy7cc.voxelodyssey.core.common.ExecutionOrder;
import com.guy7cc.voxelodyssey.core.common.PluginLifecycleListener;
import com.guy7cc.voxelodyssey.core.util.LogUtil;
import com.guy7cc.voxelodyssey.core.util.ReflectionUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class DataLoader implements PluginLifecycleListener {
    private final JavaPlugin plugin;
    private final File file;
    private final JsonFileIO gson;
    private final String version;
    private final java.util.List<DataHolder> holders = new ArrayList<>();

    private InvalidDataReason reason = null;

    public DataLoader(JavaPlugin plugin, File file, JsonFileIO json, String version) {
        this.plugin = plugin;
        this.file = file;
        this.gson = json;
        this.version = version;
    }

    public void collectHolders(Object obj){
        java.util.List<Object> list = ReflectionUtil.collectFields(obj, o -> o instanceof DataHolder, plugin.getLogger());
        holders.addAll(list.stream().map(o -> (DataHolder) o).toList());
    }

    public void collectStaticHolders(Class<?> clazz){
        java.util.List<Object> list = ReflectionUtil.collectStaticFields(clazz, o -> o instanceof DataHolder, plugin.getLogger());
        holders.addAll(list.stream().map(o -> (DataHolder) o).toList());
    }

    public String getVersion(){
        return version;
    }

    @Override
    @ExecutionOrder(ExecutionOrder.Order.LATE)
    public void onPluginEnabled(JavaPlugin plugin) {
        load();
    }

    @Override
    @ExecutionOrder(ExecutionOrder.Order.LATE)
    public void onPluginDisabled(JavaPlugin plugin) {
        save();
    }

    public void load() {
        if(file.exists()){
            JsonElement element = gson.load(file);
            if(element == null || !element.isJsonObject()){
                markAsInvalid(InvalidDataReason.BROKEN);
                return;
            }
            JsonObject data = element.getAsJsonObject();
            if (!matchVersion(data)) {
                markAsInvalid(InvalidDataReason.OLD);
                return;
            }
            for (var holder : holders) {
                if (data.has(holder.getHolderName()) && data.get(holder.getHolderName()).isJsonObject())
                    holder.load(data.getAsJsonObject(holder.getHolderName()));
                else {
                    markAsInvalid(InvalidDataReason.BROKEN);
                    return;
                }
            }
        } else {
            markAsInvalid(InvalidDataReason.EMPTY);
            return;
        }
    }

    public void save() {
        JsonObject data = new JsonObject();
        data.addProperty("version", getVersion());
        for (var holder : holders) {
            data.add(holder.getHolderName(), holder.getWrittenData());
        }
        gson.save(data, file);
    }

    public void markAsInvalid(InvalidDataReason reason){
        if(this.reason != null) return;
        this.reason = reason;
        if(reason.renameRequired()) rename();
        plugin.getLogger().warning(reason.getMessage());
        reset();
    }

    private boolean matchVersion(JsonObject data) {
        String key = "version";
        return data.has(key)
                && data.get(key).isJsonPrimitive()
                && data.get(key).getAsString().equals(getVersion());
    }

    private boolean rename(){
        if(reason == null || !reason.renameRequired()) return true;
        File to = reason.addSuffix(file);
        try {
            if (!file.renameTo(to)) {
                plugin.getLogger().severe("Cannot rename data file to " + to.getName());
                return false;
            }
            return true;
        } catch(SecurityException e) {
            LogUtil.exception(plugin.getLogger(), String.format("Could not move and rename data file to %s due to security problem.", to.getName()), e);
        }
        return false;
    }

    private void reset() {
        for (var holder : holders) {
            holder.load(new JsonObject());
        }
    }
}
