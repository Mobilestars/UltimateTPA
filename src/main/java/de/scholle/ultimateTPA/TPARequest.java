package de.scholle.ultimateTPA;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TPARequest {
    public final Player sender;
    public final Player receiver;
    public final boolean here;
    public final long created;
    public Location senderLocation;
    public boolean teleporting = false;

    public TPARequest(Player sender, Player receiver, boolean here) {
        this.sender = sender;
        this.receiver = receiver;
        this.here = here;
        this.created = System.currentTimeMillis();
        this.senderLocation = sender.getLocation().clone();
    }

    public boolean isExpired(long expireMillis) {
        return (System.currentTimeMillis() - created) >= expireMillis;
    }
}
