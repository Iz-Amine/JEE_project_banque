package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Groupe;

import java.util.List;

public interface GroupeMetier {
    Groupe saveGroupe(Groupe groupe);
    Groupe getGroupeById(Long id);
    List<Groupe> getAllGroupes();
    Groupe updateGroupe(Long id, Groupe groupe);
    void deleteGroupe(Long id);
}
