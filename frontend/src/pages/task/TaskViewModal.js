import React, {useState} from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import MDEditor from "@uiw/react-md-editor";
import Comments from "./Comments";
import EditTaskModal from "./EditTaskModal";
import {addComment, errorHandling, getTask} from "../../services/api";

function TaskViewModal({ show, onClose, task, onSave, onUpdate }) {
    const [showEditModal, setShowEditModal] = useState(false);
    console.log(task)
    if (!task) return null;

    const handleEditSave = (updatedTask) => {
        onSave(updatedTask);
        setShowEditModal(false);
        onClose();
    };

    const handleAddComment = (author, content) => {
        addComment({taskId: task.id, author, content}).then(() => {
            onUpdate(task)
        }).catch(errorHandling);
    };
    return (
        <>
            <Modal show={show} onHide={onClose} centered>
                <Modal.Header closeButton>
                    <Modal.Title>{task.title}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className="mb-4">
                        <h6 className="text-muted">Description:</h6>
                        <div data-color-mode="light">
                            <MDEditor.Markdown source={task.description} />
                        </div>
                    </div>
                    <div className="mb-4">
                        <h6 className="text-muted">Details:</h6>
                        <ul className="list-group">
                            <li className="list-group-item">
                                <strong>Assignee:</strong>{' '}
                                <span className="badge bg-primary">{task.assignee || 'Unassigned'}</span>
                            </li>
                            <li className="list-group-item">
                                <strong>Created Time:</strong>{' '}
                                <span className="text-secondary">{task.createdTime}</span>
                            </li>
                            <li className="list-group-item">
                                <strong>Deadline:</strong>{' '}
                                <span className="text-danger">{task.deadlineTime}</span>
                            </li>
                            <li className="list-group-item">
                                <strong>Last Updated:</strong>{' '}
                                <span className="text-secondary">{task.lastUpdatedTime}</span>
                            </li>
                        </ul>
                    </div>
                    <Comments comments={task.comments || []} onAddComment={handleAddComment} />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={() => setShowEditModal(true)}>
                        Edit
                    </Button>
                    <Button variant="secondary" onClick={onClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
            <EditTaskModal
                show={showEditModal}
                onClose={() => setShowEditModal(false)}
                task={task}
                onSave={handleEditSave}
            />
        </>
    );
}

export default TaskViewModal;