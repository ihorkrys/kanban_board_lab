package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.board.column.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/columns")
public class BoardColumnController {
    @PostMapping("/add")
    public ResponseEntity<AddBoardColumnResponse> addBoardColumn(@RequestBody AddBoardColumnRequest request) {
        AddBoardColumnResponse response = new AddBoardColumnResponse(new BoardColumnDTO());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateBoardColumnResponse> updateBoardColumn(@RequestBody UpdateBoardColumnRequest request) {
        UpdateBoardColumnResponse response = new UpdateBoardColumnResponse(new BoardColumnDTO());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<RemoveBoardColumnResponse> removeBoardColumn(@PathVariable String id) {
        RemoveBoardColumnResponse response = new RemoveBoardColumnResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBoardColumnResponse> getBoardColumn(@PathVariable String id) {
        GetBoardColumnResponse response = new GetBoardColumnResponse(new BoardColumnDTO());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListBoardColumnsResponse> listBoardColumns() {
        List<BoardColumnDTO> columns = List.of(
                new BoardColumnDTO(UUID.randomUUID().toString(), "To Do", "todo", "#FF5733", true, 1),
                new BoardColumnDTO(UUID.randomUUID().toString(), "In Progress", "inprogress", "#33C4FF", true, 2),
                new BoardColumnDTO(UUID.randomUUID().toString(), "Done", "done", "#33FF57", false, 3)
        );
        ListBoardColumnsResponse response = new ListBoardColumnsResponse(columns);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
