# Cookbook Web Application

This project is part of the school module 294/295 and demonstrates a simple full-stack CRUD web application.

## 🧾 Description

The Cookbook is a web application that allows users to manage cooking recipes. It consists of a React frontend, a Java Spring Boot backend, and a MySQL database. The app supports all standard CRUD operations: Create, Read, Update, and Delete.

## ⚙️ Tech Stack

- **Frontend:** React (Vite)
- **Backend:** Java Spring Boot
- **Database:** MySQL

## 🔧 Features

- View a list of all recipes
- View detailed information about a single recipe
- Create new recipes with ingredients
- Update existing recipes
- Delete recipes (including related recipe-ingredient links)
- API communication via REST (JSON)

## 🚀 Running the Application

### Backend

1. Navigate to the backend directory:
cd backend

2. Start the Spring Boot application:

./mvnw spring-boot:run


### Frontend

1. Navigate to the frontend directory:

cd frontend

2. Install dependencies:

npm install

3. Start the development server:

npm run dev


## 🌐 API Endpoints

- `GET /recipies` – Get all recipes
- `GET /recipies/{id}` – Get a single recipe with ingredients
- `POST /recipies` – Create a new recipe
- `PUT /recipies/{id}` – Update a recipe
- `DELETE /recipies/{id}` – Delete a recipe (including links)

## 📚 Author

Created as part of Module 294/295 at a Swiss vocational school (Informatiker EFZ – Applikationsentwickler - Switzerland
