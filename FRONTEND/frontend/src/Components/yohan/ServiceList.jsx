/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllServices } from "../../lib/hospitalServiceClient";
import { getDepartments } from "../../lib/departmentClient";

function ServiceList() {
  const navigate = useNavigate();
  const [services, setServices] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [filteredServices, setFilteredServices] = useState([]);
  const [departmentFilter, setDepartmentFilter] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [locationFilter, setLocationFilter] = useState("");
  const [serviceNameFilter, setServiceNameFilter] = useState("");
  const [newestService, setNewestService] = useState(null);
  const [totalServices, setTotalServices] = useState(0);
  const [ongoingServices, setOngoingServices] = useState(0);

  // Fetch all services from the server
  useEffect(() => {
    const fetchServices = async () => {
      try {
        const fetchResult = await getAllServices();
        setServices(fetchResult);
        setFilteredServices(fetchResult); // Initialize filtered services

        // Calculate newest service, total services, and ongoing services
        const total = fetchResult.length;
        const ongoing = fetchResult.filter(
          (service) => service.status === true
        ).length;
        const newest = fetchResult.reduce((latest, service) => {
          return new Date(service.createdAt) > new Date(latest.createdAt)
            ? service
            : latest;
        }, fetchResult[0]);

        setTotalServices(total); // Set total services count
        setOngoingServices(ongoing); // Set ongoing services count
        setNewestService(newest.serviceName); // Set newest service
      } catch (error) {
        console.error("Error fetching services: ", error);
      }
    };
    fetchServices();
  }, []);

  // Fetch all departments from the server
  useEffect(() => {
    const fetchDepartments = async () => {
      try {
        const fetchResult = await getDepartments();
        setDepartments(fetchResult);
      } catch (error) {
        console.error("Error fetching departments: ", error);
        throw error;
      }
    };
    fetchDepartments();
  }, []);

  // Filter Reset - This function will clear both filter fields and filter states
  const handleFilterReset = () => {
    setDepartmentFilter("");
    setStatusFilter("");
    setLocationFilter("");
    setServiceNameFilter("");

    // Clear input fields by setting filtered services to the original unfiltered list
    setFilteredServices(services);
  };

  // Filter services based on selected filters
  useEffect(() => {
    let filtered = services;

    // Department filter
    if (departmentFilter) {
      filtered = filtered.filter(
        (service) => service.department === departmentFilter
      );
    }

    // Status filter
    if (statusFilter) {
      const isActive = statusFilter === "active";
      filtered = filtered.filter((service) => service.status === isActive);
    }

    // Location filter - Fixed to use locationFilter instead of serviceNameFilter
    if (locationFilter) {
      filtered = filtered.filter((service) =>
        service.location.toLowerCase().includes(locationFilter.toLowerCase())
      );
    }

    // Service name filter
    if (serviceNameFilter) {
      filtered = filtered.filter((service) =>
        service.serviceName
          .toLowerCase()
          .includes(serviceNameFilter.toLowerCase())
      );
    }

    // Update the filtered services list
    setFilteredServices(filtered);
  }, [
    departmentFilter,
    statusFilter,
    locationFilter,
    serviceNameFilter,
    services,
  ]);

  const handleAddServiceClick = () => {
    navigate("/admin/service/addnewservice");
  };

  const handleViewClick = (serviceId) => {
    navigate(`/admin/service/${serviceId}`);
  };

  return (
    <>
      <div style={styles.container}>
        <h1 style={styles.heading}>Healthcare Services</h1>
        {/* Overview Cards */}
        <div style={styles.cardContainer}>
          <div style={styles.card}>
            <span
              style={{ ...styles.statusDot, backgroundColor: "green" }}
            ></span>
            <h3 style={styles.cardTitle}>Newly Added Service</h3>
            <p style={styles.cardText}>{newestService}</p>
          </div>
          <div style={styles.card}>
            <span
              style={{ ...styles.statusDot, backgroundColor: "red" }}
            ></span>
            <h3 style={styles.cardTitle}>Ongoing Services</h3>
            <p style={styles.cardText}>{ongoingServices}</p>
          </div>
          <div style={styles.card}>
            <h3 style={styles.cardTitle}>Total Healthcare Services</h3>
            <p style={styles.cardText}>{totalServices} 💖</p>
          </div>
          <div style={styles.card}>
            <h3 style={styles.cardTitle}>Most Utilized Service</h3>
            <p style={styles.cardText}>Emergency Room Services</p>
          </div>
        </div>
        {/* Filter Section */}
        <div style={styles.filterContainer}>
          <div style={styles.filterOptions}>
            <select
              style={styles.filterSelect}
              onChange={(e) => setDepartmentFilter(e.target.value)}
              value={departmentFilter}
            >
              <option value="">Department</option>
              {departments.map((department) => (
                <option
                  key={department.departmentId}
                  value={department.departmentName}
                >
                  {department.departmentName}
                </option>
              ))}
            </select>
            <select
              style={styles.filterSelect}
              onChange={(e) => setStatusFilter(e.target.value)}
              value={statusFilter}
            >
              <option value="">Status</option>
              <option value="active">Active</option>
              <option value="inactive">Inactive</option>
            </select>
            <input
              type="text"
              placeholder="Location"
              style={styles.filterInput}
              onChange={(e) => setLocationFilter(e.target.value)}
              value={locationFilter}
            />
            <input
              type="text"
              placeholder="Service Name"
              style={styles.filterInput}
              onChange={(e) => setServiceNameFilter(e.target.value)}
              value={serviceNameFilter}
            />
            <button style={styles.searchButton} onClick={handleFilterReset}>
              Reset
            </button>
          </div>
          <div style={styles.managementHeader}>
            <h2 style={styles.managementHeading}>
              Healthcare Service Management
            </h2>
            <button style={styles.addButton} onClick={handleAddServiceClick}>
              Add new service
            </button>
          </div>
        </div>
        {/* Service Table */}
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.tableHeader}>Service Name</th>
              <th style={styles.tableHeader}>Department</th>
              <th style={styles.tableHeader}>Location</th>
              <th style={styles.tableHeader}>Payment Options</th>
              <th style={styles.tableHeader}>Status</th>
              <th style={styles.tableHeader}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredServices.map((service) => (
              <tr
                key={service.serviceId} // Use serviceId as the key for better identification
                style={service.status === false ? styles.inactiveRow : {}}
              >
                <td style={styles.tableCell}>{service.serviceName}</td>
                <td style={styles.tableCell}>{service.department}</td>
                <td style={styles.tableCell}>{service.location}</td>
                <td style={styles.tableCell}>
                  {service.paymentOption.join(", ")}
                </td>
                <td style={styles.tableCell}>
                  <span
                    style={
                      service.status
                        ? styles.activeStatus
                        : styles.inactiveStatus
                    }
                  >
                    {service.status ? "Active" : "Inactive"}
                  </span>
                </td>
                <td style={styles.tableCell}>
                  <button
                    style={styles.actionButton}
                    onClick={() => handleViewClick(service.serviceId)}
                  >
                    View More
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

const styles = {
  container: {
    padding: "20px",
    margin: "0 auto",
    maxWidth: "1200px",
    fontFamily: "Arial, sans-serif",
    backgroundColor: "#fff",
    borderRadius: "10px",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  },
  heading: {
    fontSize: "28px",
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
    color: "#333",
  },
  filterSelect: {
    padding: "8px",
    borderRadius: "8px",
    border: "2px solid #333",
    backgroundColor: "#fff",
    color: "#333",
    flex: "1",
    minWidth: "150px",
    maxWidth: "250px",
  },
  filterInput: {
    padding: "8px",
    borderRadius: "8px",
    border: "2px solid #333",
    backgroundColor: "#fff",
    flex: "1",
    minWidth: "150px",
    maxWidth: "250px",
  },
  searchButton: {
    padding: "8px 12px",
    borderRadius: "5px",
    backgroundColor: "#F87171",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    flex: "1",
    minWidth: "150px",
    maxWidth: "250px",
    textAlign: "center",
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
    padding: "8px 12px",
    borderRadius: "5px",
    backgroundColor: "green",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    flex: "1",
    minWidth: "150px",
    maxWidth: "227px",
    textAlign: "center",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
  },
  tableHeader: {
    backgroundColor: "#FFE8E5",
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
    margin: "0 5px",
    padding: "10px 30px",
    cursor: "pointer",
    backgroundColor: "#F87171",
    color: "#fff",
    border: "none",
    borderRadius: "10px",
    fontSize: "14px",
  },
};

export default ServiceList;
