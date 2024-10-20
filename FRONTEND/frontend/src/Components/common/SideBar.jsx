import React from "react";
import { Link } from "react-router-dom";
import Arrow from "../../assets/Arrow5.png";
import DELogo from "../../assets/logo2.png";

export default function SideBar({ page, menu, user }) {
  let [url, setUrl] = React.useState(window.location.pathname.toString());
  console.log(url);

  const menuItems = menu.map((item) => {
    const itemUrl = `/${user}/${page.toLowerCase()}/${item
      .toLowerCase()
      .split(" ")
      .join("")}`;
    let selectedStyle;
    if (url === itemUrl) {
      // Apply styles for selected item (white background and black font color)
      selectedStyle = "nav-link active fs-5 bg-white text-black";
    } else {
      // Apply styles for unselected items (white font color)
      selectedStyle = "nav-link text-white fs-5";
    }
    return (
      <li className="nav-item" key={item}>
        <Link
          to={itemUrl}
          className={selectedStyle}
          aria-current="page"
          onClick={() => setUrl(itemUrl)}
        >
          <svg className="bi me-2" width="16" height="16">
            <use xlinkHref="#home"></use>
          </svg>
          {item}
        </Link>
      </li>
    );
  });

  return (
    <div
      className="d-flex flex-column flex-shrink flex-shrink-0 p-3 text-white"
      style={{
        width: "300px",
        minHeight: "100vh",
        position: "fixed",
        backgroundColor: "#333333",
      }}
    >
      <Link
        to="/admin"
        className="d-flex justify-content-center align-items-center mb-3 mt-3 mb-md-0 text-white text-decoration-none"
      >
        <img
          width="60"
          height="60"
          style={{ objectFit: "cover", borderRadius: "30px" }}
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1r104bamd1_kCdG3cElkuPL7N3HJjjw1frNaE46aWp8tX2EjLvxCiFhzK2rYVm9OiThw&usqp=CAU"
          alt="Company Logo"
        />
      </Link>
      <hr />
      <div>
        <Link to="/admin">
          <img
            className="bi me-5 ms-3"
            width="20"
            height="20"
            src={Arrow}
            alt="back"
          ></img>
        </Link>
        <span className="fs-2">{page}</span>
      </div>
      <br />
      <br />
      <ul className="nav nav-pills flex-column mb-auto">{menuItems}</ul>
      <button>Log Out</button>
      <hr />
    </div>
  );
}
