import React, { useEffect, useState } from 'react';
import axios from 'axios';

const DashboardCards = () => {
  const [paymentData, setPaymentData] = useState([]);
  const [appointmentData, setAppointmentData] = useState([]);

  useEffect(() => {
    // Fetch payment data
    axios.get('http://localhost:8080/payments')
      .then(response => {
        setPaymentData(response.data);
      })
      .catch(error => {
        console.error('Error fetching payment data:', error);
      });

    // Fetch appointment data
    axios.get('http://localhost:8080/appointment/getAll')
      .then(response => {
        setAppointmentData(response.data);
      })
      .catch(error => {
        console.error('Error fetching appointment data:', error);
      });
  }, []);

  return (
    <div style={containerStyle}>
      {/* Payment Cards */}
      <div style={cardStyle}>
        <h3 style={{ fontWeight: 'bold' }}>Total Payments</h3>
        <h4>{paymentData.length}</h4>
      </div>

      <div style={cardStyle}>
        <h3 style={{ fontWeight: 'bold' }}>Total Amount Paid</h3>
        <h4>{paymentData.reduce((total, payment) => total + payment.paymentAmount, 0)}</h4>
      </div>

      <div style={cardStyle}>
        <h3 style={{ fontWeight: 'bold' }}>Completed Payments</h3>
        <h4>{paymentData.filter(payment => payment.status === 'Completed').length}</h4>
      </div>

      {/* Appointment Cards */}
      <div style={cardStyle}>
        <h3 style={{ fontWeight: 'bold' }}>Total Appointments</h3>
        <h4>{appointmentData.length}</h4>
      </div>
    </div>
  );
};

// Outer container style for centering
const containerStyle = {
  display: 'flex',
  justifyContent: 'center',
  gap: '20px',
  marginTop: '40px',     // Extra space above the card set
  marginBottom: '70px',  // Extra space below the card set
  flexWrap: 'wrap',      // Allows wrapping of cards if the screen is small
};

// Card Style
const cardStyle = {
  background: '#f9f9f9',
  padding: '20px',
  borderRadius: '8px',
  boxShadow: '0 4px 12px rgba(0,0,0,0.15)',  // Increased shadow for a more pronounced effect
  textAlign: 'center',
  width: '230px',
  transition: 'box-shadow 0.3s ease',  // Smooth hover effect
};

export default DashboardCards;
