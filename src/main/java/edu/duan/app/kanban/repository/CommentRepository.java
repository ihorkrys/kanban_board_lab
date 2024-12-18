package edu.duan.app.kanban.repository;

import edu.duan.app.kanban.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
}