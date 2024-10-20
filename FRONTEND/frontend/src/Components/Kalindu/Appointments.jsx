import React, { useEffect, useState } from "react";
import "./Appointments.css";
import AppointmentCard from "./AppointmentCard";
import axios from "axios";
import { Link } from "react-router-dom";

function Appointments() {
  const [appointments, setAppointments] = useState([]);

  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost:8080/appointments")
      .then((res) => {
        setIsLoading(false);
        setAppointments(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const appointmentCards = appointments.map((appointment) => {
    return (
      <AppointmentCard
        key={appointment.appointmentId}
        appointmentDetails={appointment}
      />
    );
  });

  return (
    <div className="container">
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          paddingTop: "20px",
        }}
      >
        <h2>My Appointments</h2>
        <Link to={"/user/patient/appointments/add"}>
          <button style={{ marginRight: "40px" }} onClick={() => {}}>
            Add
          </button>
        </Link>
      </div>
      <div className="appointments-container">
        {isLoading ? "Loading..." : appointmentCards}
      </div>
    </div>
  );
}

export default Appointments;
