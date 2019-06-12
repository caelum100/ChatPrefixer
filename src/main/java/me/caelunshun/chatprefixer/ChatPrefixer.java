package me.caelunshun.chatprefixer;

import com.earth2me.essentials.Essentials;
import me.caelunshun.shun.UpdateChecker;
import net.milkbowl.vault.chat.Chat;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPrefixer extends JavaPlugin {
  private static final String ESSENTIALS = "EssentialsX";

  private Chat chat;
  private String format;
  private FileConfiguration config;

  private Essentials essentials;

  @Override
  public void onEnable() {
    RegisteredServiceProvider<Chat> provider =
        Bukkit.getServicesManager().getRegistration(Chat.class);
    if (provider == null) {
      Bukkit.getConsoleSender()
          .sendMessage(
              ChatColor.RED
                  + "[ChatPrefixer] Could not load Vault Chat API.\n"
                  + "This most likely means that you need to install a Vault-compatible permissions plugin.");
      Bukkit.getPluginManager().disablePlugin(this);
      return;
    }
    chat = provider.getProvider();

    MetricsLite metrics = new MetricsLite(this);
    saveDefaultConfig();

    config = getConfig();
    format = ChatColor.translateAlternateColorCodes('&', config.getString("format"));

    if (config.getBoolean("checkForUpdates")) new UpdateChecker(this, "56778");

    if (Bukkit.getPluginManager().isPluginEnabled(ESSENTIALS)) {
      this.essentials = (Essentials) Bukkit.getPluginManager().getPlugin(ESSENTIALS);
    }

    Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
  }

  @Override
  public void onDisable() {}

  public String getFormat() {
    return format;
  }

  public Chat getChat() {
    return chat;
  }

  public Essentials getEssentials() { return essentials; }
}
