package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SuffixVariableReplacer implements VariableReplacer {
  private Chat chat;

  public SuffixVariableReplacer(Chat chat) {
    this.chat = chat;
  }

  @Override
  public String apply(Player player) {
    return ChatColor.translateAlternateColorCodes('&', chat.getPlayerSuffix(player));
  }
}
