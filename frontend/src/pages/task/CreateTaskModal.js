import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import MDEditor from '@uiw/react-md-editor';

function CreateTaskModal({ show, onClose, onSubmit }) {
    const [taskData, setTaskData] = useState({
        title: '',
        description: '',
        assignee: '',
        deadlineTime: '',
    });

    const handleInputChange = (field, value) => {
        if (field === 'deadlineTime') {
            const formattedDate = new Date(value).toISOString().replace('T', ' ').substring(0, 19);
            setTaskData((prevState) => ({ ...prevState, [field]: formattedDate }));
        } else {
            setTaskData((prevState) => ({ ...prevState, [field]: value }));
        }
    };

    const handleSubmit = () => {
        onSubmit(taskData);
        setTaskData({ title: '', description: '', assignee: '', deadlineTime: '' });
        onClose();
    };

    return (
        <Modal show={show} onHide={onClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Create Task</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3">
                        <Form.Label>Title</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter task title"
                            value={taskData.title}
                            onChange={(e) => handleInputChange('title', e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Description</Form.Label>
                        <MDEditor
                            data-color-mode="light"
                            value={taskData.description}
                            onChange={(value) => handleInputChange('description', value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Assignee</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter assignee name"
                            value={taskData.assignee}
                            onChange={(e) => handleInputChange('assignee', e.target.value)}
                        />
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Deadline</Form.Label>
                        <Form.Control
                            type="datetime-local"
                            value={taskData.deadlineTime}
                            onChange={(e) => handleInputChange('deadlineTime', e.target.value)}
                        />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>
                    Cancel
                </Button>
                <Button variant="primary" onClick={handleSubmit}>
                    Create Task
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default CreateTaskModal;