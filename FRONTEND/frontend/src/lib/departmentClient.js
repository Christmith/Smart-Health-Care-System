import axios from "axios";

// Get all departments
const getDepartments = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/departments`);
    return response.data;
  } catch (error) {
    console.error("Error fetching departments:", error);
    throw error;
  }
};

export { getDepartments };
