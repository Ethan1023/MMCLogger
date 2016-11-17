package net.moddedminecraft.mmclogger;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class EventListener {
    private Main plugin;

    public EventListener(Main main) {
        plugin = main;
    }

    @Listener
    public void onPlayerChat(MessageChannelEvent.Chat event, @Root Player player)
    {
        String name = player.getName();
        String message = TextSerializers.FORMATTING_CODE.serialize(event.getFormatter().getBody().toText());
        Location<World> location = player.getLocation();
        int xLocation = (int) location.getX();
        int yLocation = (int) location.getY();
        int zLocation = (int) location.getZ();
        String world = location.getExtent().getName();
        String date = plugin.getDate();
        try {
            plugin.checkPlayer(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        plugin.processInformation(player, name, message, xLocation, yLocation, zLocation, world, date);
    }

    @Listener
    public void onPlayerCommand (SendCommandEvent event, @Root Player player) throws IOException {
        String command = event.getCommand();
        String name = player.getName();
        Location<World> location = player.getLocation();
        int xLocation = (int) location.getX();
        int yLocation = (int) location.getY();
        int zLocation = (int) location.getZ();
        String world = location.getExtent().getName();
        String date = plugin.getDate();
        plugin.checkPlayer(name);

        plugin.processCMDInformation(player, name, command, xLocation, yLocation, zLocation, world, date);
    }
}