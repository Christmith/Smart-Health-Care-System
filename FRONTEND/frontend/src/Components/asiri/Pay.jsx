import React from 'react';

function Pay() {
  return (
    <div style={styles.payContainer}>
      <div style={styles.payCard}>
        <h3 style={styles.payTitle}>Payment Page</h3>
        <p style={styles.payDescription}>Please proceed to make your payment below.</p>
        <div>
          <button style={styles.payButton}>Make Payment</button>
        </div>
      </div>
    </div>
  );
}

const styles = {
  payContainer: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',  // Full viewport height
    backgroundColor: '#f4f4f9',  // Light background color
  },
  payCard: {
    backgroundColor: '#fff',
    padding: '40px',
    borderRadius: '8px',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
    textAlign: 'center',
    width: '100%',
    maxWidth: '400px',  // Limiting max width
  },
  payTitle: {
    fontSize: '24px',
    fontWeight: 'bold',
    marginBottom: '20px',
    color: '#007bff',  // Blue color for the title
  },
  payDescription: {
    fontSize: '16px',
    marginBottom: '30px',
    color: '#555',
  },
  payButton: {
    backgroundColor: '#007bff',
    color: 'white',
    border: 'none',
    padding: '12px 20px',
    borderRadius: '5px',
    fontSize: '16px',
    cursor: 'pointer',
    transition: 'background-color 0.3s',
  },
};

export default Pay;
