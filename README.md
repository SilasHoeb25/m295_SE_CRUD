# Project Documentation – Kochbuch App

This document contains the full documentation for the **Kochbuch** application, created for modules **M294 (Frontend)** and **M295 (Backend)**.

---

## 1. Project Idea

### Overview
With this app you can manage cooking recipes and their ingredients. You can create, view, edit, and delete recipes. Each recipe can contain multiple ingredients including quantity, and you can modify or remove them as needed.

The application is designed to simplify recipe management for home cooks, food bloggers, or anyone who wants to digitally organize their favorite meals.

### Purpose
Keeping track of ingredients and instructions on paper or in scattered documents can get chaotic. You often end up rewriting recipes, forgetting ingredients, or losing notes.

This app solves that problem by allowing you to digitally manage structured recipes — clearly, consistently, and from any browser. Whether you're cooking every day or planning meals for the week, Kochbuch helps you stay organized and inspired.

---

## 2. Requirements

### Functional Requirements

- The user can create a new recipe with name, description, and ingredients.
- The user can view the list of all recipes.
- The user can view details of a single recipe.
- The user can update a recipe and change its ingredients.
- The user can delete a recipe.
- The user can fetch and manage ingredients.

---

### UI/UX Functional Flow (Frontend-Specific Behaviour)

#### Recipe Overview
- Displays all created recipes in a card layout.
- A “+ New Recipe” button opens a creation page.
- A Recipie can be viewed by a click on the Card, edited, and deleted with the respective buttons.

#### Recipe Creation & Edit
- A form with input fields for:
  - Title
  - Description
  - Dynamic list of ingredients with name and amount
- Buttons for save, cancel, and add ingredient.
- Validation on required fields before submission.

#### Recipe Details
- Displays recipe title, description, and ingredients.
- Options to edit or delete recipe.
- Confirmations on destructive actions (delete).

---

### Non-Functional Requirements

- All data persists via MySQL DB.
- Backend validates inputs and exposes REST API.
- Frontend handles errors and validates fields.
- App loads in under 2 seconds and responds instantly to CRUD actions.
- Basic unit tests implemented for both backend and frontend logic.

---

## 3. Use Cases

### Use Case 1: Create Recipe
- **Actor**: User  
- **Preconditions**: User is on the overview page  
- **Steps**:  
  1. Click “+ New Recipe”  
  2. Fill out title, description, and add ingredients  
  3. Click “Save”  
- **Expected Result**:  
  Recipe is added and shown on the overview page.

---

### Use Case 2: Edit Recipe
- **Actor**: User  
- **Preconditions**: At least one recipe exists  
- **Steps**:  
  1. Click “Edit” on a recipe card  
  2. Change title or ingredients  
  3. Click “Save”  
- **Expected Result**:  
  Changes are saved and visible in the recipe card and details.

---

### Use Case 3: View Recipe Details
- **Actor**: User  
- **Preconditions**: At least one recipe exists  
- **Steps**:  
  1. Click on a recipe title or “View”  
- **Expected Result**:  
  Detailed view of the recipe opens with all information.

---

### Use Case 4: Delete Recipe
- **Actor**: User  
- **Preconditions**: At least one recipe exists  
- **Steps**:  
  1. Click “Delete” on a recipe  
  2. Confirm deletion  
- **Expected Result**:  
  Recipe is removed from the overview list.

---

## 4. Class Diagram

### Entities & Relationships

- **Recipie**
  - `id` (String): Unique identifier
  - `name` (String): Recipe name
  - `description` (String): Description text

- **Ingredient**
  - `id` (String): Unique identifier
  - `name` (String): Ingredient name

- **RecipieIngredient**
  - `id` (String)
  - `recipieId` (String): Foreign key
  - `ingredientId` (String): Foreign key
  - `amount` (String): e.g. 2 tsp, 100g

**Relations:**  
One `Recipie` → has many `RecipieIngredient`  
One `Ingredient` → can be part of many `RecipieIngredient`

---

## 5. REST API

### Endpoints

#### Recipie Endpoints

| Method | Endpoint             | Description            |
|--------|----------------------|------------------------|
| GET    | `/api/recipies`      | List all recipes       |
| GET    | `/api/recipies/{id}` | Get single recipe      |
| POST   | `/api/recipies`      | Create new recipe      |
| PUT    | `/api/recipies/{id}` | Update recipe          |
| DELETE | `/api/recipies/{id}` | Delete recipe          |

#### Ingredient Endpoints

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `/api/ingredients`   | List all ingredients     |

### Example Payload

```json
{
  "name": "Pasta Carbonara",
  "description": "A classic Roman dish.",
  "ingredients": [
    { "ingredientId": "abc123", "amount": "100g" }
  ]
}
```

---

## 6. Test Plan

### Environment
- React + Vite
- Spring Boot + Maven
- MySQL Database
- Tools: Insomnia, JUnit, Jest, React Testing Library

---

### 6.1 Manual API Testing (Insomnia)

| Test ID | Endpoint             | Description             | Expected Result     |
|--------|----------------------|-------------------------|---------------------|
| TC01   | GET /api/recipies    | List all recipes        | 200 OK, recipe list |
| TC02   | POST /api/recipies   | Add a new recipe        | 201 Created         |
| TC03   | PUT /api/recipies/id | Update recipe           | 200 OK              |
| TC04   | DELETE /api/recipies/id | Delete recipe        | 204 No Content      |
| TC05   | GET /api/ingredients | List ingredients        | 200 OK              |

---

### 6.2 Backend Unit Tests (JUnit)

| Test Class            | Method                            | Expected Outcome         |
|-----------------------|----------------------------------|---------------------------|
| RecipieServiceTest        | createRecipie_shouldReturnSavedRecipie | Saved successfully  |
| IngredientRepositoryTest | findByName_shouldReturnCorrectIngredient | Match found    |
| RecipieControllerTest | getAllRecipies_shouldReturnRecipieList | List returned       |

---

### 6.3 Frontend Component Tests (Jest)

| Component             | Test                            | Expected Outcome         |
|-----------------------|----------------------------------|--------------------------|
| CookBook.jsx          | Load & display recipes          | Cards are rendered       |
| RecipieEdit.jsx       | Pre-fill form and submit update | Updated recipe sent      |
| DeleteModal.jsx       | Confirm delete triggers callback| Callback executed         |

---

## 7. Installation Instructions

### Backend Setup

1. Navigate to `/backend`
2. Run:
```bash
./mvnw spring-boot:run
```

### Frontend Setup

1. Navigate to `/frontend`
2. Install dependencies:
```bash
npm install
```
3. Start frontend server:
```bash
npm run dev
```

---

## 8. Support Log

### Peer Help
- Frontend structure guidance by [classmate name]
- Backend test support from M294 resources

### Online Resources
- Spring Boot Documentation
- React Router Docs
- StackOverflow: form validation, useEffect tips

### Instructor Feedback
- Approved REST structure and domain model during planning phase
