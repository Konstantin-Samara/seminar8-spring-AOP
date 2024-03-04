package com.example.seminar8springAOP.services;

import com.example.seminar8springAOP.factorys.PerformerFactoryImpl;
import com.example.seminar8springAOP.datas.PerformerRepository;
import com.example.seminar8springAOP.models.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class PerformerServiceImpl implements PerformerService{
    @Autowired
    private PerformerRepository performerRepository;

    public Performer createPerformer(Performer note) { return performerRepository.save(note); }

    public Performer getPerformerById(Long id) {

        Performer performer = performerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performer with ID:"+id+" not found"));
        return performer;
    }

    public List<String> getAllPerformers() {

        return performerRepository.findAll().stream()
                .sorted(Comparator.comparing(Performer::getId))
                .map(Performer::toString).toList();
    }

    public List<String> getPerformerByStatus() {

        return performerRepository.findAll().stream()
                .filter(it->it.getStatus().equals(Performer.Status.Working))
                .sorted(Comparator.comparing(Performer::getId))
                .map(Performer::toString).toList();
    }
    public ArrayList<Long> deletePerformer(Long id) {

        ArrayList<Long> listCommandsId = new ArrayList<>();
        listCommandsId = getPerformerById(id).getCommandsId();
        performerRepository.deleteById(id);
        return listCommandsId;

    }

    public void removeCommandIdFromPerformerList(ArrayList<Long> listPerformersId, long commandId){

        listPerformersId.forEach(it->{
            Performer tempPerformer = getPerformerById(it);
            tempPerformer.getCommandsId().remove(commandId);
            if (tempPerformer.getCommandsId().isEmpty()){
                tempPerformer.setStatus(Performer.Status.Waiting);}
            performerRepository.save(tempPerformer);});
    }
    public void changeCommandPerformer(Long id, Long id1, boolean removePerformer){

        Performer existingPerformer = getPerformerById(id1);
        if (!removePerformer){
            existingPerformer.getCommandsId().add(id);
            existingPerformer.setStatus(Performer.Status.Working);
            }
        else {
            existingPerformer.getCommandsId().remove(id);
            if (existingPerformer.getCommandsId().isEmpty()){
                existingPerformer.setStatus(Performer.Status.Waiting);}
        }
        performerRepository.save(existingPerformer);

    }

        public List<String> complete() {

            PerformerFactoryImpl performerFactory = new PerformerFactoryImpl();
            ArrayList<Performer> performers = new ArrayList<>();
            performerRepository.deleteAll();
            String[] names = {"Ivan Ivanov","Petr Petrov","Nikolay Nikolaev","Sergey Sergeev","Dmitriy Dmitriev"
                    ,"Victor Victorov","Sidor Sidorov","SOMETHING PERFORMER"};

            Arrays.stream(names).forEach(it->performers.add(performerFactory.createPerformer(it)));
            performerRepository.saveAll(performers);

            return performers.stream().map(Performer::toString).toList();

        }

}
