package com.example.seminar8springAOP.services;

import com.example.seminar8springAOP.models.Performer;

import java.util.ArrayList;
import java.util.List;

public interface PerformerService {

    public Performer createPerformer(Performer note);

    public Performer getPerformerById(Long id);

    public List<String> getAllPerformers();

    public List<String> getPerformerByStatus();

    public ArrayList<Long> deletePerformer(Long id);

    public void removeCommandIdFromPerformerList(ArrayList<Long> listPerformersId, long commandId);

    public void changeCommandPerformer(Long id, Long id1, boolean removePerformer);

    List<String> complete();

}
