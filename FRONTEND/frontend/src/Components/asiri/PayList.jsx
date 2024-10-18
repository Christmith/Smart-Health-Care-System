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
      <div className="container mt-5" style={{ width: '1200px', backgroundColor: '#EEEEEE' }}>
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
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="text"
                          className="form-control"
                          value={payment.paymentCategory}
                          onChange={(e) => handleChange(e, "paymentCategory", payment.paymentId)}
                        />
                      ) : (
                        payment.paymentCategory
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="number"
                          className="form-control"
                          value={payment.paymentAmount}
                          onChange={(e) => handleChange(e, "paymentAmount", payment.paymentId)}
                        />
                      ) : (
                        payment.paymentAmount
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="text"
                          className="form-control"
                          value={payment.paymentType}
                          onChange={(e) => handleChange(e, "paymentType", payment.paymentId)}
                        />
                      ) : (
                        payment.paymentType
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="text"
                          className="form-control"
                          value={payment.cardholderName}
                          onChange={(e) => handleChange(e, "cardholderName", payment.paymentId)}
                        />
                      ) : (
                        payment.cardholderName
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="text"
                          className="form-control"
                          value={payment.remarks}
                          onChange={(e) => handleChange(e, "remarks", payment.paymentId)}
                        />
                      ) : (
                        payment.remarks
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <input
                          type="text"
                          className="form-control"
                          value={payment.status}
                          onChange={(e) => handleChange(e, "status", payment.paymentId)}
                        />
                      ) : (
                        payment.status
                      )}
                    </td>
                    <td>
                      {editablePaymentId === payment.paymentId ? (
                        <>
                          <button className="btn btn-success" onClick={() => handleUpdate(payment.paymentId)}>Save</button>
                          <button className="btn btn-danger" onClick={() => setEditablePaymentId(null)}>Cancel</button>
                        </>
                      ) : (
                        <>
                          <button className="btn btn-warning" onClick={() => handleEdit(payment.paymentId)}>
                            <FontAwesomeIcon icon={faEdit} />
                          </button>
                          <button className="btn btn-danger" onClick={() => handleDelete(payment.paymentId)}>
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
    </>
  );
}

export default PayList;
