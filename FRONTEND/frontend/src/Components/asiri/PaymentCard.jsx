import { useState } from "react";
import axios from "axios";
import "./Payment.css";

const PaymentCard = () => {
    const paymentOptions = {
        Consultation: 5500,
        Surgery: 12000,
        Medication: 3000,
    };

    const [paymentCategory, setPaymentCategory] = useState("");
    const [paymentAmount, setPaymentAmount] = useState("");
    const [paymentType, setPaymentType] = useState("Card");
    const [formData, setFormData] = useState({
        cardholderName: "",
        cardNumber: "",
        expiryDate: "",
        cvv: "",
    });
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [error, setError] = useState(null);

    const handleCategoryChange = (e) => {
        const selectedCategory = e.target.value;
        setPaymentCategory(selectedCategory);
        setPaymentAmount(paymentOptions[selectedCategory]);
    };

    const handleInputChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);
        setError(null);

        // Real-time validations
        if (!formData.cardholderName || formData.cardNumber.length < 16 || formData.expiryDate.length !== 5 || formData.cvv.length !== 3) {
            setError("Please enter valid card details.");
            setIsSubmitting(false);
            return;
        }

        const paymentData = {
            paymentCategory,
            paymentAmount,
            paymentType,
            cardholderName: formData.cardholderName,
            cardNumber: formData.cardNumber,
            expiryDate: formData.expiryDate,
            cvv: formData.cvv,
        };

        try {
            const response = await axios.post(
                "http://localhost:8080/payments",
                paymentData
            );
            console.log("Payment submitted:", response.data);
            alert("Payment submitted successfully!");
        } catch (err) {
            console.error("Error submitting payment:", err);
            setError("Failed to submit payment. Please try again.");
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="container payment-card mt-5">
            <h3 className="text-center mb-4">Enter Payment Details</h3>
            <form className="p-4 shadow-lg bg-white rounded" onSubmit={handleSubmit} style={{ maxWidth: "600px", margin: "0 auto" }}>
                <div className="form-group mb-4">
                    <label htmlFor="paymentCategory">Payment Category</label>
                    <select
                        className="form-control"
                        id="paymentCategory"
                        value={paymentCategory}
                        onChange={handleCategoryChange}
                    >
                        <option value="" disabled>Select a category</option>
                        {Object.keys(paymentOptions).map((option) => (
                            <option key={option} value={option}>
                                {option}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="form-group mb-4">
                    <label htmlFor="paymentAmount">Payment Amount (LKR)</label>
                    <input
                        type="text"
                        className="form-control"
                        id="paymentAmount"
                        value={paymentAmount}
                        readOnly
                    />
                </div>

                <div className="form-group mb-4">
                    <label htmlFor="paymentType">Payment Type</label>
                    <select
                        className="form-control"
                        id="paymentType"
                        value={paymentType}
                        onChange={(e) => setPaymentType(e.target.value)}
                    >
                        <option value="Card">Card</option>
                        <option value="Cash">Cash</option>
                        <option value="Bank Transfer">Bank Transfer</option>
                    </select>
                </div>

                {paymentType === "Card" && (
                    <>
                        <div className="form-row mb-4">
                            <div className="form-group col-md-6">
                                <label htmlFor="cardholderName">Cardholder Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="cardholderName"
                                    name="cardholderName"
                                    value={formData.cardholderName}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>

                            <div className="form-group col-md-6">
                                <label htmlFor="cardNumber">Card Number</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="cardNumber"
                                    name="cardNumber"
                                    value={formData.cardNumber}
                                    onChange={handleInputChange}
                                    placeholder="XXXX XXXX XXXX XXXX"
                                    required
                                    minLength="16"
                                    maxLength="16"
                                />
                            </div>
                        </div>

                        <div className="form-row mb-4">
                            <div className="form-group col-md-6">
                                <label htmlFor="expiryDate">Expiry Date</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="expiryDate"
                                    name="expiryDate"
                                    value={formData.expiryDate}
                                    onChange={handleInputChange}
                                    placeholder="MM/YY"
                                    required
                                    pattern="\d{2}/\d{2}"
                                />
                            </div>

                            <div className="form-group col-md-6">
                                <label htmlFor="cvv">CVV</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="cvv"
                                    name="cvv"
                                    value={formData.cvv}
                                    onChange={handleInputChange}
                                    placeholder="XXX"
                                    required
                                    minLength="3"
                                    maxLength="3"
                                />
                            </div>
                        </div>

                        <div className="form-group text-center mb-4">
                            <img
                                src="https://img.icons8.com/color/48/000000/mastercard-logo.png"
                                alt="Mastercard"
                                className="mx-2"
                            />
                            <img
                                src="https://img.icons8.com/color/48/000000/visa.png"
                                alt="Visa"
                                className="mx-2"
                            />
                        </div>
                    </>
                )}

                {error && <p className="text-danger text-center">{error}</p>}

                <button
                    type="submit"
                    className="btn btn-primary btn-block mt-3"
                    disabled={isSubmitting}
                >
                    {isSubmitting ? "Submitting..." : "Submit Payment"}
                </button>
            </form>
        </div>
    );
};

export default PaymentCard;
