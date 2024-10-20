import axios from "axios";

//Get doctors by department Id
const getDoctorsByDepartmentId = async (departmentId) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/doctors/department/${departmentId}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching doctors:", error);
    throw error;
  }
};

//Get doctor by Id
const getDoctorById = async (doctorId) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/doctors/${doctorId}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching doctor:", error);
    throw error;
  }
};

export { getDoctorsByDepartmentId, getDoctorById };
