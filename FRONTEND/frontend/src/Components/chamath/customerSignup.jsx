import React, { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

const SignupForm = () => {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
        confirmPassword: "",
        fullName: "",
        dateOfBirth: "",
        gender: "",
        nicNumber: "",
        phoneNumber: "",
        emergencyContact: "",
        medicalHistory: [],
        allergies: [],
        bloodType: "",
        previousTreatments: []
    });

    const [step, setStep] = useState(1);
    const [errors, setErrors] = useState({});
    const [signupSuccess, setSignupSuccess] = useState(null); // For displaying success/failure popups

    // Validation rules for each field
    const validateField = (name, value) => {
        switch (name) {
            case "email":
                if (!value) {
                    return "Field is required";
                } else if (/[A-Z]/.test(value)) {
                    return "Capital letters are not allowed";
                } else if (!/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(value)) {
                    return "Invalid email format";
                }
                return null;


            case "password":
                if (!value) {
                    return "Field is required";
                } else if (value.length < 6) {
                    return "Password must be at least 6 characters";
                }
                return null;
            case "confirmPassword":
                if (!value) {
                    return "Field is required";
                } else if (value !== formData.password) {
                    return "Passwords do not match";
                }
                return null;
            case "fullName":
                if (!value) {
                    return "Field is required";
                }
                return null;
            case "dateOfBirth":
                if (!value) {
                    return "Field is required";
                }
                return null;
            case "gender":
                if (!value) {
                    return "Field is required";
                }
                return null;
            case "nicNumber":
                if (!value) {
                    return "Field is required";
                } else if (!/^[a-z0-9]{10,12}$/.test(value)) {
                    return "NIC must contain 10-12 numbers and letter";
                }
                return null;
            case "phoneNumber":
                if (!value) {
                    return "Field is required";
                } else if (!/^\d{10}$/.test(value)) {
                    return "Phone number must be exactly 10 digits";
                }
                return null;
            case "emergencyContact":
                if (!value) {
                    return "Field is required";
                } else if (!/^\d{10}$/.test(value)) {
                    return "Phone number must be exactly 10 digits";
                }
                return null;
            case "bloodType":
                if (!value) {
                    return "Field is required";
                }
                return null;
            default:
                return null;
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value
        }));
        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: validateField(name, value)
        }));
    };

    const handleArrayChange = (e, field) => {
        const { value } = e.target;
        setFormData((prevState) => ({
            ...prevState,
            [field]: value.split(",").map((item) => item.trim())
        }));
    };

    const nextStep = () => setStep(step + 1);
    const prevStep = () => setStep(step - 1);

    // Validate all fields before moving to the next step or submitting the form
    const validateForm = () => {
        const newErrors = {};
        Object.keys(formData).forEach((key) => {
            const error = validateField(key, formData[key]);
            if (error) {
                newErrors[key] = error;
            }
        });
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            axios
                .post("/api/customers", formData)
                .then((response) => {
                    setSignupSuccess(true); // Success popup
                    console.log("Success", response.data);
                })
                .catch((error) => {
                    setSignupSuccess(false); // Failure popup
                    console.error("Error", error);
                });
        }
    };

    return (
        <div
            className="d-flex justify-content-center align-items-center vh-100 mt-5 mb-5 "
            style={{ backgroundColor: "#f8f9fa" }}
        >
            <div className="card" style={{ width: "400px" }}>
                <div className="card-body">
                    <h2 className="card-title text-center mb-4">Sign Up</h2>
                    <form>
                        {step === 1 && (
                            <div>
                                <h4>Account Information</h4>
                                <div className="mb-3">
                                    <label>Email</label>
                                    <input
                                        type="email"
                                        name="email"
                                        className={`form-control ${errors.email ? "is-invalid" : ""}`}
                                        placeholder="Enter your email"
                                        value={formData.email}
                                        onChange={handleChange}
                                    />
                                    {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                                </div>
                                <div className="mb-3">
                                    <label>Password</label>
                                    <input
                                        type="password"
                                        name="password"
                                        className={`form-control ${errors.password ? "is-invalid" : ""}`}
                                        placeholder="Enter your password"
                                        value={formData.password}
                                        onChange={handleChange}
                                    />
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>
                                <div className="mb-3">
                                    <label>Confirm Password</label>
                                    <input
                                        type="password"
                                        name="confirmPassword"
                                        className={`form-control ${errors.confirmPassword ? "is-invalid" : ""}`}
                                        placeholder="Confirm your password"
                                        value={formData.confirmPassword}
                                        onChange={handleChange}
                                    />
                                    {errors.confirmPassword && <div className="invalid-feedback">{errors.confirmPassword}</div>}
                                </div>
                                <button className="btn btn-primary w-100" type="button" onClick={nextStep}>
                                    Next
                                </button>
                            </div>
                        )}

                        {step === 2 && (
                            <div>
                                <h4>Personal Information</h4>
                                <div className="mb-2">
                                    <label>Full Name</label>
                                    <input
                                        type="text"
                                        name="fullName"
                                        className={`form-control ${errors.fullName ? "is-invalid" : ""}`}
                                        placeholder="Enter your full name"
                                        value={formData.fullName}
                                        onChange={handleChange}
                                    />
                                    {errors.fullName && <div className="invalid-feedback">{errors.fullName}</div>}
                                </div>
                                <div className="mb-2">
                                    <label>Date of Birth</label>
                                    <input
                                        type="date"
                                        name="dateOfBirth"
                                        className={`form-control ${errors.dateOfBirth ? "is-invalid" : ""}`}
                                        value={formData.dateOfBirth}
                                        onChange={handleChange}
                                    />
                                    {errors.dateOfBirth && <div className="invalid-feedback">{errors.dateOfBirth}</div>}
                                </div>
                                <div className="mb-2">
                                    <label>Gender</label>
                                    <select
                                        name="gender"
                                        className={`form-control ${errors.gender ? "is-invalid" : ""}`}
                                        value={formData.gender}
                                        onChange={handleChange}
                                    >
                                        <option value="">Select Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="other">Other</option>
                                    </select>
                                    {errors.gender && <div className="invalid-feedback">{errors.gender}</div>}
                                </div>
                                <div className="mb-2">
                                    <label>NIC Number</label>
                                    <input
                                        type="text"
                                        name="nicNumber"
                                        className={`form-control ${errors.nicNumber ? "is-invalid" : ""}`}
                                        placeholder="Enter your NIC number"
                                        value={formData.nicNumber}
                                        onChange={handleChange}
                                    />
                                    {errors.nicNumber && <div className="invalid-feedback">{errors.nicNumber}</div>}
                                </div>
                                <div className="mb-2">
                                    <label>Phone Number</label>
                                    <input
                                        type="text"
                                        name="phoneNumber"
                                        className={`form-control ${errors.phoneNumber ? "is-invalid" : ""}`}
                                        placeholder="Enter your phone number"
                                        value={formData.phoneNumber}
                                        onChange={handleChange}
                                    />
                                    {errors.phoneNumber && <div className="invalid-feedback">{errors.phoneNumber}</div>}
                                </div>
                                <div className="mb-2">
                                    <label>Emergency Contact Number</label>
                                    <input
                                        type="text"
                                        name="emergencyContact"
                                        className={`form-control ${errors.emergencyContact ? "is-invalid" : ""}`}
                                        placeholder="Enter emergency contact number"
                                        value={formData.emergencyContact}
                                        onChange={handleChange}
                                    />
                                    {errors.emergencyContact && <div className="invalid-feedback">{errors.emergencyContact}</div>}
                                </div>
                                <div className="d-flex justify-content-between">
                                    <button className="btn btn-secondary w-25" type="button" onClick={prevStep}>
                                        Back
                                    </button>
                                    <button className="btn btn-primary w-25" type="button" onClick={nextStep}>
                                        Next
                                    </button>
                                </div>
                            </div>
                        )}

                        {step === 3 && (
                            <div>
                                <h4>Medical Information</h4>
                                <div className="mb-3">
                                    <label>Medical History</label>
                                    <textarea
                                        name="medicalHistory"
                                        className="form-control"
                                        placeholder="List your medical history (comma-separated)"
                                        value={formData.medicalHistory.join(", ")}
                                        onChange={(e) => handleArrayChange(e, "medicalHistory")}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label>Allergies</label>
                                    <textarea
                                        name="allergies"
                                        className="form-control"
                                        placeholder="List your allergies (comma-separated)"
                                        value={formData.allergies.join(", ")}
                                        onChange={(e) => handleArrayChange(e, "allergies")}
                                    />
                                </div>
                                <div className="mb-3">
                                    <label>Blood Type</label>
                                    <select
                                        name="bloodType"
                                        className={`form-control ${errors.bloodType ? "is-invalid" : ""}`}
                                        value={formData.bloodType}
                                        onChange={handleChange}
                                    >
                                        <option value="">Select Blood Type</option>
                                        <option value="A+">A+</option>
                                        <option value="A-">A-</option>
                                        <option value="B+">B+</option>
                                        <option value="B-">B-</option>
                                        <option value="O+">O+</option>
                                        <option value="O-">O-</option>
                                        <option value="AB+">AB+</option>
                                        <option value="AB-">AB-</option>
                                    </select>
                                    {errors.bloodType && <div className="invalid-feedback">{errors.bloodType}</div>}
                                </div>
                                <div className="mb-3">
                                    <label>Previous Treatments</label>
                                    <textarea
                                        name="previousTreatments"
                                        className="form-control"
                                        placeholder="List your previous treatments (comma-separated)"
                                        value={formData.previousTreatments.join(", ")}
                                        onChange={(e) => handleArrayChange(e, "previousTreatments")}
                                    />
                                </div>

                                <div className="d-flex justify-content-between">
                                    <button className="btn btn-secondary w-25" type="button" onClick={prevStep}>
                                        Back
                                    </button>
                                    <button className="btn btn-success w-25" type="button" onClick={handleSubmit}>
                                        Submit
                                    </button>
                                </div>
                            </div>
                        )}
                    </form>
                    {signupSuccess === true && (
                        <div className="alert alert-success mt-3">Successfully signed up!</div>
                    )}
                    {signupSuccess === false && (
                        <div className="alert alert-danger mt-3">Sign up failed! Please try again.</div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default SignupForm;
