import React from 'react';

const AddButton = ({ onClick }) => {
  return (
    <button onClick={onClick} style={{ backgroundColor: '#4CAF50', color: 'white', marginTop: '10px' }}>
      + Add Ingredient
    </button>
  );
};

export default AddButton;