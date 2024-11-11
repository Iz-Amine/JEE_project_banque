package com.example.demo.service;

import com.example.demo.entity.Groupe;

import java.util.List;

public interface GroupeService {
    Groupe addGroupe(Groupe groupe);
    List<Groupe> listGroupes();
Groupe saveGroupe(Groupe groupe);
}
