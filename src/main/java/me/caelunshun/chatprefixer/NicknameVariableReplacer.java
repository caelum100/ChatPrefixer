package me.caelunshun.chatprefixer;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import me.caelunshun.shun.ShunUtil;
import net.ess3.api.IUser;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NicknameVariableReplacer implements VariableReplacer {
  private final ChatPrefixer plugin;

  public NicknameVariableReplacer(ChatPrefixer plugin) {
    this.plugin = plugin;
  }

  @Override
  public String apply(Player sender) {
    Essentials essentials = plugin.getEssentials();
    if (essentials == null) {
      plugin.getLogger().warning("Attempting to use Essentials nicknames in chat format, but Essentials is not installed.");
      return "";
    }

    IUser user = essentials.getUser(sender.getUniqueId());
    return ShunUtil.colorCodes(((User) user).getNick(false, false, false));
  }
}
