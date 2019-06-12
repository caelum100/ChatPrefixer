package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AsyncPlayerChatEvent.class)
public class ChatListenerTest {

  @Test
  public void onChat() {
    AsyncPlayerChatEvent event = PowerMockito.mock(AsyncPlayerChatEvent.class);
    Player sender = mock(Player.class);
    when(sender.getName()).thenReturn("TestPlayer");
    when(sender.getUniqueId()).thenReturn(UUID.randomUUID());
    World world = mock(World.class);
    when(world.getName()).thenReturn("TestWorld");
    when(sender.getWorld()).thenReturn(world);

    PowerMockito.when(event.getPlayer()).thenReturn(sender);
    when(event.getMessage()).thenReturn("TestMessage");

    ChatPrefixer plugin = mock(ChatPrefixer.class);
    Chat chat = mock(Chat.class);
    when(chat.getPlayerSuffix(any())).thenReturn(" [TestSuffix]");
    when(chat.getPlayerPrefix(any())).thenReturn("[TestPrefix] ");
    when(plugin.getChat()).thenReturn(chat);
    when(plugin.getFormat()).thenReturn("${prefix}${username} ${world}${suffix}: ${message}");

    AtomicReference<Object> result = new AtomicReference<>();

    Mockito.doAnswer(
            new Answer<Void>() {
              @Override
              public Void answer(InvocationOnMock i) {
                result.set(i.getArguments()[0]);
                return null;
              }
            })
        .when(event)
        .setFormat(ArgumentMatchers.anyString());

    ChatListener listener = new ChatListener(plugin);
    listener.onChat(event);

    Assert.assertEquals(
        "[TestPrefix] TestPlayer TestWorld" + " [TestSuffix]: TestMessage", result.get());
  }
}
