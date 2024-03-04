package com.example.seminar8springAOP.factorys;

import com.example.seminar8springAOP.models.Command;

public interface CommandFactory {
    Command createCommand(String description);
}
