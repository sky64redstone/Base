package de.base.configuration.yaml;

import de.base.configuration.ConfigurationFile;
import de.base.utils.ArrayUtils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This Class helps you to create/read/write an {@code .yaml} File.
 * <p>It uses a {@link BufferedReader} and {@link BufferedWriter} to read and write the {@link File}</p>
 */
public class YamlFile implements ConfigurationFile<String, String> {

    private final Map<String, String>map = new HashMap<>();
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final String separator = ": ";
    private final File file;

    /**
     * For Example: your File: C:\\User\Username\Desktop\myFile.yaml
     * @param fileName (Example: myFile)
     * @param path (Example: C:\\User\Username\Desktop\)
     */
    public YamlFile(String path, @NotNull String fileName) {
        this.file = new File(path + fileName + ".yaml");

        try {
            this.writer = new BufferedWriter(new FileWriter(file));
            this.reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!file.exists())
            createFile();
    }

    /**
     * For Example: your File: C:\\User\Username\Desktop\myFile.yaml
     * @param file (Example: {@code new File(C:\\User\Username\Desktop\myFile.yaml)})
     */
    public YamlFile(@NotNull File file) {
        this.file = file;

        try {
            this.writer = new BufferedWriter(new FileWriter(file));
            this.reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!file.exists())
            createFile();
    }

    /**
     * Writes in the {@link Map} map
     * @param key normally you are searching with a key for data
     * @param data the Data you want to write down
     */
    @Override
    public void write(String key, String data) {
        if (map.isEmpty())
            load();

        if (!map.containsKey(key))
            map.put(key, data);
        else
            System.out.println("[Key does already exists] " + key + separator + data);
    }

    /**
     * The final save in the File
     */
    @Override
    public void save() {
        if (!this.file.exists())
            createFile();

        if (!map.isEmpty()) {
            map.forEach((key, data) -> {
                try {
                    writer.write(key + separator + data + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @param key search for the data wich was written behind the key
     * @return a String with the data
     */
    @Override
    public String read(String key) {
        if (!map.get(key).isEmpty()) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Writes all existing data of the document to the map
     */
    @Override
    public void load() {
        String[] file = readFile();
        for (String s : file) {
            map.put(s.split(separator)[0], s.split(separator)[1]);
        }
    }

    /**
     * Clears the Map and writes all existing data of the document to the map
     */
    @Override
    public void reload() {
        map.clear();
        load();
    }

    /**
     *
     * @return the File split in its lines
     */
    private String[] readFile() {
        String[] toReturn = new String[0], tempReturn = new String[1];
        String line;
        int i = 0;
        try {
            while ((line = reader.readLine()) != null) {
                toReturn = ArrayUtils.add(toReturn, line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    private void createFile() {
        try {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
