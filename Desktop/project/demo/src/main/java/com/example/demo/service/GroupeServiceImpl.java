package com.example.demo.service;


import com.example.demo.dao.GroupeRepository;
import com.example.demo.entity.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public Groupe addGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public List<Groupe> listGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe saveGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }
}
