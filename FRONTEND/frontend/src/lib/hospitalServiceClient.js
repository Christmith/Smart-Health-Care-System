import axios from "axios";

//create new hospital service
const createService = async (serviceDetails) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/services",
      serviceDetails
    );
    return response.data;
  } catch (error) {
    console.error("Error creating service: ", error);
    throw error;
  }
};

//get all hospital services
const getAllServices = async () => {
  try {
    const response = await axios.get("http://localhost:8080/services");
    return response.data;
  } catch (error) {
    console.error("Error fetching services: ", error);
    throw error;
  }
};

export { createService, getAllServices };
