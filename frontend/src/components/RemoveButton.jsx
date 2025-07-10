import React from 'react';

const RemoveButton = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      style={{
        backgroundColor: '#ff4d4d',
        color: 'white',
        border: 'none',
        marginLeft: '8px',
        padding: '0 8px',
        cursor: 'pointer',
        fontSize: '1rem',
        borderRadius: '4px',
      }}
      title="Remove Ingredient"
    >
      −
    </button>
  );
};

export default RemoveButton;