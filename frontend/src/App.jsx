import { Routes, Route } from 'react-router-dom';
import Cookbook from './pages/Cookbook';
import RecipieDetails from './pages/RecipieDetails';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Cookbook />} />
      <Route path="/recipie/:id" element={<RecipieDetails />} />
    </Routes>
  );
}

export default App;
