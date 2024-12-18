package edu.duan.app.kanban.repository;

import edu.duan.app.kanban.model.BoardColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardColumnRepository extends JpaRepository<BoardColumnEntity, String> {
    List<BoardColumnEntity> findAllSortedByPosition();
    List<BoardColumnEntity> findByActiveIsTrueSortedByPosition();
    Optional<BoardColumnEntity> findBySlug(String slug);
}