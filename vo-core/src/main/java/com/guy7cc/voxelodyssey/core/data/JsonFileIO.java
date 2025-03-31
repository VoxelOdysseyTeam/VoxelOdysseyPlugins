package com.guy7cc.voxelodyssey.core.data;

import com.google.gson.*;
import com.guy7cc.voxelodyssey.core.util.LogUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;

public class JsonFileIO {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UuidAdapter())
            .setPrettyPrinting()
            .create();
    private final Logger logger;

    public JsonFileIO(Logger logger){
        this.logger = logger;
    }

    public void save(@NotNull JsonElement element, @NotNull File file) {
        try {
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(gson.toJson(element));
            writer.close();
        } catch (IOException exception) {
            LogUtil.exception(logger, String.format("Could not save %s due to I/O errors. ", file), exception);
        } catch (SecurityException exception) {
            LogUtil.exception(logger, String.format("Could not save %s due to security problems.", file), exception);
        }
    }

    @Nullable
    public JsonElement load(@NotNull File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return JsonParser.parseString(sb.toString());
        } catch (FileNotFoundException exception) {
            return null;
        } catch (IOException exception) {
            LogUtil.exception(logger, String.format("Could not read %s due to I/O errors.", file), exception);
        } catch (JsonParseException exception) {
            LogUtil.exception(logger, String.format("Could not read %s because the file format was invalid.", file), exception);
        } catch (ClassCastException exception) {
            LogUtil.exception(logger, String.format("Could not read %s because the element was not JsonObject", file), exception);
        }
        return null;
    }
}
