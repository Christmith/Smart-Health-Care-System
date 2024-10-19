
// import React, { useEffect, useState } from "react";
// import { Chart } from "react-google-charts";

// function AppointmentCharts() {
//   const [appointments, setAppointments] = useState([]);

//   useEffect(() => {
//     // Fetch appointments data from the API
//     fetch("http://localhost:8080/appointment/getAll")
//       .then((response) => response.json())
//       .then((data) => setAppointments(data));
//   }, []);

//   // Helper to get appointment status counts
//   const getAppointmentStatusCounts = () => {
//     const statusCounts = {};
//     appointments.forEach((appointment) => {
//       const status = appointment.appointmentStatus;
//       statusCounts[status] = (statusCounts[status] || 0) + 1;
//     });
//     return statusCounts;
//   };

//   // Helper to get appointment counts per doctor
//   const getAppointmentCountsByDoctor = () => {
//     const doctorCounts = {};
//     appointments.forEach((appointment) => {
//       const doctorName = appointment.doctor.doctorName;
//       doctorCounts[doctorName] = (doctorCounts[doctorName] || 0) + 1;
//     });
//     return doctorCounts;
//   };

//   // Data for Appointment Status Pie Chart
//   const statusCounts = getAppointmentStatusCounts();
//   const statusData = [
//     ["Status", "Count"],
//     ...Object.entries(statusCounts),
//   ];

//   // Data for Appointments by Doctor Bar Chart
//   const doctorCounts = getAppointmentCountsByDoctor();
//   const doctorData = [
//     ["Doctor", "Appointments"],
//     ...Object.entries(doctorCounts),
//   ];

//   return (
//     <div style={{ display: "flex", flexDirection: "row", justifyContent: "center", gap: "20px" }}>
//       {/* <h2>Appointment Statistics</h2> */}

//       {/* Pie Chart for Appointment Status */}
//       <Chart
//         chartType="PieChart"
//         data={statusData}
//         options={{ title: "Appointment Status Breakdown", is3D: true }}
//         width={"500px"}
//         height={"300px"}
//       />

//       {/* Bar Chart for Appointments per Doctor */}
//       <Chart
//         chartType="BarChart"
//         data={doctorData}
//         options={{
//           title: "Appointments per Doctor",
//           hAxis: { title: "Appointments" },
//           vAxis: { title: "Doctor" },
//         }}
//         width={"500px"}
//         height={"300px"}
//       />
//     </div>
//   );
// }

// export default AppointmentCharts;





import React, { useEffect, useState } from "react";
import { Chart } from "react-google-charts";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSpinner } from "@fortawesome/free-solid-svg-icons"; // Import spinner icon

function AppointmentCharts() {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true); // Loading state

  useEffect(() => {
    // Fetch appointments data from the API
    fetch("http://localhost:8080/appointment/getAll")
      .then((response) => response.json())
      .then((data) => {
        setAppointments(data);
        setLoading(false); // Set loading to false after data is fetched
      })
      .catch(() => {
        setLoading(false); // Stop loading in case of an error
      });
  }, []);

  // Helper to get appointment status counts
  const getAppointmentStatusCounts = () => {
    const statusCounts = {};
    appointments.forEach((appointment) => {
      const status = appointment.appointmentStatus;
      statusCounts[status] = (statusCounts[status] || 0) + 1;
    });
    return statusCounts;
  };

  // Helper to get appointment counts per doctor
  const getAppointmentCountsByDoctor = () => {
    const doctorCounts = {};
    appointments.forEach((appointment) => {
      const doctorName = appointment.doctor.doctorName;
      doctorCounts[doctorName] = (doctorCounts[doctorName] || 0) + 1;
    });
    return doctorCounts;
  };

  // Data for Appointment Status Pie Chart
  const statusCounts = getAppointmentStatusCounts();
  const statusData = [
    ["Status", "Count"],
    ...Object.entries(statusCounts),
  ];

  // Data for Appointments by Doctor Bar Chart
  const doctorCounts = getAppointmentCountsByDoctor();
  const doctorData = [
    ["Doctor", "Appointments"],
    ...Object.entries(doctorCounts),
  ];

  return (
    <div style={{ display: "flex", flexDirection: "row", justifyContent: "center", gap: "20px" }}>
      {loading ? (
        <div style={{ textAlign: "center" }}>
          <FontAwesomeIcon icon={faSpinner} spin size="3x" />
          <p>Loading...</p>
        </div>
      ) : (
        <>
          {/* Pie Chart for Appointment Status */}
          <Chart
            chartType="PieChart"
            data={statusData}
            options={{ title: "Appointment Status Breakdown", is3D: true }}
            width={"500px"}
            height={"300px"}
          />

          {/* Bar Chart for Appointments per Doctor */}
          <Chart
            chartType="BarChart"
            data={doctorData}
            options={{
              title: "Appointments per Doctor",
              hAxis: { title: "Appointments" },
              vAxis: { title: "Doctor" },
            }}
            width={"500px"}
            height={"300px"}
          />
        </>
      )}
    </div>
  );
}

export default AppointmentCharts;
