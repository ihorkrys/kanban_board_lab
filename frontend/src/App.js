import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Board from './pages/Board';
import Header from './pages/Header';
import Footer from './pages/Footer';
import ColumnManager from "./pages/column/ColumnManager";

function App() {
    return (
        <Router>
            <div className="d-flex flex-column min-vh-100">
                <Header/>
                <main className="flex-grow-1">
                    <Routes>
                        <Route path="/" element={<Board/>}/>
                        <Route path="/column/manager" element={<ColumnManager/>}/>
                    </Routes>
                </main>
                <Footer />
            </div>
        </Router>
);
}

export default App;
