package me.inao.discordbot.command;

import me.inao.discordbot.Main;
import me.inao.discordbot.ifaces.ICommand;
import me.inao.discordbot.ifaces.Permissionable;
import me.inao.discordbot.util.MessageSender;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public class Ticket extends Permissionable implements ICommand {
    @Override
    public void onCommand(Main instance, Message message, String[] args) {
        if(!hasPermission(instance, message, this.getClass())){
            new MessageSender("No Permissions", instance.getConfig().getMessage("generic", "no_perms"), Color.RED, message.getChannel());
            return;
        }
        if (args != null) {
            if(args.length < 1){
                new MessageSender("Not enough arguments", instance.getConfig().getMessage("generic", "no_args"), Color.RED, message.getChannel());
                return;
            }
            new MessageSender("Not enough arguments", instance.getConfig().getMessage("generic", "no_args"), Color.RED, message.getChannel());
            return;
        }

    }

    @Override
    public String getUsage() {
        return "<reason>";
    }
}
