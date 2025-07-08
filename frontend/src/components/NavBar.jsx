import { Link } from 'react-router-dom'
import '../CSS/NavBar.css'

const NavBar = () => {
  return (
    <nav className="navbar">
      <Link to="/" className="nav-item">Cook Book</Link>
      <Link to="/add" className="nav-item">New Recipie</Link>
    </nav>
  )
}

export default NavBar
