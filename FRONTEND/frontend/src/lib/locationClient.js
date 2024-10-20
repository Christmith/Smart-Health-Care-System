import axios from "axios";

//Get Locations by Department ID
const getLocationsByDepartmentId = async (departmentId) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/locations/department/${departmentId}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching locations:", error);
    throw error;
  }
};

export { getLocationsByDepartmentId };
