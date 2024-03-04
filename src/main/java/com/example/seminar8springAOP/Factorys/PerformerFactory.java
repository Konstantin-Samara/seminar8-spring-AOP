package com.example.seminar8springAOP.Factorys;

import com.example.seminar8springAOP.models.Performer;

public interface PerformerFactory {
    Performer createPerformer(String name);
}
