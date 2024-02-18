package com.example.seminar8springAOP.services;

import com.example.seminar8springAOP.annotacions.LoggedExecution;
import com.example.seminar8springAOP.datas.CommandRepository;
import com.example.seminar8springAOP.datas.PerformerRepository;
import com.example.seminar8springAOP.models.Command;
import com.example.seminar8springAOP.models.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
@Service
public class My_Service {
    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private PerformerRepository performerRepository;


    public Command createCommand(Command note) {

        return commandRepository.save(note);
    }

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

    @LoggedExecution
    public Command changeCommandStatus(Long id) {

        Command existingCommand = getCommandById(id);
        if (existingCommand.getStatus().equals(Command.Status.Running)){
            existingCommand.setStatus(Command.Status.Completed);
            if (!existingCommand.getPerformersId().isEmpty()){
                removeCommandIdFromPerformerList(existingCommand);}
            existingCommand.setRunningDate("no Date");
        }
        else {
            existingCommand.setStatus(Command.Status.Running);
            existingCommand.setRunningDate(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return commandRepository.save(existingCommand);
    }

    public void deleteCommand(Long id) {

        Command tempCommand = getCommandById(id);
        if(!tempCommand.getPerformersId().isEmpty()){
            removeCommandIdFromPerformerList(tempCommand);}
        commandRepository.deleteById(id);
    }

    public List<String> getCommandByStatus() {

        return commandRepository.findAll().stream()
                .filter(it->it.getStatus().equals(Command.Status.Running))
                .sorted(Comparator.comparing(Command::getId))
                .map(Command::toString).toList();
    }

    public Performer createPerformer(Performer note) {

        return performerRepository.save(note);
    }

    public List<String> getAllPerformers() {

        return performerRepository.findAll().stream()
                .sorted(Comparator.comparing(Performer::getId))
                .map(Performer::toString).toList();
    }

    public Performer getPerformerById(Long id) {

        Performer performer = performerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performer with ID:"+id+" not found"));
        return performer;
    }

    public void deletePerformer(Long id) {

        Performer tempPerformer = getPerformerById(id);
        if (!tempPerformer.getCommandsId().isEmpty()){
            removePerformerIdFromCommandList(tempPerformer);}
        performerRepository.deleteById(id);
    }

    public List<String> getPerformerByStatus() {

        return performerRepository.findAll().stream()
                .filter(it->it.getStatus().equals(Performer.Status.Working))
                .sorted(Comparator.comparing(Performer::getId))
                .map(Performer::toString).toList();
    }

    public String changeCommandPerformer(Long id, Long id1){

        String part1 ="removed from command";
        Command existingCommand = getCommandById(id);
        Performer existingPerformer = getPerformerById(id1);
        if (!existingCommand.getPerformersId().contains(id1)){
            existingCommand.getPerformersId().add(id1);
            existingPerformer.getCommandsId().add(id);
            existingPerformer.setStatus(Performer.Status.Working);
            part1="added to command";}
        else {
            existingCommand.getPerformersId().remove(id1);
            existingPerformer.getCommandsId().remove(id);
            if (existingPerformer.getCommandsId().isEmpty()){
                existingPerformer.setStatus(Performer.Status.Waiting);}
        }
        String message = "Performer (id:"+id1+" name:"+existingPerformer.getName()+") "+part1
                +" (id:"+id+" description:"+existingCommand.getDescription()+")";
        performerRepository.save(existingPerformer);
        commandRepository.save(existingCommand);
        return message;
    }

    private void removePerformerIdFromCommandList(Performer performer){

        performer.getCommandsId().forEach(it->{
            Command tempCommand = getCommandById(it);
            tempCommand.getPerformersId().remove(performer.getId());
            commandRepository.save(tempCommand);});
    }

    private void removeCommandIdFromPerformerList(Command command){

        command.getPerformersId().forEach(it->{
            Performer tempPerformer = getPerformerById(it);
            tempPerformer.getCommandsId().remove(command.getId());
            if (tempPerformer.getCommandsId().isEmpty()){
                tempPerformer.setStatus(Performer.Status.Waiting);}
            performerRepository.save(tempPerformer);});
    }

}
