import { useState } from 'react'
import NavBar from '../components/NavBar'
import IngredientComponent from '../components/IngredientComponent'
import ButtonPlusIngredient from '../components/ButtonPlusIngredient'
import '../CSS/AddRecipie.css'

const AddRecipie = () => {
  const [name, setName] = useState('')
  const [time, setTime] = useState('')
  const [instruction, setInstruction] = useState('')
  const [ingredients, setIngredients] = useState([{ name: '', amount: '' }])

  const handleIngredientChange = (index, updatedIngredient) => {
    const newIngredients = [...ingredients]
    newIngredients[index] = updatedIngredient
    setIngredients(newIngredients)
  }

  const addIngredient = () => {
    setIngredients([...ingredients, { name: '', amount: '' }])
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    const recipieData = {
      name,
      time,
      instruction,
      ingredients
    }

    console.log('Sending Recipie:', recipieData)
    // Hier später die POST-Anfrage ans Backend

    // Reset
    setName('')
    setTime('')
    setInstruction('')
    setIngredients([{ name: '', amount: '' }])
  }

  return (
    <>
      <NavBar />
      <div className="add-recipie-container">
        <h1>Add a new Recipie</h1>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Name:</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Time:</label>
            <div className="time-input">
              <input
                type="number"
                value={time}
                onChange={(e) => setTime(e.target.value)}
                required
              />
              <span>min</span>
            </div>
          </div>

          <h3>Ingredients</h3>
          {ingredients.map((ingredient, index) => (
            <IngredientComponent
              key={index}
              index={index}
              value={ingredient}
              onChange={handleIngredientChange}
            />
          ))}

          <ButtonPlusIngredient onClick={addIngredient} />

          <div className="form-group instruction-group">
            <label>Instruction:</label>
            <textarea
              value={instruction}
              onChange={(e) => setInstruction(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="submit-button">
            Save Recipie
          </button>
        </form>
      </div>
    </>
  )
}

export default AddRecipie
