import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

export const getAllRecipies = async () => {
  const response = await axios.get(`${BASE_URL}/recipies`);
  return response.data;
};

export const createRecipie = async (recipie) => {
  const response = await axios.post(`${BASE_URL}/recipies`, recipie);
  return response.data;
};