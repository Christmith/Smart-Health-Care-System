/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getDepartments } from "../../lib/departmentClient";
import { getLocationsByDepartmentId } from "../../lib/locationClient";
import { getDoctorsByDepartmentId } from "../../lib/doctorClient";
import { createService } from "../../lib/hospitalServiceClient";

function AddNewServiceComponent() {
  const [serviceName, setServiceName] = useState("");
  const [departments, setDepartments] = useState([]);
  const [selectedDepartmentId, setSelectedDepartmentID] = useState("");
  const [department, setDepartment] = useState("");
  const [departmentLocations, setDepartmentLocations] = useState([]);
  const [location, setLocation] = useState("");
  const [serviceDescription, setServiceDescription] = useState("");
  const [paymentOptions, setPaymentOptions] = useState([]);
  const [selectedDays, setSelectedDays] = useState([]);
  const [departmentDoctors, setDepartmentDoctors] = useState([]);
  const [selectedDoctors, setSelectedDoctors] = useState([]);
  const [timeSlots, setTimeSlots] = useState(new Map());
  const [isTimeSlotAdded, setIsTimeSlotAdded] = useState(false);
  const AvailablePaymentOptions = ["Cash", "Card", "Insurance", "Free"];
  const daysOfWeek = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  ];
  const navigate = useNavigate();

  //Get all departments
  useEffect(() => {
    const fetchDepartments = async () => {
      const fetchResult = await getDepartments();
      setDepartments(fetchResult);
    };
    fetchDepartments();
  }, []);

  //Get locations by department Id
  useEffect(() => {
    const fetchLocations = async () => {
      const fetchResult = await getLocationsByDepartmentId(
        selectedDepartmentId
      );
      setDepartmentLocations(fetchResult);
    };
    fetchLocations();
  }, [selectedDepartmentId]);

  //Get doctors by department Id
  useEffect(() => {
    const fetchDoctors = async () => {
      const fetchResult = await getDoctorsByDepartmentId(selectedDepartmentId);
      setDepartmentDoctors(fetchResult);
    };
    fetchDoctors();
  }, [selectedDepartmentId]);

  // update selected doctors by iterating through time slots and the distinct doctor ids
  useEffect(() => {
    const selectedDoctors = Array.from(timeSlots.values()).reduce(
      (acc, slots) => {
        const doctorIds = slots.map((slot) => slot.doctorId);
        return [...acc, ...doctorIds];
      },
      []
    );

    // Use a Set to filter out duplicate doctor IDs
    const distinctDoctors = Array.from(new Set(selectedDoctors));
    setSelectedDoctors(distinctDoctors);
  }, [timeSlots]);

  // Handle Create Service
  const handleCreateService = async () => {
    // Convert the timeSlots Map to the required structure
    const formattedTimeSlots = Array.from(timeSlots.entries()).reduce(
      (acc, [day, slots]) => {
        // Transform the slots into the required format: { doctorId: timeSlot }
        const formattedSlots = slots.map((slot) => ({
          [slot.doctorId]: slot.timeSlot, // Use the doctorId as the key and timeSlot as the value
        }));

        acc[day] = formattedSlots; // Assign the array of formatted slots to the day
        return acc;
      },
      {}
    );

    const serviceDetails = {
      serviceName: serviceName,
      department: department,
      location: location,
      serviceDescription: serviceDescription,
      paymentOption: paymentOptions,
      selectedDays: selectedDays,
      selectedDoctors: selectedDoctors,
      timeSlots: formattedTimeSlots,
    };

    // Show success alert
    alert("Service created successfully!");

    // Reset form
    setServiceName("");
    setDepartment("");
    setLocation("");
    setServiceDescription("");
    setPaymentOptions([]);
    setSelectedDays([]);
    setSelectedDoctors([]);
    setTimeSlots(new Map());

    // Navigate to service list page
    navigate(-1);
    try {
      const response = await createService(serviceDetails);
      console.log("Service Created: ", response);
    } catch (error) {
      console.error("Error creating service: ", error);
      throw error;
    }
  };

  const handleDaySelection = (day) => {
    setSelectedDays((prevSelectedDays) => {
      const newSelectedDays = prevSelectedDays.includes(day)
        ? prevSelectedDays.filter((d) => d !== day)
        : [...prevSelectedDays, day];

      if (!newSelectedDays.includes(day)) {
        setTimeSlots((prevTimeSlots) => {
          const updatedTimeSlots = new Map(prevTimeSlots);
          updatedTimeSlots.delete(day);
          return updatedTimeSlots;
        });
      }

      return newSelectedDays;
    });
  };

  const handleAddTimeSlot = (day) => {
    const doctorId = document.getElementById(`doctor-${day}`).value;
    const timeSlot = document.getElementById(`time-slot-${day}`).value;

    // Find the doctor object to get the name for display purposes
    const selectedDoctor = departmentDoctors.find(
      (doc) => doc.doctorId === doctorId
    );

    if (!doctorId || !timeSlot || !selectedDoctor) {
      alert("Please select a doctor and enter a time slot.");
      return;
    }

    setTimeSlots((prevTimeSlots) => {
      const updatedTimeSlots = new Map(prevTimeSlots);
      const dayTimeSlots = updatedTimeSlots.get(day) || [];

      // Check if this doctor and time slot already exists for this day
      const slotExists = dayTimeSlots.some(
        (slot) => slot.doctorId === doctorId && slot.timeSlot === timeSlot
      );

      if (!slotExists) {
        // Store only doctorId and timeSlot in the map
        dayTimeSlots.push({ doctorId, timeSlot });
        updatedTimeSlots.set(day, dayTimeSlots);
        setIsTimeSlotAdded(true);
      }

      return updatedTimeSlots;
    });

    // Reset the input fields
    document.getElementById(`doctor-${day}`).value = "";
    document.getElementById(`time-slot-${day}`).value = "";
  };

  const handleResetTimeSlots = (day) => {
    setTimeSlots((prevTimeSlots) => {
      const updatedTimeSlots = new Map(prevTimeSlots);
      updatedTimeSlots.delete(day);
      setIsTimeSlotAdded(false);
      return updatedTimeSlots;
    });
  };

  // UPDATED LOGIC: Filter doctors based on availability (not working on selected day and not already selected)
  const getAvailableDoctorsForDay = (day) => {
    const selectedDoctorIds =
      timeSlots.get(day)?.map((slot) => slot.doctorId) || [];

    // Filter doctors who haven't been selected and are not working on the specified day
    return departmentDoctors.filter(
      (doctor) =>
        !selectedDoctorIds.includes(doctor.doctorId) && // Exclude already selected doctors for the day
        !doctor.workingDays.includes(day) // Exclude doctors who are already working on the day
    );
  };

  const handlePaymentOptionChange = (option) => {
    setPaymentOptions((prevSelectedOptions) =>
      prevSelectedOptions.includes(option)
        ? prevSelectedOptions.filter((o) => o !== option)
        : [...prevSelectedOptions, option]
    );
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h2>Add New Service</h2>

      <div
        style={{ padding: 20, backgroundColor: "#FFE8E5", borderRadius: 15 }}
      >
        {/* Row 1: Service Name, Department, and Location */}
        <div style={{ display: "flex", gap: "10px", marginBottom: "10px" }}>
          <div style={{ flex: 1 }}>
            <label style={{ fontWeight: "bold" }}>Service Name:</label>
            <input
              type="text"
              value={serviceName}
              onChange={(e) => setServiceName(e.target.value)}
              placeholder="Enter service name"
              style={{
                width: "100%",
                padding: "5px",
                marginTop: "5px",
                backgroundColor: "#ffffff",
                color: "#333333",
                border: "2px solid #333333",
                borderRadius: "8px",
              }}
            />
          </div>
          <div style={{ flex: 1 }}>
            <label style={{ fontWeight: "bold" }}>Department:</label>
            <select
              value={selectedDepartmentId}
              onChange={(e) => {
                const selectedDeptId = e.target.value;
                setSelectedDepartmentID(selectedDeptId);

                // Find the selected department from the departments list
                const selectedDept = departments.find(
                  (dept) => dept.departmentId === selectedDeptId
                );

                if (selectedDept) {
                  setDepartment(selectedDept.departmentName); // Set the department
                }
              }}
              style={{
                width: "100%",
                padding: "5px",
                marginTop: "5px",
                backgroundColor: "#ffffff",
                color: "#333333",
                border: "2px solid #333333",
                borderRadius: "8px",
              }}
            >
              <option value="">Select Department</option>
              {departments.map((dept) => (
                <option key={dept.departmentId} value={dept.departmentId}>
                  {dept.departmentName}
                </option>
              ))}
            </select>
          </div>

          <div style={{ flex: 1 }}>
            <label style={{ fontWeight: "bold" }}>Location:</label>
            <select
              value={location}
              onChange={(e) => setLocation(e.target.value)}
              style={{
                width: "100%",
                padding: "5px",
                marginTop: "5px",
                backgroundColor: "#ffffff",
                color: "#333333",
                border: "2px solid #333333",
                borderRadius: "8px",
              }}
            >
              <option value="">Select Location</option>
              {departmentLocations.map((loc) => (
                <option key={loc.locationName} value={loc.locationName}>
                  {loc.locationName}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Row 2: Service Description and Payment Options */}
        <div style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
          <div style={{ flex: 2 }}>
            <label style={{ fontWeight: "bold" }}>Service Description:</label>
            <textarea
              value={serviceDescription}
              onChange={(e) => setServiceDescription(e.target.value)}
              placeholder="Enter service description"
              style={{
                width: "100%",
                padding: "5px",
                marginTop: "5px",
                backgroundColor: "#ffffff",
                color: "#333333",
                border: "2px solid #333333",
                borderRadius: "8px",
              }}
              rows="4"
            />
          </div>
          <div style={{ flex: 1 }}>
            <label style={{ fontWeight: "bold" }}>Payment Options:</label>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                gap: "5px",
                marginTop: "5px",
              }}
            >
              {AvailablePaymentOptions.map((option) => (
                <label key={option}>
                  <input
                    type="checkbox"
                    checked={paymentOptions.includes(option)}
                    onChange={() => handlePaymentOptionChange(option)}
                    style={{
                      marginRight: "5px",
                      cursor: "pointer",
                      colorScheme: "auto",
                    }}
                  />
                  {option}
                </label>
              ))}
            </div>
          </div>
        </div>

        {/* Time Slot Management and Display */}
        <h5>Available Days and Time Slots</h5>
        <table
          border="2px solid #333333"
          style={{
            width: "100%",
            borderCollapse: "collapse",
          }}
        >
          <thead>
            <tr>
              {daysOfWeek.map((day) => (
                <th key={day}>
                  <input
                    type="checkbox"
                    checked={selectedDays.includes(day)}
                    onChange={() => handleDaySelection(day)}
                    style={{
                      marginRight: "5px",
                      cursor: "pointer",
                      width: "16px",
                      height: "16px",
                      colorScheme: "initial",
                    }}
                  />
                  {day}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            <tr>
              {daysOfWeek.map((day) => (
                <td
                  key={day}
                  style={{
                    verticalAlign: "top",
                    padding: "10px",
                    border: "2px solid #333333",
                  }}
                >
                  {selectedDays.includes(day) && (
                    <div
                      style={{
                        display: "flex",
                        flexDirection: "column",
                        gap: "5px",
                      }}
                    >
                      <div
                        style={{
                          display: "flex",
                          flexDirection: "row",
                          alignItems: "center",
                        }}
                      >
                        <select
                          id={`doctor-${day}`}
                          style={{
                            width: "100%",
                            padding: "5px",
                            backgroundColor: "#ffffff",
                            color: "#333333",
                            border: "2px solid #333333",
                            borderRadius: "8px",
                          }}
                        >
                          <option value="">Select Doctor</option>
                          {getAvailableDoctorsForDay(day).map((doctor) => (
                            <option
                              key={doctor.doctorId}
                              value={doctor.doctorId}
                            >
                              {doctor.doctorName}
                            </option>
                          ))}
                        </select>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          flexDirection: "row",
                          alignItems: "center",
                        }}
                      >
                        <input
                          type="text"
                          id={`time-slot-${day}`}
                          placeholder="Time Slot"
                          style={{
                            width: "100%",
                            padding: "5px",
                            backgroundColor: "#ffffff",
                            color: "#333333",
                            border: "2px solid #333333",
                            borderRadius: "8px",
                          }}
                        />
                      </div>
                      <div
                        style={{
                          display: "flex",
                          flexDirection: "column",
                          gap: "5px",
                        }}
                      >
                        <button
                          onClick={() => handleAddTimeSlot(day)}
                          style={{
                            width: "100%",
                            padding: "5px",
                            backgroundColor: "#28a745",
                            color: "white",
                            border: "none",
                            cursor: "pointer",
                            borderRadius: "8px",
                          }}
                        >
                          Add Time Slot
                        </button>
                        {timeSlots.get(day)?.length > 0 && (
                          <button
                            onClick={() => handleResetTimeSlots(day)}
                            style={{
                              width: "100%",
                              padding: "5px",
                              backgroundColor: "#dc3545",
                              color: "white",
                              border: "none",
                              cursor: "pointer",
                              borderRadius: "8px",
                            }}
                          >
                            Reset
                          </button>
                        )}
                      </div>
                      {/* Display added time slots */}
                      {timeSlots.get(day) && (
                        <ul
                          style={{
                            paddingLeft: "0",
                            listStyleType: "none",
                            marginTop: "10px",
                          }}
                        >
                          {timeSlots.get(day).map((slot, index) => {
                            // Find the doctor name for display purposes using the doctorId stored in the slot
                            const doctor = departmentDoctors.find(
                              (doc) => doc.doctorId === slot.doctorId
                            );
                            return (
                              <li key={index} style={{ marginBottom: "5px" }}>
                                <span style={{ fontWeight: "bold" }}>
                                  {doctor?.doctorName}:{" "}
                                  {/* Display doctor name here */}
                                </span>{" "}
                                {slot.timeSlot} {/* Display the time slot */}
                              </li>
                            );
                          })}
                        </ul>
                      )}
                    </div>
                  )}
                </td>
              ))}
            </tr>
          </tbody>
        </table>

        {/* Create Service Button */}
        <button
          style={{
            marginTop: "20px",
            padding: "10px 20px",
            borderRadius: "8px",
            backgroundColor: "#F87171",
            color: "white",
            border: "none",
            cursor: "pointer",
            fontSize: "16px",
          }}
          onClick={() => {
            handleCreateService();
          }}
        >
          Create Service
        </button>
      </div>
    </div>
  );
}

export default AddNewServiceComponent;
