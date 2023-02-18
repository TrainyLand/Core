package fr.shall0wer.trainycore.managers;

import fr.shall0wer.trainycore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CoreManager {

    public static Boolean getMaintenance(){
        FileConfiguration config = null;
        File file = new File(Main.getInstance().getDataFolder() + "/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("Maintenance");
    }
    public static void setMaintenance(Boolean b) throws IOException {
        FileConfiguration config = null;
        File file = new File(Main.getInstance().getDataFolder() + "/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        config.set("Maintenance", b);
        config.save(file);
    }
}
