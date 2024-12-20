package edu.duan.app.kanban.mapper;

import edu.duan.app.kanban.api.comment.CommentDTO;
import edu.duan.app.kanban.model.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentEntity toEntity(CommentDTO dto) {
        return CommentEntity.builder()
                .withId(dto.getId())
                .withAuthor(dto.getAuthor())
                .withContent(dto.getContent())
                .withPublishDate(DateTimeMapper.toTimestamp(dto.getPublishDate()))
                .build();
    }

    public CommentDTO toDTO(CommentEntity entity) {
        if (entity == null) {
            return null;
        }
        return CommentDTO.builder()
                .withId(entity.getId())
                .withAuthor(entity.getAuthor())
                .withContent(entity.getContent())
                .withPublishDate(DateTimeMapper.toLocalDateTime(entity.getPublishDate()))
                .build();
    }
}
