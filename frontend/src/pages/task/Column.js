import Task from "./Task";
import ItemType from "../../constans";
import {useDrop} from "react-dnd";

function Column({ column, tasks, moveTask, onClick }) {
    const [, drop] = useDrop({
        accept: ItemType.TASK,
        drop: (item) => moveTask(item.id, item.fromColumnSlug, column.slug),
    });

    return (
        <div ref={drop} className="col-lg-3 col-md-4 col-sm-6">
            <div className="card kanban-column border-0">
                <div
                    className="card-header text-white"
                    style={{ backgroundColor: column.hexColor }}
                >
                    <h5 className="mb-0">{column.name}</h5>
                </div>
                <div className="card-body p-2">
                    <ul className="list-group kanban-tasks">
                        {tasks.map((task) => (
                            <Task key={task.id} task={task} fromColumnSlug={column.slug} onClick={onClick}/>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Column;