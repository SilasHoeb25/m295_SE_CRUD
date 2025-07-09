// src/components/DeleteButton.jsx
import React from 'react';

const DeleteButton = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      style={{
        backgroundColor: 'red',
        color: 'white',
        padding: '5px 10px',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer'
      }}
    >
      Delete
    </button>
  );
};

export default DeleteButton;
