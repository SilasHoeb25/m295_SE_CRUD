import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import NavBar from '../components/NavBar'
import '../CSS/Recipie.css'

const Recipie = () => {
  const { id } = useParams()
  const [recipie, setRecipie] = useState(null)

  useEffect(() => {
    // TODO: Später durch echten API-Call ersetzen
    setRecipie({
      id,
      name: 'Spaghetti Bolognese',
      time: 30,
      ingredients: [
        { name: 'Spaghetti', amount: '200g' },
        { name: 'Hackfleisch', amount: '250g' },
        { name: 'Tomatensauce', amount: '150ml' },
      ],
      instruction:
        'Die Spaghetti kochen. Fleisch anbraten. Tomatensauce hinzufügen und mit den Spaghetti mischen.',
    })
  }, [id])

  if (!recipie) return <p>Lade Rezept...</p>

  return (
    <>
      <NavBar />
      <div className="recipie-container">
        <h1>{recipie.name}</h1>
        <p className="recipie-time">
          <strong>Time:</strong> {recipie.time} min
        </p>

        <h2>Ingredients</h2>
        <ul className="ingredient-list">
          {recipie.ingredients.map((ing, index) => (
            <li key={index}>
              {ing.amount} {ing.name}
            </li>
          ))}
        </ul>

        <h2>Instruction</h2>
        <p className="instruction">{recipie.instruction}</p>
      </div>
    </>
  )
}

export default Recipie
