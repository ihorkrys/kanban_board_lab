import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const apiClient = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Запросы
export const loadAppContext = () => apiClient.get('/context/load');
export const setTaskColumn = (setTaskColumnRequest) => apiClient.post('/tasks/set-column', setTaskColumnRequest);
export const addTask = (task) => apiClient.post(`/tasks`, task);
export const getTask = (taskId) => apiClient.get(`/tasks/${taskId}`);
export const updateTask = (task) => apiClient.put(`/tasks`, task);
export const addComment = (commentRequest) => apiClient.post(`/comments`, commentRequest);
export const listBoardColumns = () => apiClient.get("/columns");
export const addBoardColumn = (column) => apiClient.post("/columns", column);
export const updateBoardColumn = (column) => apiClient.put("/columns", column);
export const deleteBoardColumn = (columnId) => apiClient.put(`/columns/${columnId}`);

export const errorHandling = (error) => console.error("Error on request exection: ", error)