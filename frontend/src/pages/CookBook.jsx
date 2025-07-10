import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Cookbook.css';
import EditButton from '../components/EditButton';
import DeleteButton from '../components/DeleteButton';
import DeleteModal from '../components/DeleteModal';
import NewRecipieButton from '../components/NewRecipieButton';

const Cookbook = () => {
  const [recipies, setRecipies] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [recipieToDelete, setRecipieToDelete] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/recipies')
      .then(response => response.json())
      .then(data => setRecipies(data))
      .catch(error => console.error('Fehler beim Laden:', error));
  }, []);

  const handleDeleteClicked = (e, recipie) => {
    e.stopPropagation();
    setRecipieToDelete(recipie);
    setShowModal(true);
  };

  const handleDeleteConfirmed = () => {
    fetch(`http://localhost:8080/recipies/${recipieToDelete.id}`, {
      method: 'DELETE',
    })
      .then((res) => {
        if (!res.ok) throw new Error('Fehler beim Löschen');
        // Aktualisiere die Liste
        setRecipies(recipies.filter(r => r.id !== recipieToDelete.id));
        setShowModal(false);
        setRecipieToDelete(null);
      })
      .catch((err) => console.error('Löschfehler:', err));
  };

  const handleCancelDelete = () => {
    setShowModal(false);
    setRecipieToDelete(null);
  };


  return (
    <div className="cookbook-container">
      <NewRecipieButton />
      <h1>Cookbook</h1>
      <ul className="recipie-list">
        {recipies.map((recipie) => (
          <li key={recipie.id} className="recipie-item" onClick={() => navigate(`/recipie/${recipie.id}`)}>
            <div>
              <strong>{recipie.name}</strong> – {recipie.timeToPrep} Minuten
            </div>
            <div className="buttons">
              <EditButton onClick={(e) => { e.stopPropagation();navigate(`/recipie/${recipie.id}/edit`) }} />
              <DeleteButton onClick={(e) => handleDeleteClicked(e, recipie)} />
            </div>
          </li>
        ))}
      </ul>
      <DeleteModal
  recipie={recipieToDelete}
  onConfirm={handleDeleteConfirmed}
  onCancel={handleCancelDelete}
/>
    </div>
  );
};

export default Cookbook;
