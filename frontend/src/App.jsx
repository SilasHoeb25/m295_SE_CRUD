import { Route, Routes } from 'react-router-dom'
import AddRecipie from './pages/AddRecipie'
import CookBook from './pages/CookBook'
import Recipie from './pages/Recipie'
import UpdateRecipie from './pages/UpdateRecipie'

function App() {
  return (
    <Routes>
      <Route path="/" element={<CookBook />} />
      <Route path="/new" element={<AddRecipie />} />
      <Route path="/recipie/:id" element={<Recipie />} />
      <Route path="/update/:id" element={<UpdateRecipie />} />
      <Route path="/update-recipie/:id" element={<UpdateRecipie />} />
    </Routes>
  )
}

export default App