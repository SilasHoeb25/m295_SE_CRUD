import React from 'react';
import { useNavigate } from 'react-router-dom';

const NewRecipieButton = () => {
  const navigate = useNavigate();

  return (
    <button
      onClick={() => navigate('/recipie/new')}
      style={{
        position: 'absolute',
        top: '20px',
        right: '30px',
        padding: '10px 15px',
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        borderRadius: '5px',
        fontWeight: 'bold',
        cursor: 'pointer'
      }}
    >
      + New Recipie
    </button>
  );
};

export default NewRecipieButton;