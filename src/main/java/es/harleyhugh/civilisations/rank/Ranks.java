package es.harleyhugh.civilisations.rank;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Ranks {

    DEFAULT(Component.text("Member"), TextColor.color(9510456), TextColor.color(13862946), 0),
    MOD(Component.text("Mod"), TextColor.color(7214382), TextColor.color(7214382), 1),
    ADMIN(Component.text("Admin"), TextColor.color(7214382), TextColor.color(7214382), 2),
    BUILDER(Component.text("Builder"), TextColor.color(7214382), TextColor.color(7214382), 3),
    DEVELOPER(Component.text("Dev"), TextColor.color(7214382), TextColor.color(7214382), 4),
    OWNER(Component.text("Owner"), TextColor.color(7214382), TextColor.color(7214382), 5);

    Ranks(TextComponent prefix, TextColor nameColour, TextColor chatColour, int weight) {
        this.prefix = prefix;
        this.weight = weight;
        this.nameColour = nameColour;
        this.chatColour = chatColour;
    }

    private final TextComponent prefix;
    private final int weight;
    private final TextColor nameColour;
    private final TextColor chatColour;

    public TextComponent getPrefix() {
        return this.prefix;
    }

    public int getWeight() {
        return this.weight;
    }

    public TextColor chatColour() {
        return this.chatColour;
    }

    public TextColor nameColour() {
        return this.nameColour;
    }





}
