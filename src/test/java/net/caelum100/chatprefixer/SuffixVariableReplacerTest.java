package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class SuffixVariableReplacerTest {

  @Test
  public void apply() {
    Chat chat = mock(Chat.class);
    String suffix = "[Suffix]";
    when(chat.getPlayerSuffix(any())).thenReturn(suffix);
    Player player = mock(Player.class);

    assertEquals(suffix, new SuffixVariableReplacer(chat).apply(player));
  }
}