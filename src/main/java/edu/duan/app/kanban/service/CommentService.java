package edu.duan.app.kanban.service;

import edu.duan.app.kanban.api.comment.CommentDTO;
import edu.duan.app.kanban.mapper.CommentMapper;
import edu.duan.app.kanban.model.CommentEntity;
import edu.duan.app.kanban.model.TaskEntity;
import edu.duan.app.kanban.repository.CommentRepository;
import edu.duan.app.kanban.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(TaskRepository taskRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional
    public CommentDTO createComment(String taskId, String author, String content) {
        if (taskRepository.existsById(taskId)) {

            CommentEntity savedComment = commentRepository.save(CommentEntity.builder()
                    .withAuthor(author)
                    .withContent(content)
                    .withPublishDate(new Timestamp(System.currentTimeMillis()))
                    .build());
            TaskEntity taskEntity = taskRepository.getReferenceById(taskId);
            List<CommentEntity> commentaries = taskEntity.getCommentaries();
            commentaries.add(savedComment);
            taskEntity.setCommentaries(commentaries);
            return commentMapper.toDTO(savedComment);
        } else throw new ResponseStatusException(NOT_FOUND, "Task not found");
    }
}