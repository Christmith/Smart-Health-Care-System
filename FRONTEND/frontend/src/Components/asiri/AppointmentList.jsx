import React, { useState, useEffect } from "react";
import axios from "axios";
import AdminHeader from "../common/AdminHeader";

function AppointmentList() {
  const [appointments, setAppointments] = useState([]);
  const [editableAppointmentId, setEditableAppointmentId] = useState(null);
  const [status, setStatus] = useState({}); // Holds status for each appointment

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = () => {
    axios
      .get("http://localhost:8080/appointment/getAll")
      .then((response) => {
        setAppointments(response.data);
        const initialStatuses = {};
        response.data.forEach((appointment) => {
          initialStatuses[appointment.appointmentId] = appointment.appointmentStatus;
        });
        setStatus(initialStatuses); // Set initial status values
      })
      .catch((error) => {
        console.error("Error fetching appointments:", error);
      });
  };

  const handleUpdate = (appointmentId) => {
    const newStatus = status[appointmentId];

    axios
      .put(`http://localhost:8080/appointment/${appointmentId}/${newStatus}`)
      .then(() => {
        alert("Appointment status updated successfully");
        setEditableAppointmentId(null);
        fetchAppointments(); // Refresh appointments after update
      })
      .catch((error) => {
        console.error("Error updating appointment:", error);
      });
  };

  const handleStatusChange = (e, appointmentId) => {
    setStatus((prevStatus) => ({
      ...prevStatus,
      [appointmentId]: e.target.value, // Update status for the specific appointment
    }));
  };


  const formatAppointmentDate = (dateString) => {
    const date = new Date(dateString);
  
    // Get day (e.g., Fri)
    const dayOfWeek = date.toLocaleString('en-US', { weekday: 'short' });
  
    // Get month (e.g., Oct)
    const month = date.toLocaleString('en-US', { month: 'short' });
  
    // Get day of the month (e.g., 18)
    const day = date.getDate();
  
    // Get time in 24-hour format (e.g., 13:18)
    const time = date.toLocaleString('en-US', { hour: '2-digit', minute: '2-digit', hour12: false });
  
    // Format and return the result as "Fri Oct 18 at 13:18"
    return `${dayOfWeek} ${month} ${day} at ${time}`;
  };


  const handleDelete = (appointmentId) => {
    axios
      .delete(`http://localhost:8080/appointment/admin-delete/${appointmentId}`)
      .then(() => {
        alert("Appointment deleted successfully");
        fetchAppointments(); // Refresh appointments after deletion
      })
      .catch((error) => {
        console.error("Error deleting appointment:", error);
      });
  };

  return (
    <>
    <AdminHeader pageName={'Dashboard'} />
    <div className="container mt-5" style={{ width: '1200px', backgroundColor: '#EEEEEE' }}>
      <center>
        <div className="rounded-5 div-shadow mt-5 pb-4 example example1" style={{ width: '1180px', height: '450px', overflowY: 'scroll', backgroundColor: '#EEEEEE' }}>
          <table className="table-hover example1" style={{ width: '1090px', backgroundColor: '#EEEEEE' }}>
            <thead style={{ fontSize: '18px', height: '90px' }}>
              <tr className="border-bottom border-dark border-3 pt-3" style={{ position: 'sticky', top: 0, backgroundColor: '#EEEEEE' }}>
                <th>Appointment Date & Time</th>
                <th>Patient Name</th>
                <th>Doctor Name</th>
                <th>Specialization</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {appointments.map((appointment) => (
                <tr key={appointment.appointmentId}>
                  <td>{appointment.appointmentDateTime}</td>
                  <td>{appointment.patient.fullName}</td>
                  <td>{appointment.doctor.doctorName}</td>
                  <td>{appointment.doctor.doctorSpecialization}</td>
                  <td>
                    {editableAppointmentId === appointment.appointmentId ? (
                      <select
                        className="form-control"
                        value={status[appointment.appointmentId]}
                        onChange={(e) => handleStatusChange(e, appointment.appointmentId)}
                      >
                        <option value="pending">Pending</option>
                        <option value="done">Done</option>
                        <option value="canceled">Canceled</option>
                      </select>
                    ) : (
                      appointment.appointmentStatus
                    )}
                  </td>
                  <td>
                    {editableAppointmentId === appointment.appointmentId ? (
                      <>
                        <button className="btn btn-success me-2" onClick={() => handleUpdate(appointment.appointmentId)}>
                          Save
                        </button>
                        <button className="btn btn-danger" onClick={() => setEditableAppointmentId(null)}>
                          Cancel
                        </button>
                      </>
                    ) : (
                      <>
                        <button className="btn btn-warning me-2" onClick={() => setEditableAppointmentId(appointment.appointmentId)}>
                          Edit 
                        </button>
                        <button className="btn btn-danger" onClick={() => handleDelete(appointment.appointmentId)}>
                          Delete
                        </button>
                      </>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </center>

      {/* CSS Styles */}
      <style jsx>{`
        .btn {
          width: 90px; /* Set button width */
          height: 35px; /* Set button height */
          display: inline-block;
        }

        .btn-warning {
          background-color: #ff9800;
          border: none;
          color: white;
        }

        .btn-success {
          background-color: #4caf50;
          border: none;
          color: white;
        }

        .btn-danger {
          background-color: #f44336;
          border: none;
          color: white;
        }

        .btn:hover {
          opacity: 0.8;
        }
      `}</style>
    </div>
    </>
  );
}

export default AppointmentList;
