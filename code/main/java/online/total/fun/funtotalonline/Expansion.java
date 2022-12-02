package online.total.fun.funtotalonline;

import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import static online.total.fun.funtotalonline.Main.identifierValues;
public class Expansion extends PlaceholderExpansion implements Cacheable {

    public @NotNull String getIdentifier() {
        return "fto";
    }

    public @NotNull String getAuthor() {
        return "qNoKeRp";
    }

    public @NotNull String getVersion() {
        return "1.1";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String identifier) {

        for(String i : identifierValues.keySet()) {
            if(identifier.equals(i)) {
                return String.valueOf(identifierValues.get(i));
            }
        }

        return null;
    }


    @Override
    public void clear() {
    }

}