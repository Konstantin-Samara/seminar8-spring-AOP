package com.example.seminar8springAOP.facade;

import com.example.seminar8springAOP.models.Command;
import com.example.seminar8springAOP.models.Performer;
import java.util.List;

public interface Facade {

    public Command createCommand(Command note) ;

    public List<String> getAllCommands() ;

    public Command getCommandById(Long id) ;

    public Command changeCommandStatus(Long id) ;

    public void deleteCommand(Long id) ;

    public List<String> getCommandByStatus() ;

    public Performer createPerformer(Performer note) ;

    public List<String> getAllPerformers() ;

    public Performer getPerformerById(Long id) ;

    public void deletePerformer(Long id) ;

    public List<String> getPerformerByStatus() ;

    public String changeCommandPerformer(Long id, Long id1);

    List<String> completeCommands();

    List<String> completePerformers();
}
