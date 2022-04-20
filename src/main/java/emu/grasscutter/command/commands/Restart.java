package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.GenshinPlayer;

import java.util.List;

@Command(label = "restart", usage = "restart - Restarts the current session")
public class Restart implements CommandHandler {

    @Override
    public void onCommand(GenshinPlayer sender, List<String> args) {
        sender.getSession().close();
    }
}
