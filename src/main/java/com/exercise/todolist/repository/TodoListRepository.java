package com.exercise.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA Repository interface for managing TodoList entities
 * This interface extends JpaRepository which provides basic CRUD operations
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, UUID> {

}
