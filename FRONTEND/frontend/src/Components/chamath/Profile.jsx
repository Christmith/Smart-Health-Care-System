import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Profile.css"; // External CSS for styling

const Profile = () => {
    // Hardcoded email for testing
    const userEmail = "johndoe@example.com";

    const [customerData, setCustomerData] = useState(null);
    const [isEditing, setIsEditing] = useState(false); // Tracks if we are in edit mode
    const [formData, setFormData] = useState({
        fullName: "",
        dateOfBirth: "",
        phoneNumber: "",
        emergencyContact: "",
        nicNumber: "",
        medicalHistory: "",
        allergies: "",
        previousTreatments: ""
    });

    // Fetch customer details on component load
    useEffect(() => {
        console.log("Fetching data for user:", userEmail);
        axios
            .get(`/api/customers/${userEmail}`)
            .then((response) => {
                console.log("Response received:", response.data);
                setCustomerData(response.data);
                setFormData({
                    ...response.data,
                    medicalHistory: response.data.medicalHistory.join(", "),
                    allergies: response.data.allergies.join(", "),
                    previousTreatments: response.data.previousTreatments.join(", ")
                });
            })
            .catch((error) => {
                console.error("Error fetching customer details:", error);
            });
    }, [userEmail]);

    // Handle input changes in form
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    // Submit updated customer details to the server
    const handleSubmit = () => {
        const updatedData = {
            ...formData,
            medicalHistory: formData.medicalHistory.split(",").map(item => item.trim()), // Convert comma-separated string to array
            allergies: formData.allergies.split(",").map(item => item.trim()), // Convert comma-separated string to array
            previousTreatments: formData.previousTreatments.split(",").map(item => item.trim()) // Convert comma-separated string to array
        };

        axios
            .put(`/api/customers/${userEmail}`, updatedData)
            .then((response) => {
                // Update the customer data
                setCustomerData(response.data);

                // Show success message
                alert("User details successfully updated!");

                // Set updated form data
                setFormData({
                    ...response.data,
                    medicalHistory: response.data.medicalHistory.join(", "),
                    allergies: response.data.allergies.join(", "),
                    previousTreatments: response.data.previousTreatments.join(", ")
                });

                // Exit editing mode
                setIsEditing(false);
            })
            .catch((error) => {
                console.error("Error updating customer details:", error);
            });
    };

    // Cancel editing and revert to original data
    const handleCancel = () => {
        setFormData({
            ...customerData,
            medicalHistory: customerData.medicalHistory.join(", "),
            allergies: customerData.allergies.join(", "),
            previousTreatments: customerData.previousTreatments.join(", ")
        });
        setIsEditing(false);
    };

    // Handle account deletion using email
    const handleDelete = () => {
        if (window.confirm("Are you sure you want to delete your account?")) {
            axios
                .delete(`/api/customers/${userEmail}`)
                .then(() => {
                    alert("Account successfully deleted");
                    window.location.href = "/login"; // Redirect to login page
                })
                .catch((error) => {
                    console.error("Error deleting account:", error);
                });
        }
    };

    // Return loading until data is fetched
    if (!customerData) {
        return <p>Loading...</p>;
    }

    return (
        <div className="profile-container" >
            {/* Container 1: Profile Picture and Name */}
            <div className="profile-section profile-picture" style={{alignItems:"center",marginTop:20,marginLeft:20,height:690,backgroundColor:'#fde5df'}}>
                <img
                    src="https://cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
                    alt="Profile"
                    className="profile-img"
                />
                <h3>{customerData.fullName}</h3>
            </div>

            {/* Container 2: Personal Details */}
            <div className="profile-section profile-details" style={{marginTop:20,height:690,backgroundColor:'#fde5df'}}>
                <h4>Personal Details</h4>
                <p><strong>Full Name:</strong> {isEditing ? <input name="fullName" value={formData.fullName} onChange={handleChange} /> : customerData.fullName}</p>
                <p><strong>Date of Birth:</strong> {isEditing ? <input name="dateOfBirth" type="date" value={formData.dateOfBirth} onChange={handleChange} /> : customerData.dateOfBirth}</p>
                <p><strong>Phone Number:</strong> {isEditing ? <input name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} /> : customerData.phoneNumber}</p>
                <p><strong>Emergency Contact:</strong> {isEditing ? <input name="emergencyContact" value={formData.emergencyContact} onChange={handleChange} /> : customerData.emergencyContact}</p>
                <p><strong>NIC Number:</strong> {isEditing ? <input name="nicNumber" value={formData.nicNumber} onChange={handleChange} /> : customerData.nicNumber}</p>
            </div>

            {/* Container 3: Medical Information */}
            <div className="profile-section profile-medical-info" style={{marginTop:20,marginRight:20,height:690,backgroundColor:'#fde5df'}}>
                <h4>Medical Information</h4>
                <p><strong>Medical History:</strong> {isEditing ? <textarea name="medicalHistory" value={formData.medicalHistory} onChange={handleChange} /> : customerData.medicalHistory.join(", ")}</p>
                <p><strong>Allergies:</strong> {isEditing ? <textarea name="allergies" value={formData.allergies} onChange={handleChange} /> : customerData.allergies.join(", ")}</p>
                <p><strong>Previous Treatments:</strong>
                    {isEditing ? (
                        <textarea name="previousTreatments" value={formData.previousTreatments} onChange={handleChange} />
                    ) : (
                        <ul>
                            {customerData.previousTreatments.map((treatment, index) => (
                                <li key={index}>{treatment}</li>
                            ))}
                        </ul>
                    )}
                </p>

                {!isEditing ? (
                    <button className="btn btn-primary"  onClick={() => setIsEditing(true)}>Update Details</button>
                ) : (
                    <div>
                        <button className="btn btn-success" onClick={handleSubmit}>Submit</button>
                        <button className="btn btn-secondary" onClick={handleCancel}>Cancel</button>
                    </div>
                )}
                {!isEditing && (
                    <button className="btn btn-danger mt-3" onClick={handleDelete}>Delete Account</button>
                )}
            </div>
        </div>
    );
};

export default Profile;
