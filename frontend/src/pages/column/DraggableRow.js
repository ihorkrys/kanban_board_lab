import React from 'react';
import { useDrag, useDrop } from 'react-dnd';
import ItemType from "../../constans";

function DraggableRow({ column, index, moveColumn, onEdit, onSave, onDelete }) {
    const ref = React.useRef(null);

    const [, drop] = useDrop({
        accept: ItemType.COLUMN,
        hover: (item) => {
            if (item.index !== index) {
                moveColumn(item.index, index);
                item.index = index;
            }
        },
    });

    const [{ isDragging }, drag] = useDrag({
        type: ItemType.COLUMN,
        item: { id: column.id, index },
        collect: (monitor) => ({
            isDragging: monitor.isDragging(),
        }),
    });

    drag(drop(ref));

    return (
        <tr ref={ref} style={{ cursor: 'grab', opacity: isDragging ? 0.5 : 1 }}>
            <td>
                <span title="Drag to reorder">
                    &#x2630;
                </span>
            </td>
            <td>
                <input
                    type="text"
                    className="form-control"
                    value={column.name}
                    onChange={(e) => onEdit(column.id, 'name', e.target.value)}
                />
            </td>
            <td>
                <input
                    type="text"
                    className="form-control"
                    value={column.slug}
                    onChange={(e) => onEdit(column.id, 'slug', e.target.value)}
                />
            </td>
            <td>
                <input
                    type="color"
                    className="form-control"
                    value={column.hexColor}
                    onChange={(e) => onEdit(column.id, 'hexColor', e.target.value)}
                />
            </td>
            <td className="text-center">
                <input
                    type="checkbox"
                    checked={column.active}
                    onChange={(e) => onEdit(column.id, 'active', e.target.checked)}
                />
            </td>
            <td>
                <button className="btn btn-success me-2" onClick={() => onSave(column.id)}>
                    Save
                </button>
                <button className="btn btn-danger" onClick={() => onDelete(column.id)}>
                    Delete
                </button>
            </td>
        </tr>
    );
}

export default DraggableRow;