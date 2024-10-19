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

//   // Helper to get appointment counts per date
//   const getAppointmentCountsByDate = () => {
//     const dateCounts = {};
//     appointments.forEach((appointment) => {
//       const date = new Date(appointment.appointmentDateTime).toLocaleDateString();
//       dateCounts[date] = (dateCounts[date] || 0) + 1;
//     });
//     return dateCounts;
//   };

//   // Data for Appointment Status Pie Chart
//   const statusCounts = getAppointmentStatusCounts();
//   const statusData = [
//     ["Status", "Count"],
//     ...Object.entries(statusCounts)
//   ];

//   // Data for Appointments by Doctor Bar Chart
//   const doctorCounts = getAppointmentCountsByDoctor();
//   const doctorData = [
//     ["Doctor", "Appointments"],
//     ...Object.entries(doctorCounts)
//   ];

//   // Data for Appointments by Date Line Chart
//   const dateCounts = getAppointmentCountsByDate();
//   const dateData = [
//     ["Date", "Appointments"],
//     ...Object.entries(dateCounts)
//   ];

//   return (
//     <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
//       <h2>Appointment Statistics</h2>

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
//         options={{ title: "Appointments per Doctor", hAxis: { title: "Appointments" }, vAxis: { title: "Doctor" } }}
//         width={"500px"}
//         height={"300px"}
//       />

//       {/* Line Chart for Appointments by Date */}
//       <Chart
//         chartType="LineChart"
//         data={dateData}
//         options={{ title: "Appointments by Date", hAxis: { title: "Date" }, vAxis: { title: "Appointments" } }}
//         width={"500px"}
//         height={"300px"}
//       />
//     </div>
//   );
// }

// export default AppointmentCharts;







import React, { useEffect, useState } from "react";
import { Chart } from "react-google-charts";

function AppointmentCharts() {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    // Fetch appointments data from the API
    fetch("http://localhost:8080/appointment/getAll")
      .then((response) => response.json())
      .then((data) => setAppointments(data));
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
      {/* <h2>Appointment Statistics</h2> */}

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
    </div>
  );
}

export default AppointmentCharts;

