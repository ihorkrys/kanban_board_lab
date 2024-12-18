package edu.duan.app.kanban.mapper;


import edu.duan.app.kanban.api.board.column.AddBoardColumnRequest;
import edu.duan.app.kanban.api.board.column.BoardColumnDTO;
import edu.duan.app.kanban.api.board.column.UpdateBoardColumnRequest;
import edu.duan.app.kanban.model.BoardColumnEntity;
import org.springframework.stereotype.Component;

@Component
public class BoardColumnMapper {
    public BoardColumnDTO toDTO(AddBoardColumnRequest request) {
        return BoardColumnDTO.builder()
                .withName(request.getName())
                .withSlug(request.getSlug())
                .withHexColor(request.getHexColor())
                .withActive(request.isActive())
                .withPosition(request.getPosition())
                .build();
    }

    public BoardColumnDTO toDTO(UpdateBoardColumnRequest request) {
        return BoardColumnDTO.builder()
                .withId(request.getColumnId())
                .withName(request.getName())
                .withSlug(request.getSlug())
                .withHexColor(request.getHexColor())
                .withActive(request.isActive())
                .withPosition(request.getPosition())
                .build();
    }

    public BoardColumnEntity toEntity(BoardColumnDTO dto) {
        return BoardColumnEntity.builder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withSlug(dto.getSlug())
                .withHexColor(dto.getHexColor())
                .withActive(dto.isActive())
                .withPosition(dto.getPosition())
                .build();
    }

    public BoardColumnDTO toDTO(BoardColumnEntity entity) {
        return BoardColumnDTO.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSlug(entity.getSlug())
                .withHexColor(entity.getHexColor())
                .withActive(entity.isActive())
                .withPosition(entity.getPosition())
                .build();
    }
}
