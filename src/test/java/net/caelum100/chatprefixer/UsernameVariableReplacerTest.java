package net.caelum100.chatprefixer;

import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class UsernameVariableReplacerTest {

    @Test
    public void apply() {
        Player player = mock(Player.class);
        final String name = "Name";
        when(player.getName()).thenReturn(name);

        assertEquals(new UsernameVariableReplacer().apply(player), name);
    }
}