// components/SaveButton.jsx
import React from 'react';

const SaveButton = ({ onClick }) => (
  <button style={{ backgroundColor: 'blue', color: 'white', padding: '6px 12px' }} onClick={onClick}>
    Save Recipie
  </button>
);

export default SaveButton;