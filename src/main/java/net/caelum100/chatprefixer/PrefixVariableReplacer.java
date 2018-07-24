package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PrefixVariableReplacer implements VariableReplacer {
  private Chat chat;

  public PrefixVariableReplacer(Chat chat) {
    this.chat = chat;
  }

  @Override
  public String apply(Player sender) {
    return ChatColor.translateAlternateColorCodes('&', chat.getPlayerPrefix(sender));
  }
}
