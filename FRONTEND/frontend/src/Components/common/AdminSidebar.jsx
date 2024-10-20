import React from "react";
import { Link } from "react-router-dom";
import Arrow from "../../assets/Arrow5.png";
import DELogo from "../../assets/logo2.png";
// Import Font Awesome icons
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faDashboard,
  faClipboardList,
  faCreditCard,
  faCogs,
} from "@fortawesome/free-solid-svg-icons";

export default function SideBar() {
  // Define common card style as an object
  const cardStyle = {
    width: "300px",
    height: "180px",
    backgroundColor: "#fce4ec", // Light pink from the screenshot
    color: "#333", // Dark text color
    borderRadius: "15px",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)", // Softer shadow
    transition: "transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out",
    textAlign: "center",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    cursor: "pointer",
    margin: "20px", // Add margin to space out the cards
  };

  // White box for cards
  const cardContainerStyle = {
    backgroundColor: "#ffffff", // White background for the container
    borderRadius: "15px", // Rounded corners
    padding: "20px", // Padding around the cards
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)",
    marginTop: 20, // Soft shadow around the container
    minHeight: "calc(100vh - 140px)", // Adjust height to fit the screen
  };

  // Sidebar styles, with margin and border-radius added
  const sidebarStyle = {
    height: "95vh",
    width: "250px",
    position: "fixed",
    top: "20px",
    left: "20px", // Adds some margin from the left
    backgroundColor: "#333333", // Dark background like the screenshot
    padding: "20px",
    color: "#ffffff",
    zIndex: 1000,
    textAlign: "left", // Align text to the left
    borderRadius: "15px", // Adds border radius to match the screenshot
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)", // Soft shadow around the sidebar
  };

  // Sidebar link style
  const linkStyle = {
    textDecoration: "none",
    color: "#ffffff",
    display: "flex",
    alignItems: "center", // Align items center vertically
    padding: "15px 10px",
    fontSize: "18px",
    margin: "5px 0", // Space between links
  };

  // Image styles for the top of the sidebar and top bar
  const imageStyle = {
    width: "50px", // Smaller size for the sidebar image
    borderRadius: "50%", // Make the image circular
    marginBottom: "5px", // Reduced margin to reduce the gap
    marginRight: "10px", // Add margin to space the image from the text
  };

  // Top bar image style
  const topBarImageStyle = {
    width: "50px", // Smaller size for the top bar image
    borderRadius: "50%", // Keep it circular
    marginRight: "20px", // Add margin to space the image from the title
  };

  // Top bar style (fixed positioning)
  const topBarStyle = {
    backgroundColor: "#333333", // Dark background color for the top bar
    width: "calc(100% - 310px)", // Adjust width to account for the sidebar width
    height: "80px",
    color: "#fff",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.2)",
    fontSize: "36px",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between", // Space between heading and image/title
    borderRadius: "15px", // Border radius for rounded corners
    paddingLeft: "30px", // Padding on the left to align content
    position: "fixed", // Fixed position for the top bar
    top: 0,
    left: "290px", // Adjust to match the sidebar + margin
    marginBottom: "20px", // Add margin at the bottom to separate from the content
    marginTop: "20px", // Add margin at the top to separate from the content
    zIndex: 999, // Ensure it stays above other content
  };

  // Admin name and position style
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

  // Admin container style (flex with space-between)
  const adminInfoStyle = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    width: "90%",
  };

  const adminTextStyle = {
    display: "flex",
    flexDirection: "column", // Stack name and position in two rows
    textAlign: "left", // Align text to the left
  };

  // Admin Panel heading style
  const headingStyle = {
    fontSize: "24px",
    fontWeight: "bold",
    marginTop: "20px", // Add some margin to separate from the admin info
  };

  // Main component background style
  const mainComponentStyle = {
    backgroundColor: "#f0f0f0", // Light gray background
    minHeight: "100vh", // Ensures it covers the full height
    paddingBottom: "20px", // Padding at the bottom to avoid overflow
    marginTop: "80px", // Account for the fixed top bar
    overflowY: "auto", // Enable vertical scrolling
  };

  // Main container style to cover the full area behind the sidebar
  const adminPanelContainerStyle = {
    display: "flex",
    backgroundColor: "#f0f0f0", // Same light gray color as the main component
    minHeight: "100vh", // Ensures it covers the full height
  };
  //   let [url, setUrl] = React.useState(window.location.pathname.toString());
  //   console.log(url);
  //
  //   const menuItems = menu.map((item) => {
  //     const itemUrl = `/${user}/${page.toLowerCase()}/${item
  //       .toLowerCase()
  //       .split(" ")
  //       .join("")}`;
  //     let selectedStyle;
  //     if (url === itemUrl) {
  //       // Apply styles for selected item (white background and black font color)
  //       selectedStyle = "nav-link active fs-5 bg-white text-black";
  //     } else {
  //       // Apply styles for unselected items (white font color)
  //       selectedStyle = "nav-link text-white fs-5";
  //     }
  //     return (
  //         <li className="nav-item" key={item}>
  //             <Link
  //                 to={itemUrl}
  //                 className={selectedStyle}
  //                 aria-current="page"
  //                 onClick={() => setUrl(itemUrl)}
  //             >
  //                 <svg className="bi me-2" width="16" height="16">
  //                     <use xlinkHref="#home"></use>
  //                 </svg>
  //                 {item}
  //             </Link>
  //         </li>
  //     );
  //   });

  return (
    <>
      <div style={sidebarStyle}>
        {/* Add healthcare image and admin details to the top */}
        <div style={adminInfoStyle}>
          <img
            src="https://cms-assets.tutsplus.com/uploads/users/810/profiles/19338/profileImage/profile-square-extra-small.png"
            alt="Healthcare"
            style={imageStyle}
          />
          <div style={adminTextStyle}>
            <h2 style={adminNameStyle}>Asiri Alwis</h2>
            <p style={adminPositionStyle}>Super Admin</p>
          </div>
        </div>

        {/* Admin Panel heading and horizontal line */}
        <h3 style={headingStyle}>Admin Panel</h3>
        <hr style={{ border: "1px solid #fff", width: "100%" }} />

        {/* Navigation Links */}
        <nav>
          <Link to={"admin/payment/dashboard"} style={linkStyle}>
            <FontAwesomeIcon
              icon={faDashboard}
              style={{ marginRight: "10px" }}
            />
            Dashboard
          </Link>
          <Link to={"admin/payment/appointments"} style={linkStyle}>
            <FontAwesomeIcon
              icon={faClipboardList}
              style={{ marginRight: "10px" }}
            />
            Appointments
          </Link>
          <Link to={"admin/payment/recivedpayments"} style={linkStyle}>
            <FontAwesomeIcon
              icon={faCreditCard}
              style={{ marginRight: "10px" }}
            />
            Payments
          </Link>
          <Link to={"admin/service/servicelist"} style={linkStyle}>
            <FontAwesomeIcon icon={faCogs} style={{ marginRight: "10px" }} />
            Services
          </Link>
        </nav>
      </div>
    </>
  );
}
