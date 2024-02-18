package com.example.seminar8springAOP.datas;

import com.example.seminar8springAOP.models.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
