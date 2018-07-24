package net.caelum100.chatprefixer;

import net.caelum100.chatprefixer.updatechecker.ChatPrefixerUpdater;
import net.milkbowl.vault.chat.Chat;
import org.bstats.bukkit.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPrefixer extends JavaPlugin {
  private Chat chat;
  private String format;
  private FileConfiguration config;

  @Override
  public void onEnable() {
    RegisteredServiceProvider<Chat> provider = Bukkit.getServicesManager().getRegistration(Chat.class);
    if (provider == null) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ChatPrefixer] Could not load Vault Chat API.\n" +
              "This most likely means that you need to install a Vault-compatible permissions plugin.");
      Bukkit.getPluginManager().disablePlugin(this);
      return;
    }
    chat = provider.getProvider();

    MetricsLite metrics = new MetricsLite(this);
    ChatPrefixerUpdater updater = new ChatPrefixerUpdater(this);
    saveDefaultConfig();

    config = getConfig();
    format = ChatColor.translateAlternateColorCodes('&', config.getString("format"));

    Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
  }

  @Override
  public void onDisable() {

  }

  public String getFormat() {
    return format;
  }

  public Chat getChat() {
    return chat;
  }
}
