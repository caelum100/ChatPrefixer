package net.caelum100.chatprefixer;

import com.google.common.collect.ImmutableMap;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
  private ChatPrefixer plugin;
  private Chat chat;

  private final ImmutableMap<String, VariableReplacer> VARIABLES;

  public ChatListener(ChatPrefixer plugin) {
    this.plugin = plugin;
    this.chat = plugin.getChat();

    VARIABLES = ImmutableMap.of("prefix", new PrefixVariableReplacer(chat), "world", new WorldVariableReplacer(),
            "uuid", new UuidVariableReplacer(), "username", new UsernameVariableReplacer(),
            "suffix", new SuffixVariableReplacer(chat));
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    Player sender = event.getPlayer();

    String message = plugin.getFormat();
    for (String var : VARIABLES.keySet()) {
      message = message.replaceAll("\\$\\{" + var + "\\}", VARIABLES.get(var).apply(sender));
    }
    // Exclusive variable
    message = message.replaceAll("\\$\\{message}", event.getMessage());

    event.setFormat(message);
  }
}
