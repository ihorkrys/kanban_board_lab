package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.board.column.*;
import edu.duan.app.kanban.mapper.BoardColumnMapper;
import edu.duan.app.kanban.service.BoardColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/columns")
public class BoardColumnController {
    private final BoardColumnService boardColumnService;
    private final BoardColumnMapper boardColumnMapper;

    @Autowired
    public BoardColumnController(BoardColumnService boardColumnService, BoardColumnMapper boardColumnMapper) {
        this.boardColumnService = boardColumnService;
        this.boardColumnMapper = boardColumnMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<AddBoardColumnResponse> addBoardColumn(@RequestBody AddBoardColumnRequest request) {
        BoardColumnDTO createdColumn = boardColumnService.createBoardColumn(boardColumnMapper.toDTO(request));
        return new ResponseEntity<>(new AddBoardColumnResponse(createdColumn), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateBoardColumnResponse> updateBoardColumn(@RequestBody UpdateBoardColumnRequest request) {
        BoardColumnDTO updateColumn = boardColumnService.updateBoardColumn(boardColumnMapper.toDTO(request));
        return new ResponseEntity<>(new UpdateBoardColumnResponse(updateColumn), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<RemoveBoardColumnResponse> removeBoardColumn(@PathVariable long id) {
        boardColumnService.deleteBoardColumn(id);
        return new ResponseEntity<>(new RemoveBoardColumnResponse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBoardColumnResponse> getBoardColumn(@PathVariable long id) {
        BoardColumnDTO column = boardColumnService.getBoardColumnById(id);
        return new ResponseEntity<>(new GetBoardColumnResponse(column), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListBoardColumnsResponse> listBoardColumns() {
        ListBoardColumnsResponse response = new ListBoardColumnsResponse(boardColumnService.getAllBoardColumns());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
