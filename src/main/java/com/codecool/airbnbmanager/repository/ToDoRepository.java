package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {



}
