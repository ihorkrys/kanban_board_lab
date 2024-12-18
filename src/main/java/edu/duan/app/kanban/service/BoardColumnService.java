package edu.duan.app.kanban.service;

import edu.duan.app.kanban.api.board.column.BoardColumnDTO;
import edu.duan.app.kanban.mapper.BoardColumnMapper;
import edu.duan.app.kanban.model.BoardColumnEntity;
import edu.duan.app.kanban.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;
    private final BoardColumnMapper boardColumnMapper;

    @Autowired
    public BoardColumnService(BoardColumnRepository boardColumnRepository, BoardColumnMapper boardColumnMapper) {
        this.boardColumnRepository = boardColumnRepository;
        this.boardColumnMapper = boardColumnMapper;
    }

    @Transactional
    public BoardColumnDTO createBoardColumn(BoardColumnDTO column) {
        return boardColumnMapper.toDTO(boardColumnRepository.save(boardColumnMapper.toEntity(column)));
    }

    public BoardColumnDTO getBoardColumnById(String id) {
        BoardColumnEntity boardColumnEntity = boardColumnRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board column not found"));
        return boardColumnMapper.toDTO(boardColumnEntity);
    }

    public List<BoardColumnDTO> getAllBoardColumns() {
        return boardColumnRepository.findAllSortedByPosition().stream()
                .map(boardColumnMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BoardColumnDTO> getActiveBoardColumns() {
        return boardColumnRepository.findByActiveIsTrueSortedByPosition().stream()
                .map(boardColumnMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardColumnDTO updateBoardColumn(BoardColumnDTO updatedColumn) {
        if (boardColumnRepository.existsById(updatedColumn.getId())) {
            return boardColumnMapper.toDTO(boardColumnRepository.save(boardColumnMapper.toEntity(updatedColumn)));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board column not found");
    }

    public void deleteBoardColumn(String id) {
        boardColumnRepository.deleteById(id);
    }
}