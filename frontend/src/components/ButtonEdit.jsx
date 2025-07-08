import React from "react";
import { useNavigate } from "react-router-dom";
import "../CSS/ButtonEdit.css";

function ButtonEdit({ recipieId }) {
  const navigate = useNavigate();

  const handleEdit = () => {
    navigate(`/update-recipie/${recipieId}`);
  };

  return (
    <button className="buttonEdit" onClick={handleEdit}>
      Edit
    </button>
  );
}

export default ButtonEdit;
  