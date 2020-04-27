package me.inao.discordbot.command;

import me.inao.discordbot.Main;
import me.inao.discordbot.ifaces.ICommand;
import me.inao.discordbot.objects.Countgame;
import me.inao.discordbot.util.ExceptionCatcher;
import me.inao.discordbot.util.PermissionCheck;
import org.javacord.api.entity.message.Message;

import java.util.Arrays;

public class Count implements ICommand {

    @Override
    public void onCommand(Main instance, Message message, String[] args) {
        if(!(hasPermission(instance, message))){
            return;
        }
        if(args.length < 1){
            return;
        }
        long finish = 1337;
        long prev = 0;
        try{
            finish = Long.decode(args[0]);
            if(args[1] != null){
                prev = Long.decode(args[1]);
            }
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        if(finish < 1){
            return;
        }
        if(instance.getCountgame() != null){
            return;
        }
        long finalFinish = finish;
        long prevFinish = prev;
        message.getServer().ifPresent(server-> instance.setCountgame(new Countgame(server.getChannelsByName(instance.getConfig().getCommandRoom(this.getClass().getSimpleName())).get(0).asServerTextChannel().get(), finalFinish, instance, prevFinish)));

    }

    @Override
    public String getUsage() {
        return "<finish> (prev)";
    }

    @Override
    public boolean hasPermission(Main main, Message message) {
        return new PermissionCheck(main).hasPermission(message.getServer().get(), message.getAuthor().asUser().get(), this.getClass().getSimpleName());
    }
}