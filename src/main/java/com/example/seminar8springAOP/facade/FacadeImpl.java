package com.example.seminar8springAOP.facade;

import com.example.seminar8springAOP.annotacions.LoggedExecution;
import com.example.seminar8springAOP.models.Command;
import com.example.seminar8springAOP.models.Performer;
import com.example.seminar8springAOP.services.CommandServiceImpl;
import com.example.seminar8springAOP.services.PerformerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacadeImpl implements Facade{

    @Autowired
    CommandServiceImpl commandService;
    @Autowired
    PerformerServiceImpl performerService;

    public Command createCommand(Command note) {return commandService.createCommand(note);}

    public List<String> getAllCommands() {return commandService.getAllCommands();}

    public Command getCommandById(Long id) {return commandService.getCommandById(id);}

    public List<String> getCommandByStatus() {return commandService.getCommandByStatus();}

    public Performer createPerformer(Performer note) {return performerService.createPerformer(note);}

    public List<String> getAllPerformers() {return performerService.getAllPerformers();}

    public List<String> getPerformerByStatus() {return performerService.getPerformerByStatus();}

    public Performer getPerformerById(Long id) {return performerService.getPerformerById(id);}

    public void deleteCommand(Long id) {

        ArrayList<Long> listPerformersId = new ArrayList<>();
        listPerformersId = commandService.deleteCommand(id);
        if (!listPerformersId.isEmpty()){performerService.removeCommandIdFromPerformerList(listPerformersId, id);}

    }

    public void deletePerformer(Long id) {

        ArrayList<Long> listCommandsId = new ArrayList<>();
        listCommandsId = performerService.deletePerformer(id);
        if (!listCommandsId.isEmpty()){commandService.removePerformerIdFromCommandList(listCommandsId, id);}

    }

    @LoggedExecution
    public Command changeCommandStatus(Long id) {

        ArrayList<Long> listPerformersId = new ArrayList<>();
        listPerformersId = commandService.changeCommandStatus(id);
        if (!listPerformersId.isEmpty()){performerService.removeCommandIdFromPerformerList(listPerformersId,id);}
        Command command = commandService.getCommandById(id);

        return command;

    }

    @LoggedExecution
    public String changeCommandPerformer(Long id, Long id1){

        String part1 ="added to";
        boolean removePerformer = commandService.changeCommandPerformer(id,id1);
        if (removePerformer) {part1 ="removed from";}
        performerService.changeCommandPerformer(id, id1, removePerformer);

        String message = "Performer (id:"+id1+" name:"+performerService.getPerformerById(id1).getName()+") "
                +part1+" command(id:"+id+" description:"+commandService.getCommandById(id).getDescription()+")";

        return message;

    }

    public List<String> completeCommands() { return commandService.complete(); }

    public List<String> completePerformers() { return performerService.complete(); }


}
