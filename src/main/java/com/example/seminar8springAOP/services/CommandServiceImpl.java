package com.example.seminar8springAOP.services;

import com.example.seminar8springAOP.factorys.CommandFactoryImpl;
import com.example.seminar8springAOP.datas.CommandRepository;
import com.example.seminar8springAOP.models.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class CommandServiceImpl implements CommandService{
    @Autowired
    private CommandRepository commandRepository;

    public Command createCommand(Command note) {return commandRepository.save(note);}

    public List<String> getAllCommands() {

        return commandRepository.findAll().stream()
                .sorted(Comparator.comparing(Command::getId))
                .map(Command::toString).toList();
    }

    public Command getCommandById(Long id) {

        Command command = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command with ID:"+id+"  not found"));
        return command;

    }
    public List<String> getCommandByStatus() {

        return commandRepository.findAll().stream()
                .filter(it->it.getStatus().equals(Command.Status.Running))
                .sorted(Comparator.comparing(Command::getId))
                .map(Command::toString).toList();
    }
    public void removePerformerIdFromCommandList(ArrayList<Long> listCommandsId, long performerId){

        listCommandsId.forEach(it->{
            Command tempCommand = getCommandById(it);
            tempCommand.getPerformersId().remove(performerId);
            commandRepository.save(tempCommand);});
    }
    public ArrayList<Long> deleteCommand(long id) {

        ArrayList<Long> listPerformersId = new ArrayList<>();
        listPerformersId = getCommandById(id).getPerformersId();
        commandRepository.deleteById(id);

        return listPerformersId;
    }

    public ArrayList<Long> changeCommandStatus(Long id) {
        ArrayList<Long> listPerformersId = new ArrayList<>();
        Command existingCommand = getCommandById(id);
        if (existingCommand.getStatus().equals(Command.Status.Running)){
            existingCommand.setStatus(Command.Status.Completed);
            if (!existingCommand.getPerformersId().isEmpty()){
                listPerformersId = existingCommand.getPerformersId();}
            existingCommand.setRunningDate("no Date");
        }
        else {
            existingCommand.setStatus(Command.Status.Running);
            existingCommand.setRunningDate(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        commandRepository.save(existingCommand);
        return listPerformersId;
    }

    public boolean changeCommandPerformer(Long id, Long id1){

        boolean removePerformer = true;
        Command existingCommand = getCommandById(id);
        if (!existingCommand.getPerformersId().contains(id1)){
            existingCommand.getPerformersId().add(id1);
            removePerformer=false;}
        else {existingCommand.getPerformersId().remove(id1);}
        commandRepository.save(existingCommand);

        return removePerformer;
    }

    public List<String> complete() {

        CommandFactoryImpl commandFactory = new CommandFactoryImpl();
        ArrayList<Command> commands = new ArrayList<>();
        commandRepository.deleteAll();
        String[] descriptions = {"GO","RUN","JUMP","EAT","TALK","STAND","CRY","SOMETHING COMMAND"};

        Arrays.stream(descriptions).forEach(it->commands.add(commandFactory.createCommand(it)));
        commandRepository.saveAll(commands);

        return commands.stream().map(Command::toString).toList();

    }

}
