package edu.duan.app.kanban.api.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskResponse {
    private TaskDTO task;
}
