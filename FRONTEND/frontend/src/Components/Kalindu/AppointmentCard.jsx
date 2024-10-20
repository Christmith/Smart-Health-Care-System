import React from "react";
import "./Appointments.css";

export default function AppointmentCard({ appointmentDetails }) {
  return (
    <div className="div-shadow appointments-card">
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Date:{" "}
        </p>
        <span>
          {new Date(appointmentDetails.appointmentDateTime)
            .toISOString()
            .substring(0, 10)}
        </span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Service:{" "}
        </p>
        <span>{appointmentDetails.serviceId.serviceName}</span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Doctor:{" "}
        </p>
        <span>{appointmentDetails.doctorId.doctorName}</span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Department:{" "}
        </p>
        <span>{appointmentDetails.serviceId.department}</span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Time:{" "}
        </p>
        <span>
          {new Date(appointmentDetails.appointmentDateTime)
            .toISOString()
            .substring(11, 16)}
        </span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Location:{" "}
        </p>
        <span>{appointmentDetails.serviceId.location}</span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Status:{" "}
        </p>
        <span>{appointmentDetails.appointmentStatus}</span>
      </div>
      <div className="flex">
        <p style={{ marginRight: "10px" }} className="bold">
          Description:{" "}
        </p>
        <span>{appointmentDetails.appointmentDesc}</span>
      </div>
    </div>
  );
}
