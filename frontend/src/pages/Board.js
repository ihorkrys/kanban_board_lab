import React, { useEffect, useState } from 'react';
import {addTask, getTask, errorHandling, loadAppContext, setTaskColumn, updateTask} from '../services/api';
import {DndProvider} from "react-dnd";
import {HTML5Backend} from "react-dnd-html5-backend";
import Column from "./task/Column";
import TaskViewModal from "./task/TaskViewModal";
import CreateTaskModal from "./task/CreateTaskModal";

function Board() {
    const [columns, setColumns] = useState([]);
    const [tasksPerColumn, setTasksPerColumn] = useState({});
    const [isLoading, setIsLoading] = useState(true);
    const [selectedTask, setSelectedTask] = useState(null);
    const [showCreateTaskModal, setShowCreateTaskModal] = useState(false);
    const [showTaskModal, setShowTaskModal] = useState(false);

    useEffect(() => {
        loadAppContext()
            .then((response) => {
                setColumns(response.data.boardColumnsSortedByPriority);
                setTasksPerColumn(response.data.tasksGroupedByColumnSlug);
            })
            .catch(errorHandling)
            .finally(() => setIsLoading(false));
    }, []);

    const moveTask = (taskId, fromColumnSlug, toColumnSlug) => {
        if (fromColumnSlug == toColumnSlug) {
            return;
        }
        const taskToMove = tasksPerColumn[fromColumnSlug].find((task) => task.id === taskId);

        const updatedFromColumnTasks = tasksPerColumn[fromColumnSlug].filter((task) => task.id !== taskId);

        const updatedToColumnTasks = [...(tasksPerColumn[toColumnSlug] || []), taskToMove];

        setTasksPerColumn({
            ...tasksPerColumn,
            [fromColumnSlug]: updatedFromColumnTasks,
            [toColumnSlug]: updatedToColumnTasks,
        });

        setTaskColumn({ id: taskId, columnSlug: toColumnSlug }).catch(errorHandling);
    };

    if (isLoading) {
        return <div className="text-center py-5">Loading...</div>;
    }

    const handleTaskClick = (task) => {
        //console.log(task);
        getTask(task.id).then((response) => {
            setSelectedTask(response.data.task);
            setShowTaskModal(true);
        }).catch(errorHandling);
    };

    const handleModalClose = () => {
        setSelectedTask(null);
        setShowTaskModal(false);
    };

    const handleCreateModalClose = () => {
        setShowCreateTaskModal(false);
    };

    const handleSaveTask = (updatedTask) => {
        const columnSlug = updatedTask.activeColumn.slug;
        console.log(updatedTask)
        setTasksPerColumn((prevState) => ({
            ...prevState,
            [columnSlug]: prevState[columnSlug].map((task) =>
                task.id === updatedTask.id ? updatedTask : task
            ),
        }));

        updateTask(updatedTask).catch(errorHandling);
    };

    return (
        <DndProvider backend={HTML5Backend}>
            <div className="container py-4">
                <div className="d-flex justify-content-between align-items-center mb-3">
                    <h1>Task Board</h1>
                    <button className="btn btn-primary" onClick={() => setShowCreateTaskModal(true)}>
                        Create Task
                    </button>
                </div>
                <CreateTaskModal
                    show={showCreateTaskModal}
                    onClose={handleCreateModalClose}
                    task={selectedTask}
                    onSave={handleSaveTask}
                />
                <TaskViewModal
                    show={showTaskModal}
                    onClose={handleModalClose}
                    task={selectedTask}
                    onSave={handleSaveTask}
                    onUpdate={handleTaskClick}
                />
                <div className="kanban-board">
                    {columns.map((column) => (
                        <Column
                            key={column.slug}
                            column={column}
                            tasks={tasksPerColumn[column.slug] || []}
                            moveTask={moveTask}
                            onClick={handleTaskClick}
                        />
                    ))}
                </div>
            </div>
        </DndProvider>
    );
}

export default Board;