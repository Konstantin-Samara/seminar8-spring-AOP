package com.example.seminar8springAOP.datas;

import com.example.seminar8springAOP.models.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
