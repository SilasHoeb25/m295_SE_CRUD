const ButtonDelete = ({ onClick }) => {
    return (
      <button
        onClick={onClick}
        style={{
          backgroundColor: 'red',
          color: 'white',
          padding: '6px 12px',
          border: 'none',
          borderRadius: '4px',
          cursor: 'pointer'
        }}
      >
        Delete
      </button>
    )
  }
  
  export default ButtonDelete
  