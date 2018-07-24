package net.caelum100.chatprefixer;

import org.bukkit.entity.Player;

public class UsernameVariableReplacer implements VariableReplacer {
  @Override
  public String apply(Player sender) {
    return sender.getName();
  }
}
