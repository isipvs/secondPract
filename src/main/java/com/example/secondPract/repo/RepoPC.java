package com.example.secondPract.repo;

import com.example.secondPract.models.ModelPC;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoPC extends CrudRepository<ModelPC, Long> {

    List<ModelPC> findByName(String name);
    List<ModelPC> findByNameContains(String name);
}
