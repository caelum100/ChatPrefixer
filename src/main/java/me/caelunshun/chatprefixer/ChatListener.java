package me.caelunshun.chatprefixer;

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

    VARIABLES =
        new ImmutableMap.Builder<String, VariableReplacer>()
            .put("prefix", new PrefixVariableReplacer(chat))
            .put("world", new WorldVariableReplacer())
            .put("uuid", new UuidVariableReplacer())
            .put("username", new UsernameVariableReplacer())
            .put("suffix", new SuffixVariableReplacer(chat))
            .put("nickname", new NicknameVariableReplacer(plugin))
            .build();
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    Player sender = event.getPlayer();

    String message = plugin.getFormat();
    for (String var : VARIABLES.keySet()) {
      if (!message.contains("${" + var + "}")) continue;

      message = message.replaceAll("\\$\\{" + var + "\\}", VARIABLES.get(var).apply(sender));
    }
    // Exclusive variable
    message = message.replaceAll("\\$\\{message}", event.getMessage());

    event.setFormat(message);
  }
}
