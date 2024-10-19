import React, { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faEdit } from "@fortawesome/free-solid-svg-icons";
import AdminHeader from "../common/AdminHeader";

function PayList() {
  const [payment, setPayment] = useState([]);
  const [editablePaymentId, setEditablePaymentId] = useState(null);

  useEffect(() => {
    fetchPaymentData();
  }, []);

  const fetchPaymentData = () => {
    axios
      .get("http://localhost:8080/payments")
      .then((response) => {
        setPayment(response.data);
      })
      .catch((error) => {
        console.error("Error fetching payment data:", error);
      });
  };

  const handleUpdate = (paymentId) => {
    const updatedPayment = payment.find((payment) => payment.paymentId === paymentId);
    
    if (updatedPayment) {
      axios
        .put(`http://localhost:8080/payments/${paymentId}`, updatedPayment)
        .then(() => {
          alert("Payment updated successfully");
          setEditablePaymentId(null); // Exit edit mode
          fetchPaymentData(); // Fetch updated payment data
        })
        .catch((error) => {
          console.error("Error updating payment:", error);
        });
    }
  };

  const handleDelete = (paymentId) => {
    if (window.confirm('Are you sure you want to delete this payment?')) {
      axios
        .delete(`http://localhost:8080/payments/${paymentId}`)
        .then(() => {
          setPayment((prevPayment) =>
            prevPayment.filter((payment) => payment.paymentId !== paymentId)
          );
        })
        .catch((error) => {
          console.error("Error deleting payment:", error);
        });
    }
  };

  const handleEdit = (paymentId) => {
    setEditablePaymentId(paymentId);
  };

  const handleChange = (e, fieldName, paymentId) => {
    setPayment((prevPayment) => {
      return prevPayment.map((payment) => {
        if (payment.paymentId === paymentId) {
          return {
            ...payment,
            [fieldName]: e.target.value,
          };
        }
        return payment;
      });
    });
  };

  return (
    <>
      <AdminHeader pageName={'Payment List'} />
      <div className="container mt-5 shadow rounded" style={{ width: '1200px', backgroundColor: '#EEEEEE' }}>
        <center>
          <div className="rounded-5 div-shadow mt-5 pb-4 example example1" style={{ width: '1180px', height: '450px', overflowY: 'scroll', backgroundColor: '#EEEEEE' }}>
            <table className="table-hover example1" style={{ width: '1090px', backgroundColor: '#EEEEEE' }}>
              <thead style={{ fontSize: '18px', height: '90px' }}>
                <tr className="border-bottom border-dark border-3 pt-3" style={{ position: 'sticky', top: 0, backgroundColor: '#EEEEEE' }}>
                  <th>Payment Category</th>
                  <th>Amount</th>
                  <th>Payment Type</th>
                  <th>Cardholder Name</th>
                  <th>Remarks</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {payment.map((payment) => (
                  <tr key={payment.paymentId}>
                    <td>
                      {/* Disable Payment Category field during edit */}
                      {payment.paymentCategory}
                    </td>
                    <td>
                      {/* Disable Amount field during edit */}
                      {payment.paymentAmount}
                    </td>
                    <td>
                      {/* Disable Payment Type field during edit */}
                      {payment.paymentType}
                    </td>
                    <td>
                      {/* Disable Cardholder Name field during edit */}
                      {payment.cardholderName}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <select
                          className="form-control"
                          value={payment.remarks || "Select Remarks"}  // Set default value
                          onChange={(e) => handleChange(e, "remarks", payment.paymentId)}
                        >
                          <option disabled value="Select Remarks">Select Remarks</option>  {/* Placeholder */}
                          <option value="Thank you for Your Payment">Thank you for Your Payment</option>
                          <option value="Payment Was not received">Payment Was not received</option>
                          <option value="Payment is processing">Payment is processing</option>
                        </select>
                      ) : (
                        payment.remarks
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <select
                          className="form-control"
                          value={payment.status || "Select Status"}  // Set default value
                          onChange={(e) => handleChange(e, "status", payment.paymentId)}
                        >
                          <option disabled value="Select Status">Select Status</option>  {/* Placeholder */}
                          <option value="Pending">Pending</option>
                          <option value="Completed">Completed</option>
                          <option value="Failed">Failed</option>
                        </select>
                      ) : (
                        payment.status
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <>
                          <button className="btn btn-success action-button me-2" onClick={() => handleUpdate(payment.paymentId)}>Save</button>
                          <button className="btn btn-danger action-button" onClick={() => setEditablePaymentId(null)}>Cancel</button>
                        </>
                      ) : (
                        <>
                          <button className="btn btn-warning action-button me-2" onClick={() => handleEdit(payment.paymentId)}>
                            <FontAwesomeIcon icon={faEdit} />
                          </button>
                          <button className="btn btn-danger action-button" onClick={() => handleDelete(payment.paymentId)}>
                            <FontAwesomeIcon icon={faTrash} />
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
      </div>

      {/* CSS Styles */}
      <style jsx>{`
        .action-button {
          width: 90px; /* Set button width */
          height: 35px; /* Set button height */
          display: inline-block;
        }

        .action-button .fa-edit, .action-button .fa-trash {
          margin-right: 5px; /* Optional: to align icons nicely */
        }
      `}</style>
    </>
  );
}

export default PayList;
