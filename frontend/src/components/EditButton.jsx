import React from 'react';

const EditButton = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      style={{
        backgroundColor: 'green',
        color: 'white',
        padding: '5px 10px',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer'
      }}
    >
      Edit
    </button>
  );
};

export default EditButton;
