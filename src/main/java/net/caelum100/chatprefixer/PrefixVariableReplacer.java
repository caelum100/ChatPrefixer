package net.caelum100.chatprefixer;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;

public class PrefixVariableReplacer implements VariableReplacer {
    private Chat chat;
    public PrefixVariableReplacer(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String apply(Player sender) {
        return chat.getPlayerPrefix(sender);
    }
}
