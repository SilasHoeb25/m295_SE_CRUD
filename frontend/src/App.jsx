import { Routes, Route } from 'react-router-dom';
import Cookbook from './pages/Cookbook';
import RecipieDetails from './pages/RecipieDetails';
import RecipieEdit from './pages/RecipieEdit';
import RecipieNew from './pages/RecipieNew';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Cookbook />} />
      <Route path="/recipie/:id" element={<RecipieDetails />} />
      <Route path="/recipie/:id/edit" element={<RecipieEdit />} />
      <Route path="/recipie/new" element={<RecipieNew />} />
    </Routes>
  );
}

export default App;
