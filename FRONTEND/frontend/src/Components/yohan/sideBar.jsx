import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faDashboard,
  faClipboardList,
  faCreditCard,
  faCogs,
} from "@fortawesome/free-solid-svg-icons";

export function Sidebar() {
  const sidebarStyle = {
    height: "95vh",
    width: "250px",
    position: "fixed",
    top: "20px",
    left: "20px",
    backgroundColor: "#333333",
    padding: "20px",
    color: "#ffffff",
    zIndex: 1000,
    textAlign: "left",
    borderRadius: "15px",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)",
  };

  const linkStyle = {
    textDecoration: "none",
    color: "#ffffff",
    display: "flex",
    alignItems: "center",
    padding: "15px 10px",
    fontSize: "18px",
    margin: "5px 0",
  };

  const imageStyle = {
    width: "50px",
    borderRadius: "50%",
    marginBottom: "5px",
    marginRight: "10px",
  };

  const adminNameStyle = {
    fontWeight: "bold",
    fontSize: "16px",
    margin: 0,
  };

  const adminPositionStyle = {
    fontWeight: "normal",
    fontSize: "12px",
    margin: 0,
  };

  const adminInfoStyle = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    width: "90%",
  };

  const adminTextStyle = {
    display: "flex",
    flexDirection: "column",
    textAlign: "left",
  };

  const headingStyle = {
    fontSize: "24px",
    fontWeight: "bold",
    marginTop: "20px",
  };

  return (
    <div style={sidebarStyle}>
      {/* Admin Info */}
      <div style={adminInfoStyle}>
        <img
          src="https://cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
          alt="Admin"
          style={imageStyle}
        />
        <div style={adminTextStyle}>
          <h2 style={adminNameStyle}>Asiri Chathurika</h2>
          <p style={adminPositionStyle}>Super Admin</p>
        </div>
      </div>

      {/* Admin Panel heading and navigation */}
      <h3 style={headingStyle}>Admin Panel</h3>
      <hr style={{ border: "1px solid #fff", width: "100%" }} />

      {/* Navigation Links */}
      <nav>
        <Link to={"/admin"} style={linkStyle}>
          <FontAwesomeIcon icon={faDashboard} style={{ marginRight: "10px" }} />
          Dashboard
        </Link>
        <Link to={"appointments"} style={linkStyle}>
          <FontAwesomeIcon
            icon={faClipboardList}
            style={{ marginRight: "10px" }}
          />
          Appointments
        </Link>
        <Link to={"payment/dashboard"} style={linkStyle}>
          <FontAwesomeIcon
            icon={faCreditCard}
            style={{ marginRight: "10px" }}
          />
          Payments
        </Link>
        <Link to={"service/servicelist"} style={linkStyle}>
          <FontAwesomeIcon icon={faCogs} style={{ marginRight: "10px" }} />
          Services
        </Link>
      </nav>
    </div>
  );
}
