import React from "react";
import AdminHeader from "../common/AdminHeader";

function ServiceList() {
  return (
    <>
      <AdminHeader pageName={"Services"} />
      <div style={styles.container}>
        <h1 style={styles.heading}>Healthcare Services</h1>

        {/* Overview Cards */}
        <div style={styles.cardContainer}>
          <div style={styles.card}>
            <span
              style={{ ...styles.statusDot, backgroundColor: "green" }}
            ></span>
            <h3 style={styles.cardTitle}>Newly Added Service</h3>
            <p style={styles.cardText}>Acupuncture Service</p>
          </div>
          <div style={styles.card}>
            <span
              style={{ ...styles.statusDot, backgroundColor: "red" }}
            ></span>
            <h3 style={styles.cardTitle}>Ongoing</h3>
            <p style={styles.cardText}>15</p>
          </div>
          <div style={styles.card}>
            <h3 style={styles.cardTitle}>Total Healthcare Services</h3>
            <p style={styles.cardText}>45 💖</p>
          </div>
          <div style={styles.card}>
            <h3 style={styles.cardTitle}>Most Utilized Service</h3>
            <p style={styles.cardText}>Emergency Room Services</p>
          </div>
        </div>

        {/* Filter Section */}
        <div style={styles.filterContainer}>
          <div style={styles.filterOptions}>
            <select style={styles.filterSelect}>
              <option>Status</option>
            </select>
            <select style={styles.filterSelect}>
              <option>Department</option>
            </select>
            <input
              type="text"
              placeholder="Starting Hour"
              style={styles.filterInput}
            />
            <input
              type="text"
              placeholder="Ending Hour"
              style={styles.filterInput}
            />
            <input
              type="text"
              placeholder="Enter Keywords ..."
              style={styles.filterInput}
            />
            <button style={styles.searchButton}>Search</button>
          </div>
          <div style={styles.managementHeader}>
            <h2 style={styles.managementHeading}>
              Healthcare Service Management
            </h2>
            <button style={styles.addButton}>Add new service +</button>
          </div>
        </div>

        {/* Service Table */}
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.tableHeader}>Service Name</th>
              <th style={styles.tableHeader}>Department</th>
              <th style={styles.tableHeader}>Location</th>
              <th style={styles.tableHeader}>Staff</th>
              <th style={styles.tableHeader}>Available Days</th>
              <th style={styles.tableHeader}>Available Hours</th>
              <th style={styles.tableHeader}>Status</th>
              <th style={styles.tableHeader}>Action</th>
            </tr>
          </thead>
          <tbody>
            {dummyData.map((service, index) => (
              <tr
                key={index}
                style={service.status === "Inactive" ? styles.inactiveRow : {}}
              >
                <td style={styles.tableCell}>{service.name}</td>
                <td style={styles.tableCell}>{service.department}</td>
                <td style={styles.tableCell}>{service.location}</td>
                <td style={styles.tableCell}>{service.staff}</td>
                <td style={styles.tableCell}>{service.availableDays}</td>
                <td style={styles.tableCell}>{service.availableHours}</td>
                <td style={styles.tableCell}>
                  <span
                    style={
                      service.status === "Active"
                        ? styles.activeStatus
                        : styles.inactiveStatus
                    }
                  >
                    {service.status}
                  </span>
                </td>
                <td style={styles.tableCell}>
                  <button style={styles.actionButton}>✏️</button>
                  <button style={styles.actionButton}>🗑️</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

const dummyData = [
  {
    name: "Physical Therapy",
    department: "Rehabilitation",
    location: "A-12",
    staff: "Dr. Priyantha, Ms. Wijecursiya",
    availableDays: "Sun, Wed, Fri",
    availableHours: "2pm - 4pm",
    status: "Active",
  },
  {
    name: "Pediatric Services",
    department: "Pediatric",
    location: "A-10",
    staff: "Dr. Gawesh, RN. Hewage",
    availableDays: "Mon, Wed, Fri, Sat",
    availableHours: "4pm - 6:30pm",
    status: "Inactive",
  },
  // Add more dummy data as needed
];

const styles = {
  container: {
    padding: "20px",
    marginTop: "40px",
    margin: "0 auto",
    maxWidth: "1200px",
    fontFamily: "Arial, sans-serif",
    backgroundColor: "#fff",
    borderRadius: "10px",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  },
  heading: {
    fontSize: "24px",
    fontWeight: "bold",
    marginBottom: "20px",
    textAlign: "left",
  },
  cardContainer: {
    display: "flex",
    gap: "20px",
    marginBottom: "20px",
    flexWrap: "wrap",
  },
  card: {
    flex: "1 1 calc(25% - 20px)",
    backgroundColor: "#f8f9fa",
    borderRadius: "8px",
    padding: "15px",
    textAlign: "center",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
    position: "relative",
  },
  statusDot: {
    width: "10px",
    height: "10px",
    borderRadius: "50%",
    position: "absolute",
    top: "10px",
    right: "10px",
  },
  cardTitle: {
    fontSize: "18px",
    marginBottom: "10px",
  },
  cardText: {
    fontSize: "16px",
    color: "#555",
  },
  filterContainer: {
    marginBottom: "20px",
  },
  filterOptions: {
    display: "flex",
    gap: "10px",
    alignItems: "center",
    flexWrap: "wrap",
  },
  filterSelect: {
    padding: "8px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  filterInput: {
    padding: "8px",
    borderRadius: "5px",
    border: "1px solid #ccc",
    flex: "1",
  },
  searchButton: {
    padding: "8px 12px",
    borderRadius: "5px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    cursor: "pointer",
  },
  managementHeader: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginTop: "15px",
  },
  managementHeading: {
    fontSize: "18px",
    fontWeight: "bold",
  },
  addButton: {
    backgroundColor: "#28a745",
    color: "#fff",
    padding: "8px 12px",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
  },
  tableHeader: {
    backgroundColor: "#f1f1f1",
    padding: "10px",
    textAlign: "left",
    fontWeight: "bold",
  },
  tableCell: {
    padding: "10px",
    borderBottom: "1px solid #ddd",
  },
  activeStatus: {
    color: "green",
    fontWeight: "bold",
  },
  inactiveStatus: {
    color: "red",
    fontWeight: "bold",
  },
  inactiveRow: {
    backgroundColor: "#fce4e4",
  },
  actionButton: {
    backgroundColor: "transparent",
    border: "none",
    cursor: "pointer",
    marginRight: "5px",
  },
};

export default ServiceList;
