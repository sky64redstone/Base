package de.base.configuration;

public interface ConfigurationFile<Key, Data> {

    String read(Key key);

    void write(Key key, Data data);

    void save();

    void load();

    void reload();

}
