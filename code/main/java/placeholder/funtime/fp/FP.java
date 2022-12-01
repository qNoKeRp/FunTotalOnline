package placeholder.funtime.fp;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class FP extends JavaPlugin {
    public static HashMap<String, Integer> identifierValues = new HashMap<String, Integer>();
    public static FP instance;
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
                for (String a : FP.getData().getConfig().getConfigurationSection("servers").getKeys(false)) {
                    int totalNum = 0;
                    for(String b : FP.getData().getConfig().getStringList("servers." + a + ".list")) {
                        try {
                            int num = Integer.parseInt(PlaceholderAPI.setPlaceholders(getServer().getPlayer("default"), b));
                            totalNum = totalNum + num;
                        } catch (NumberFormatException e) {
                            System.out.println("[FP error] сервера " + b + " не существует" );
                        }
                    }
                    identifierValues.put(FP.getData().getConfig().getString("servers." + a + ".placeholder"), totalNum);
                }
            }
        }, 1, FP.getData().getConfig().getInt("timer") * 20);
    }
    public static FP getInstance() {
        return instance;
    }
    public static ConfigManager getData() { return instance.data; }
}
