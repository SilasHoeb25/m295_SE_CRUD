import '../CSS/IngredientComponent.css'

const IngredientComponent = ({ index, value, onChange }) => {
  return (
    <div className="ingredient-row">
      <label>{index + 1}.</label>
      <input
        type="text"
        placeholder="Ingredient"
        value={value.name}
        onChange={(e) => onChange(index, { ...value, name: e.target.value })}
      />
      <input
        type="text"
        placeholder="Amount"
        value={value.amount}
        onChange={(e) => onChange(index, { ...value, amount: e.target.value })}
      />
    </div>
  )
}

export default IngredientComponent
