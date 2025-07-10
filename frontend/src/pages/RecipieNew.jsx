// pages/RecipieNew.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import SaveButton from '../components/SaveButton';
import RemoveButton from '../components/RemoveButton';
import AddButton from '../components/AddButton';

const RecipieNew = () => {
  const navigate = useNavigate();

  const [recipie, setRecipie] = useState({
    name: '',
    instruction: '',
    timeToPrep: '',
    ingredients: []
  });

  const handleInputChange = (e) => {
    setRecipie({ ...recipie, [e.target.name]: e.target.value });
  };

  const handleIngredientChange = (index, field, value) => {
    const updatedIngredients = [...recipie.ingredients];
    updatedIngredients[index][field] = value;
    setRecipie({ ...recipie, ingredients: updatedIngredients });
  };

  const addIngredient = () => {
    setRecipie({
      ...recipie,
      ingredients: [...recipie.ingredients, { name: '', amount: '' }]
    });
  };

  const removeIngredient = (index) => {
    const updatedIngredients = recipie.ingredients.filter((_, i) => i !== index);
    setRecipie({ ...recipie, ingredients: updatedIngredients });
  };

  const handleSave = () => {
    const formattedRecipie = {
      ...recipie,
      timeToPrep: Number(recipie.timeToPrep)
    };

    fetch('http://localhost:8080/recipies', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formattedRecipie)
    })
      .then((res) => {
        if (!res.ok) throw new Error('Fehler beim Erstellen');
        navigate('/');
      })
      .catch((err) => console.error(err));
  };

  return (
    <div className="recipie-edit-container">
      <h2>Create New Recipie</h2>
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
        <RemoveButton onClick={() => removeIngredient(index)} />
        </div>
      ))}
    <AddButton onClick={addIngredient} />
        <br />
    <br />
      <SaveButton onClick={handleSave} />
    </div>
  );
};

export default RecipieNew;
