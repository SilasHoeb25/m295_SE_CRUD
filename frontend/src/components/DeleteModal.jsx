import React from 'react';
import '../CSS/DeleteModal.css'; 

const DeleteModal = ({ recipie, onConfirm, onCancel }) => {
  if (!recipie) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <p>Do you really want to delete <strong>{recipie.name}</strong> from your Cookbook?</p>
        <div className="modal-buttons">
          <button className="yes" onClick={onConfirm}>Yes</button>
          <button className="no" onClick={onCancel}>No</button>
        </div>
      </div>
    </div>
  );
};

export default DeleteModal;
