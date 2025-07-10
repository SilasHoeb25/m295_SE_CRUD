import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import SaveButton from '../components/SaveButton';
import AddButton from '../components/AddButton';
import RemoveButton from '../components/RemoveButton'; 

const RecipieEdit = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [recipie, setRecipie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/recipies/${id}`)
      .then(response => response.json())
      .then(data => setRecipie(data))
      .catch(err => console.error('Fehler beim Laden:', err));
  }, [id]);

  const handleInputChange = (e) => {
    setRecipie({ ...recipie, [e.target.name]: e.target.value });
  };

  const handleIngredientChange = (index, field, value) => {
    const updatedIngredients = [...recipie.ingredients];
    updatedIngredients[index][field] = value;
    setRecipie({ ...recipie, ingredients: updatedIngredients });
  };

  
  const handleAddIngredient = () => {
    setRecipie({
      ...recipie,
      ingredients: [...recipie.ingredients, { name: '', amount: '' }],
    });
  };

  const handleRemoveIngredient = (indexToRemove) => {
    const updatedIngredients = recipie.ingredients.filter((_, i) => i !== indexToRemove);
    setRecipie({ ...recipie, ingredients: updatedIngredients });
  };

  const handleSave = () => {
    fetch(`http://localhost:8080/recipies/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(recipie)
    })
      .then(res => {
        if (!res.ok) throw new Error('Fehler beim Speichern');
        navigate('/');
      })
      .catch(err => console.error(err));
  };

  if (!recipie) return <div>Loading...</div>;

  return (
    <div className="recipie-edit-container">
      <h2>Edit Recipie</h2>
      <label>
        Name:
        <input type="text" name="name" value={recipie.name} onChange={handleInputChange} />
      </label>
      <br />
      <label>
        Time to Prepare (min):
        <input type="number" name="timeToPrep" value={recipie.timeToPrep} onChange={handleInputChange} />
      </label>
      <br />
      <label>
        Instruction:
        <textarea name="instruction" value={recipie.instruction} onChange={handleInputChange} />
      </label>
      <h3>Ingredients</h3>
      {recipie.ingredients.map((ingredient, index) => (
        <div key={index}>
          <input
            type="text"
            value={ingredient.name}
            onChange={(e) => handleIngredientChange(index, 'name', e.target.value)}
            placeholder="Name"
          />
          <input
            type="text"
            value={ingredient.amount}
            onChange={(e) => handleIngredientChange(index, 'amount', e.target.value)}
            placeholder="Amount"
          />
          <RemoveButton onClick={() => handleRemoveIngredient(index)} />
        </div>
      ))}

        <AddButton onClick={handleAddIngredient} />

      <br />
      <SaveButton onClick={handleSave} />
    </div>
  );
};

export default RecipieEdit;