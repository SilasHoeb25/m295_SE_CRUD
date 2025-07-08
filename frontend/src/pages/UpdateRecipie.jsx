import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import NavBar from '../components/NavBar'
import IngredientComponent from '../components/IngredientComponent'
import ButtonPlusIngredient from '../components/ButtonPlusIngredient'
import '../CSS/UpdateRecipie.css'

const UpdateRecipie = () => {
  const { id } = useParams()
  const [recipie, setRecipie] = useState(null)
  const [ingredients, setIngredients] = useState([])

  useEffect(() => {
    // TODO: Backend-Call ersetzen
    const mockData = {
      id,
      name: 'Spaghetti Bolognese',
      time: 30,
      instruction: 'Spaghetti kochen. Sauce zubereiten.',
      ingredients: [
        { name: 'Spaghetti', amount: '200g' },
        { name: 'Tomatensauce', amount: '150ml' },
      ],
    }
    setRecipie(mockData)
    setIngredients(mockData.ingredients)
  }, [id])

  const handleAddIngredient = () => {
    setIngredients([...ingredients, { name: '', amount: '' }])
  }

  const handleIngredientChange = (index, field, value) => {
    const updated = [...ingredients]
    updated[index][field] = value
    setIngredients(updated)
  }

  const handleInstructionChange = (e) => {
    setRecipie({ ...recipie, instruction: e.target.value })
  }

  if (!recipie) return <p>Lade Rezept...</p>

  return (
    <>
      <NavBar />
      <div className="update-recipie-container">
        <h1>Update Recipie</h1>

        <label>Name</label>
        <input
          type="text"
          value={recipie.name}
          onChange={(e) => setRecipie({ ...recipie, name: e.target.value })}
        />

        <label>Time</label>
        <div className="time-input">
          <input
            type="number"
            value={recipie.time}
            onChange={(e) => setRecipie({ ...recipie, time: e.target.value })}
          />
          <span>min</span>
        </div>

        <h2>Ingredients</h2>
        {ingredients.map((ing, index) => (
          <IngredientComponent
            key={index}
            index={index + 1}
            name={ing.name}
            amount={ing.amount}
            onChange={(field, value) => handleIngredientChange(index, field, value)}
            editable={true}
          />
        ))}

        <ButtonPlusIngredient onClick={handleAddIngredient} />

        <h2>Instruction</h2>
        <textarea
          value={recipie.instruction}
          onChange={handleInstructionChange}
          rows="6"
        />

        {/* Optional: Save button */}
        {/* <button className="save-button">Save Changes</button> */}
      </div>
    </>
  )
}

export default UpdateRecipie
