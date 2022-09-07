package com.example.secondPract.repo;

import com.example.secondPract.models.ModelUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoUser extends CrudRepository<ModelUser, Long> {
    List<ModelUser> findByFname(String fname);
    List<ModelUser> findByFnameContains(String fname);
}
