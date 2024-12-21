import Footer from "./Footer";

function Header() {
    return (
        <header className="navbar navbar-expand-lg navbar-dark bg-primary">
            <div className="container">
                <a href="/" className="navbar-brand">KanBan App</a>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <a href="/" className="nav-link">Board</a>
                        </li>
                        <li className="nav-item">
                            <a href="/column/manager" className="nav-link">Column Manager</a>
                        </li>
                        <li className="nav-item">
                            <a href="#about" className="nav-link">About</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
    );
}

export default Header