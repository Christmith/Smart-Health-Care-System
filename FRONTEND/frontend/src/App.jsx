// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
//
// function App() {
//   const [count, setCount] = useState(0)
//
//   return (
//     <>
//       <div>
//         <a href="https://vitejs.dev" target="_blank">
//           <img src={viteLogo} className="logo" alt="Vite logo" />
//         </a>
//         <a href="https://react.dev" target="_blank">
//           <img src={reactLogo} className="logo react" alt="React logo" />
//         </a>
//       </div>
//       <h1>Vite + React</h1>
//       <div className="card">
//         <button onClick={() => setCount((count) => count + 1)}>
//           count is {count}
//         </button>
//         <p>
//           Edit <code>src/App.jsx</code> and save to test HMR
//         </p>
//       </div>
//       <p className="read-the-docs">
//         Click on the Vite and React logos to learn more
//       </p>
//     </>
//   )
// }
//
// export default App

import { useState } from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import AdminLayout from "./Components/common/AdminLayout.jsx";
import AdminPanel from "./Components/common/AdminPanel.jsx";
import PaymentCard from "./Components/asiri/PaymentCard.jsx";
import PayList from "./Components/asiri/PayList.jsx";
import Dashboard from "./Components/asiri/Dashboard.jsx";
import AppointmentList from "./Components/asiri/AppointmentList.jsx";
import ServiceList from "./Components/yohan/ServiceList.jsx";
import Pay from "./Components/asiri/Pay.jsx";
import CustomerLayout from "./components/common/CustomerLayout";
import Profile from "./Components/chamath/Profile.jsx";
import Appointments from "./Components/Kalindu/Appointments.jsx";
import CustomerPayments from "./Components/asiri/CustomerPayments.jsx";
import AddAppointment from "./Components/Kalindu/AddAppointment.jsx";
import CustomerSignup from "./Components/chamath/customerSignup.jsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Pay />} />
        <Route path="/admin" element={<AdminPanel />} />
        <Route
          element={
            <AdminLayout
              page={"Payment"}
              menu={["Dashboard", "Recived Payments", "Appointments"]}
            ></AdminLayout>
          }
        >
          <Route path="admin/payment/dashboard" element={<Dashboard />} />
          <Route path="admin/payment/recivedpayments" element={<PayList />} />
          <Route
            path="admin/payment/appointments"
            element={<AppointmentList />}
          />
          <Route path="admin/service/servicelist" element={<ServiceList />} />
        </Route>

        {/* <Route
          element={
            <AdminLayout page={"Service"} menu={["Service List"]}></AdminLayout>
          }
        >
          <Route path="admin/service/servicelist" element={<ServiceList />} />
        </Route> */}

        <Route
          element={
            <CustomerLayout
              page={"patient"}
              menu={["Profile", "Appointments", "Payments"]}
            ></CustomerLayout>
          }
        >
          <Route path="user/" element={<Profile />} />
          <Route path="user/patient/profile" element={<Profile />} />
          <Route path="user/patient/appointments" element={<Appointments />} />
          <Route
            path="user/patient/appointments/add"
            element={<AddAppointment />}
          />
          <Route path="user/patient/payments" element={<PaymentCard />} />
        </Route>

        {/*<Route*/}
        {/*    element={*/}
        {/*        <AdminLayout*/}
        {/*            page={"Bin"}*/}
        {/*            menu={["Customers", "Schedules"]}*/}
        {/*        ></AdminLayout>*/}
        {/*    }*/}
        {/*>*/}
        {/*    <Route path="admin/bin/customers" element={<Customers />} />*/}
        {/*    <Route path="admin/bin/schedules" element={<Schedules />} />*/}
        {/*</Route>*/}

        {/*<Route*/}
        {/*    element={*/}
        {/*        <AdminLayout page={"Profile"} menu={["Report"]}></AdminLayout>*/}
        {/*    }*/}
        {/*>*/}
        {/*    <Route path="admin/profile/report" element={<ProfileReport />} />*/}
        {/*</Route>*/}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
