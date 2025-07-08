const ButtonPlusIngredient = ({ onClick }) => {
    return (
      <button
        onClick={onClick}
        style={{
          backgroundColor: '#007bff',
          color: 'white',
          padding: '6px 12px',
          border: 'none',
          borderRadius: '4px',
          cursor: 'pointer',
          marginTop: '10px'
        }}
      >
        + Add Ingredient
      </button>
    )
  }
  
  export default ButtonPlusIngredient
  