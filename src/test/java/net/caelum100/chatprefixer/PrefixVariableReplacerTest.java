package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class PrefixVariableReplacerTest {

  @Test
  public void apply() {
    Chat chat = mock(Chat.class);
    final String prefix = "[Prefix]";
    when(chat.getPlayerPrefix(ArgumentMatchers.any())).thenReturn(prefix);

    Player player = mock(Player.class);
    assertEquals(new PrefixVariableReplacer(chat).apply(player), prefix);
  }
}
