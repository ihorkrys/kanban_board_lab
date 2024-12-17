import logo from './logo.svg';
import React, { useEffect, useState } from 'react';
import './App.css';

function App() {
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(data => setMessage(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return (
        <div>
            <h1>React + Spring Boot + KanBan</h1>
            <p>{message}</p>
        </div>
    );
}

export default App;
