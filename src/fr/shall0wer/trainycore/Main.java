package fr.shall0wer.trainycore;

import fr.shall0wer.trainycore.commands.*;
import fr.shall0wer.trainycore.commands.misc.ChatCommand;
import fr.shall0wer.trainycore.commands.misc.DiscordCommand;
import fr.shall0wer.trainycore.commands.misc.YoutubeCommand;
import fr.shall0wer.trainycore.gui.GuiSanctionMain;
import fr.shall0wer.trainycore.gui.GuiSanctionMessages;
import fr.shall0wer.trainycore.managers.EventsManager;
import fr.shall0wer.trainycore.utils.GuiBuilder;
import fr.shall0wer.trainycore.utils.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin{
    public static Main instance;

    private GuiManager guiManager;
    private Map<Class <? extends GuiBuilder>, GuiBuilder> registeredMenus;

    public GuiManager getGuiManager() {
        return guiManager;
    }
    public Map<Class<? extends GuiBuilder>, GuiBuilder> getRegisteredMenus() {
        return registeredMenus;
    }
    public Map<Player, String> sanctionsCorrespond = new HashMap<>();
    public Map<Player, String> targetPremium = new HashMap<>();
    public Map<Player, String> reportCorrespond = new HashMap<>();
    public ArrayList<Player> moderateurs = new ArrayList<>();
    public HashMap<UUID, Location> freezedPlayers = new HashMap<>();
    public Map<UUID, Double> cpsverif = new HashMap<>();
    public Map<UUID, Double> cpsverifright = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        loadGui();

        EventsManager.registers();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getCommand("pinfo").setExecutor(new PinfoCommand());
        getCommand("punish").setExecutor(new SanctionCommand());
        getCommand("uuid").setExecutor(new UuidCommand());
        getCommand("testcore").setExecutor(new TestCoreCommand());
        getCommand("tete").setExecutor(new TeteCommand());
        getCommand("rclist").setExecutor(new RcListCommand());
        getCommand("chatreport").setExecutor(new ChatReportCommand());
        getCommand("dev").setExecutor(new DevCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("mod").setExecutor(new ModCommand());
        getCommand("tp").setExecutor(new TpCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("chat").setExecutor(new ChatCommand());
        getCommand("youtube").setExecutor(new YoutubeCommand());


        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            File file = new File(getDataFolder() + "/config.yml");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    private void loadGui(){
        guiManager = new GuiManager();
        registeredMenus = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(guiManager, this);
        guiManager.addMenu(new GuiSanctionMain());
        guiManager.addMenu(new GuiSanctionMessages());
    }

}
