package me.caelunshun.chatprefixer;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class NicknameVariableReplacerTest {

  @Test
  public void apply() {
    Player player = mock(Player.class);
    UUID uuid = UUID.randomUUID();
    when(player.getUniqueId()).thenReturn(uuid);

    final String nick = "Nick";

    User user = mock(User.class);
    when(user.getNick(false, false, false)).thenReturn(nick);

    Essentials essentials = mock(Essentials.class);
    when(essentials.getUser(uuid)).thenReturn(user);

    ChatPrefixer plugin = mock(ChatPrefixer.class);
    when(plugin.getEssentials()).thenReturn(essentials);

    assertEquals(new NicknameVariableReplacer(plugin).apply(player), "Nick");
  }
}
