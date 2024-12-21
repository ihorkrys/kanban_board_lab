import ItemType from "../../constans";
import {useDrag} from "react-dnd";

function Task({ task, fromColumnSlug, onClick }) {
    const [, drag] = useDrag({
        type: ItemType.TASK,
        item: { id: task.id, fromColumnSlug },
    });

    return (
        <li
            ref={drag}
            className="list-group-item kanban-task d-flex flex-column"
            style={{ cursor: 'grab' }}
            onClick={() => onClick(task)}
        >
            <h6 className="task-title">{task.title}</h6>
            <p className="badge bg-secondary mt-2 align-self-start">ETA: {task.deadlineTime}</p>
            <span className="badge bg-secondary mt-2 align-self-start">
                Assignee: {task.assignee || 'Unassigned'}
            </span>
        </li>
    );
}

export default Task;