import React from "react";
import { Outlet } from "react-router-dom";
import SideBar from "./SideBar";
import AdminSidebar from "./AdminSidebar";

export default function AdminLayout(props) {
  return (
    <>
      {/* <SideBar page={props.page} menu={props.menu} user={"admin"}/> */}
      <AdminSidebar />

      <div
        style={{
          flex: "auto",
          position: "relative",
          height: "100vh",
          marginLeft: "300px",
        }}
      >
        <Outlet />
      </div>
    </>
  );
}
