import axios from "axios";
import React, { useEffect, useState } from "react";

export default function AddAppointment() {
  const [appointmentData, setAppointmentData] = useState({
    patientId: {
      customerId: "671146ca4c78986afe93a886",
    },
    serviceId: {
      serviceId: "",
    },
    doctorId: {
      doctorId: "",
    },
    appointmentDateTime: "",
    appointmentDesc: "",
    appointmentStatus: "pending",
  });

  const [services, setServices] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const [serviceDoctors, setServiceDoctors] = useState([]);
  const [selectedService, setSelectedService] = useState();
  const [dateError, setDateError] = useState(false);

  useEffect(() => {
    axios
      .get("http://localhost:8080/services")
      .then((res) => {
        setServices(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:8080/doctors")
      .then((res) => {
        setDoctors(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const handleSubmit = () => {};
  const handleInputChange = (event) => {
    setAppointmentData((prev) => {
      return {
        ...prev,
        appointmentDesc: event.target.value,
      };
    });
  };

  const handleDateInput = (date) => {
    setAppointmentData((prev) => {
      return {
        ...prev,
        appointmentDateTime: new Date(date).toISOString(),
      };
    });
  };

  const handleCategoryChange = (event) => {
    const { id, value } = event.target;

    if (id == "serviceId") {
      findSelectedService(value);
    }

    // console.log("value", value);
    setAppointmentData((prev) => {
      return {
        ...prev,
        [id]: {
          [id]: value,
        },
      };
    });
  };

  const getDoctorNameById = (doctor, id) => {
    return doctor.doctorId == id;
  };

  const findSelectedService = (id) => {
    const selectedService = services.find((serv) => {
      return serv.serviceId == id;
    });
    // console.log("selectedService", selectedService);
    setServiceDoctors(selectedService.selectedDoctors);
    setSelectedService(selectedService);
  };

  // console.log(appointmentData);

  function addDays(date, day) {
    let new_date = new Date(date.getTime());
    new_date.setDate(new_date.getDate() + day);
    return new_date;
  }

  function getDates(k, day) {
    const day_num = [
      "sunday",
      "monday",
      "tuesday",
      "wednesday",
      "thursday",
      "friday",
      "saturday",
    ].indexOf(day.toLowerCase()); // converting day name to number

    let new_day = new Date();
    while (new_day.getDay() !== day_num) {
      new_day = addDays(new_day, 1);
    }

    return Array(k)
      .fill()
      .map((_, index) => addDays(new_day, index * 7).toLocaleDateString());
  }

  const handleDateChange = (event) => {
    const { value } = event.target;

    let newDate = new Date(value).getDay();
    let day;
    switch (newDate) {
      case 0:
        day = "Sunday";
        break;
      case 1:
        day = "Monday";
        break;
      case 2:
        day = "Tuesday";
        break;
      case 3:
        day = "Wednesday";
        break;
      case 4:
        day = "Thursday";
        break;
      case 5:
        day = "Friday";
        break;
      case 6:
        day = "Saturday";
        break;
      default:
        day = false;
    }
    // console.log("newDate", day);

    const isAvailable = selectedService.selectedDays.find((sday) => {
      // console.log("sday", sday);
      // console.log("day", day);
      return sday == day;
    });
    // console.log("isAvailable", isAvailable);

    if (isAvailable) {
      handleDateInput(value);
      setDateError(false);
    } else {
      setDateError(true);
    }
  };

  return (
    <div className="container payment-card pt-5">
      <h3 className="text-center mb-4">Add an Appointment</h3>
      <form
        className="p-4 shadow-lg bg-white rounded"
        onSubmit={handleSubmit}
        style={{ maxWidth: "600px", margin: "0 auto" }}
      >
        <div className="form-group mb-4">
          <label htmlFor="serviceId">Service Type</label>
          <select
            className="form-control"
            id="serviceId"
            value={appointmentData.serviceId.serviceId}
            onChange={handleCategoryChange}
          >
            <option value="" disabled>
              Select a category
            </option>
            {services.map((service) => (
              <option key={service.serviceId} value={service.serviceId}>
                {service.serviceName}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group mb-4">
          <label htmlFor="serviceId">Select a Doctor</label>
          <select
            className="form-control"
            id="doctorId"
            value={appointmentData.doctorId.doctorId}
            onChange={handleCategoryChange}
          >
            <option value="" disabled>
              Select a category
            </option>
            {serviceDoctors &&
              serviceDoctors.map((sDoctor) => (
                <option key={sDoctor} value={sDoctor}>
                  {
                    doctors.find((doctor) => getDoctorNameById(doctor, sDoctor))
                      .doctorName
                  }
                </option>
              ))}
          </select>
        </div>
        {selectedService && (
          <>
            <div className="form-group mb-4">
              <label htmlFor="">Available Dates</label>
              {selectedService &&
                selectedService.selectedDays.map((day) => <p>{day}</p>)}
            </div>

            <div className="form-group mb-4">
              <label htmlFor="paymentAmount">Select a Date</label>
              <input
                type="Date"
                data-provide="datepicker"
                className="form-control datepicker"
                id="paymentAmount"
                onChange={handleDateChange}
              />
              {dateError && (
                <p style={{ color: "red" }}>
                  The selected date is not available. Please choose a date
                  according to the available dates
                </p>
              )}
            </div>
          </>
        )}

        <div className="form-group mb-4">
          {/*<label htmlFor="paymentType">Payment Type</label>
           <select
            className="form-control"
            id="paymentType"
            value={paymentType}
            onChange={(e) => setPaymentType(e.target.value)}
          >
            <option value="Card">Card</option>
            <option value="Cash">Cash</option>
            <option value="Bank Transfer">Bank Transfer</option>
          </select> */}
        </div>

        <div className="form-group mb-4">
          <label htmlFor="cardholderName">Appointment Desciption</label>
          <input
            type="text"
            className="form-control"
            id="appointmentDesc"
            name="appointmentDesc"
            value={appointmentData.appointmentDesc}
            onChange={handleInputChange}
            required
          />
        </div>

        {/* {error && <p className="text-danger text-center">{error}</p>} */}

        <button
          type="submit"
          className="btn btn-primary btn-block mt-3"
          disabled={false}
          style={{ alignSelf: "center" }}
        >
          Book
        </button>
      </form>
    </div>
  );
}
