import { Link } from "react-router-dom";

export function MainContent() {
  const cardStyle = {
    width: "300px",
    height: "180px",
    backgroundColor: "#fce4ec",
    color: "#333",
    borderRadius: "15px",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)",
    transition: "transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out",
    textAlign: "center",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    cursor: "pointer",
    margin: "20px",
  };

  const cardContainerStyle = {
    backgroundColor: "#ffffff",
    borderRadius: "15px",
    padding: "20px",
    boxShadow: "0px 4px 12px rgba(0, 0, 0, 0.1)",
    marginTop: 20,
    minHeight: "calc(100vh - 140px)",
  };

  const mainComponentStyle = {
    backgroundColor: "#f0f0f0",
    minHeight: "100vh",
    paddingBottom: "20px",
    marginTop: "80px",
    overflowY: "auto",
  };

  return (
    <div
      style={{
        marginLeft: "270px",
        padding: "20px",
        width: "100%",
        ...mainComponentStyle,
      }}
    >
      <div style={cardContainerStyle}>
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
          Quick Access
        </h2>
        <div
          className="cardContainer"
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexWrap: "wrap",
            marginTop: "40px",
          }}
        >
          <Link to={"payment/dashboard"} style={{ textDecoration: "none" }}>
            <div
              className="card div-shadow"
              style={cardStyle}
              onMouseEnter={(e) =>
                (e.currentTarget.style.transform = "scale(1.05)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.transform = "scale(1)")
              }
            >
              <h1 style={{ fontSize: "32px" }}>Payments</h1>
            </div>
          </Link>

          <Link to={"service/servicelist"} style={{ textDecoration: "none" }}>
            <div
              className="card div-shadow"
              style={cardStyle}
              onMouseEnter={(e) =>
                (e.currentTarget.style.transform = "scale(1.05)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.transform = "scale(1)")
              }
            >
              <h1 style={{ fontSize: "32px" }}>Services</h1>
            </div>
          </Link>

          <Link to={"appointments"} style={{ textDecoration: "none" }}>
            <div
              className="card div-shadow"
              style={cardStyle}
              onMouseEnter={(e) =>
                (e.currentTarget.style.transform = "scale(1.05)")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.transform = "scale(1)")
              }
            >
              <h1 style={{ fontSize: "32px" }}>Appointments</h1>
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
}
