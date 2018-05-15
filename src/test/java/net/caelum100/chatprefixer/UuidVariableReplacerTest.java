package net.caelum100.chatprefixer;

import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class UuidVariableReplacerTest {

    @Test
    public void apply() {
        Player player = mock(Player.class);
        final UUID uuid = UUID.randomUUID();
        when(player.getUniqueId()).thenReturn(uuid);

        assertEquals(new UuidVariableReplacer().apply(player), uuid.toString());
    }
}