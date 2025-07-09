// src/Pages/RecipieDetails.jsx
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const RecipieDetails = () => {
  const { id } = useParams();
  const [recipie, setRecipie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/recipies/${id}`)
      .then(response => response.json())
      .then(data => setRecipie(data))
      .catch(error => console.error('Fehler beim Laden:', error));
  }, [id]);

  if (!recipie) return <p>Lädt...</p>;

  return (
    <div style={{ padding: '20px' }}>
      <h1>{recipie.name}</h1>
      <p><strong>Zubereitungszeit:</strong> {recipie.timeToPrep} Minuten</p>
      <p><strong>Anleitung:</strong> {recipie.instruction}</p>
      <h3>Zutaten:</h3>
      <ul>
        {recipie.ingredients.map((ing, idx) => (
          <li key={idx}>{ing.amount} – {ing.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default RecipieDetails;
