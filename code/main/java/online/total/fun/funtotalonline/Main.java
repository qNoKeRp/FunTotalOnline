package online.total.fun.funtotalonline;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {
    public static HashMap<String, Integer> identifierValues = new HashMap<String, Integer>();
    public static Main instance;
    private ConfigManager data;

    @Override
    public void onEnable() {


        instance = this;
        saveDefaultConfig();
        data = new ConfigManager("config.yml");

        new Expansion().register();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                identifierValues.clear();
                for (String a : Main.getData().getConfig().getConfigurationSection("servers").getKeys(false)) {
                    int totalNum = 0;
                    for(String b : Main.getData().getConfig().getStringList("servers." + a + ".list")) {
                        try {
                            int num = Integer.parseInt(PlaceholderAPI.setPlaceholders(getServer().getPlayer("default"), b));
                            totalNum = totalNum + num;
                        } catch (NumberFormatException e) {
                            System.out.println("[FTO error] сервера " + b + " не существует" );
                        }
                    }
                    identifierValues.put(Main.getData().getConfig().getString("servers." + a + ".placeholder"), totalNum);
                }
            }
        }, 1, Main.getData().getConfig().getInt("timer") * 20);
    }
    public static Main getInstance() {
        return instance;
    }
    public static ConfigManager getData() { return instance.data; }
}