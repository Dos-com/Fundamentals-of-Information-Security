package com.example.fundamentals_of_information_security_project;

import java.util.UUID;

public class MyCipher {
    private UUID Id;
    private String Name;

    public MyCipher(String name) {
        this.Id = UUID.randomUUID();
        Name = name;
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
