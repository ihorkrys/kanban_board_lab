import React, { useState, useEffect } from 'react';
import {
    listBoardColumns,
    addBoardColumn,
    updateBoardColumn,
    deleteBoardColumn,
    errorHandling
} from '../../services/api';
import {DndProvider} from "react-dnd";
import {HTML5Backend} from "react-dnd-html5-backend";
import DraggableRow from "./DraggableRow";

function ColumnManager() {
    const [columns, setColumns] = useState([]);
    const [newColumn, setNewColumn] = useState({ name: '', slug: '', hexColor: '', position: 1, active: true });

    useEffect(() => {
        loadColumns();
    }, []);

    const loadColumns = () => {
        listBoardColumns()
            .then((response) => {
                const sortedColumns = response.data.columns.sort((a, b) => a.position - b.position);
                setColumns(sortedColumns);
            })
            .catch(errorHandling);
    };

    const handleAddColumn = () => {
        addBoardColumn(newColumn)
            .then(() => {
                loadColumns();
                setNewColumn({ name: '', slug: '', hexColor: '', position: columns.length + 1, active: true });
            })
            .catch(errorHandling);
    };

    const handleEditColumn = (id, field, value) => {
        const updatedColumns = columns.map((col) =>
            col.id === id ? { ...col, columnId: id, [field]: value } : col
        );
        setColumns(updatedColumns);
    };

    const handleSaveColumn = (id) => {
        const column = columns.find((col) => col.id === id);
        updateBoardColumn(column)
            .then(() => loadColumns())
            .catch(errorHandling);
    };

    const handleDeleteColumn = (id) => {
        deleteBoardColumn(id)
            .then(() => loadColumns())
            .catch(errorHandling);
    };

    const moveColumn = (dragIndex, hoverIndex) => {
        const updatedColumns = [...columns];
        const [draggedColumn] = updatedColumns.splice(dragIndex, 1);
        updatedColumns.splice(hoverIndex, 0, draggedColumn);

        updatedColumns.forEach((col, index) => {
            col.position = index + 1;
        });

        setColumns(updatedColumns);

        updatedColumns.forEach((col) =>
            updateBoardColumn(col).catch(errorHandling)
        );
    };

    return (
        <DndProvider backend={HTML5Backend}>
            <div className="container py-4">
                <h1>Column Manager</h1>
                <table className="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Slug</th>
                        <th>Color</th>
                        <th>Is active?</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {columns.map((column, index) => (
                        <DraggableRow
                            key={column.id}
                            column={column}
                            index={index}
                            moveColumn={moveColumn}
                            onEdit={handleEditColumn}
                            onSave={handleSaveColumn}
                            onDelete={handleDeleteColumn}
                        />
                    ))}
                    <tr>
                        <td>New</td>
                        <td>
                            <input
                                type="text"
                                className="form-control"
                                value={newColumn.name}
                                onChange={(e) => setNewColumn({...newColumn, name: e.target.value})}
                            />
                        </td>
                        <td>
                            <input
                                type="text"
                                className="form-control"
                                value={newColumn.slug}
                                onChange={(e) => setNewColumn({...newColumn, slug: e.target.value})}
                            />
                        </td>
                        <td>
                            <input
                                type="color"
                                className="form-control"
                                value={newColumn.hexColor}
                                onChange={(e) => setNewColumn({...newColumn, hexColor: e.target.value})}
                            />
                        </td>
                        <td className="text-center">
                            <input
                                type="checkbox"
                                checked={newColumn.active}
                                onChange={(e) => setNewColumn({...newColumn, active: e.target.checked})}
                            />
                        </td>
                        <td>
                            <button className="btn btn-primary" onClick={handleAddColumn}>
                                Add
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </DndProvider>
    );
}

export default ColumnManager;