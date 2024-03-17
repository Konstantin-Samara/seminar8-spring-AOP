package com.example.seminar8springAOP.myFactorys;

import com.example.seminar8springAOP.models.Command;

public class CommandFactoryImpl implements CommandFactory{
    public Command createCommand(String description) {

        Command.CommandBuilder commandBuilder = new Command.CommandBuilder();
        commandBuilder.description(description);

        return commandBuilder.description(description).build();
    }
}
