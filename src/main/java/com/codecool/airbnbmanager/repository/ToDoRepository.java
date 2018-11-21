package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByLodgingsId(Long lodgingsId);

}
