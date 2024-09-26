package org.nebich.infected.zombies;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.nebich.infected.player.InfectedPlayer;

public class Pigman implements ZombieRole {
    private final InfectedPlayer infectedPlayer;

    public Pigman(InfectedPlayer infectedPlayer) {
        this.infectedPlayer = infectedPlayer;
    }

    @Override
    public void transform() {
        final Player player = this.infectedPlayer.getPlayer();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
