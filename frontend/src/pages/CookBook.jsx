import React, { useEffect, useState } from "react";
import { getAllRecipies } from "../api/recipieApi";

function CookBook() {
  const [recipies, setRecipies] = useState([]);

  useEffect(() => {
    getAllRecipies().then(setRecipies).catch(console.error);
  }, []);

  return (
    <div>
      <h2>All Recipes</h2>
      <ul>
        {recipies.map((r) => (
          <li key={r.id}>{r.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default CookBook;