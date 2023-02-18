package fr.shall0wer.trainycore.managers;

import fr.shall0wer.trainycore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ReportManager {

    public static Boolean isReportExist(String uuid){
        File file = new File("/home/DataBase/Reports/Confirmed/" + uuid + ".yml");
        return file.exists();
    }

    public static void saveChatMessage(Player player, String message, UUID uuid) throws IOException {
        FileConfiguration config = null;
        File file = new File("/home/DataBase/Reports/Confirmed/" + uuid + ".yml");
        file.createNewFile();
        config = YamlConfiguration.loadConfiguration(file);
        config.set("Joueur", player.getName());
        config.set("Message", message);
        config.save(file);
    }

    public static Boolean hasFileReportWaiting(){
        File file = new File("/home/DataBase/Reports/Confirmed");
        if(file.isDirectory() && file.list().length == 0){
            return false;
        }
        return true;
    }

    public static final Integer countReportWaiting(){
        File file = new File("/home/DataBase/Reports/Confirmed");
        if(file.isDirectory()){
            return file.list().length;
        } else {
            return null;
        }
    }

    public static String getInfoReport(String uuid, String path){
        FileConfiguration config = null;
        File file = new File("/home/DataBase/Reports/Confirmed/" + uuid + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config.getString(path);
    }

    public static String getPreInfoReport(String uuid, String path){
        FileConfiguration config = null;
        File file = new File(Main.getInstance().getDataFolder() + "/" + uuid + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config.getString(path);
    }

    public static void deleteReport(String path){
        File file = new File("/home/DataBase/Reports/Confirmed/" + path + ".yml");
        file.delete();
    }

    public static String getNameFileReport(){
        File folder = new File("/home/DataBase/Reports/Confirmed");
        File[] files = folder.listFiles(); // -> liste tous les fichiers
        String str = "";
        Integer i = 0;
        while (i == 0){
            for (File file : files) {
                if (file.isFile()) {
                    str = file.getName();
                    i++;
                }
            }
        }
        // File file = new File("/home/DataBase/Prefixes/" + str + ".yml");
        return str;
    }



    public static void saveReport(String uuid, String player, String msg) throws IOException {
        FileConfiguration config = null;
        File file = new File( Main.getInstance().getDataFolder().toString() + "/" + uuid + ".yml");
        file.createNewFile();
        config = YamlConfiguration.loadConfiguration(file);
        config.set("Joueur", player);
        config.set("Message", msg);
        config.save(file);
    }

    public static Boolean existReport(String uuid){
        File file = new File(Main.getInstance().getDataFolder().toString() + "/" + uuid + ".yml");
        return file.exists();
    }

    public static void setReporter(String uuid, String reporter) throws IOException {
        FileConfiguration config = null;
        File file = new File(Main.getInstance().getDataFolder().toString() + "/" + uuid + ".yml");
        config = YamlConfiguration.loadConfiguration(file);
        config.set("Reporter", reporter);
        config.save(file);
    }

    public static void setReportInConfirmation(String uuid) throws IOException {
        FileConfiguration config = null;
        File file = new File(Main.getInstance().getDataFolder().toString() + "/" + uuid + ".yml");
        String dest = "/home/DataBase/Reports/Confirmed/" + uuid + ".yml";
        Path tmp = Files.move(Paths.get(file.getPath()), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
    }

    public static Boolean isAlreadyReported(String uuid){
        File file = new File("/home/DataBase/Reports/Confirmed/" + uuid + ".yml");
        return file.exists();
    }
}
