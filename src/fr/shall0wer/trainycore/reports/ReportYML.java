package fr.shall0wer.trainycore.reports;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ReportYML {

    public static Boolean existPlayerReportFile(String player){
        return new File("/plugins/TrainyCore/" + player + ".yml").exists();
    }

    public static void createPlayerReportFile(String player) throws IOException {
        File file = new File("/plugins/TrainyCore/" + player + ".yml");
        file.createNewFile();
    }

    public static void registerMessage(Player player, String UUID, String message){
        if(existPlayerReportFile(player.getName())){

        }
    }
}
