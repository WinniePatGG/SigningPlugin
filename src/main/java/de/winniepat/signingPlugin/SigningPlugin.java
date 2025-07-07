package de.winniepat.signingPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SigningPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("sign")).setExecutor(new SignCommand());
        getLogger().info("enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("disabled");
    }
}
