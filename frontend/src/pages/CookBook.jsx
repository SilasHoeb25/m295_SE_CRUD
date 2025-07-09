import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Cookbook.css';
import EditButton from '../components/EditButton';
import DeleteButton from '../components/DeleteButton';

const Cookbook = () => {
  const [recipies, setRecipies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/recipies')
      .then(response => response.json())
      .then(data => setRecipies(data))
      .catch(error => console.error('Fehler beim Laden:', error));
  }, []);

  return (
    <div className="cookbook-container">
      <h1>Cookbook</h1>
      <ul className="recipie-list">
        {recipies.map((recipie) => (
          <li key={recipie.id} className="recipie-item" onClick={() => navigate(`/recipie/${recipie.id}`)}>
            <div>
              <strong>{recipie.name}</strong> – {recipie.timeToPrep} Minuten
            </div>
            <div className="buttons">
              <EditButton onClick={(e) => { e.stopPropagation(); /* Funktion später */ }} />
              <DeleteButton onClick={(e) => { e.stopPropagation(); /* Funktion später */ }} />
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Cookbook;
