import React from 'react'
import AppointmentCharts from './AppointmentCharts'
import AdminHeader from "../common/AdminHeader";
import DashboardCards from './DashboardCards';

function Dashboard() {
  return (
    <div>
      <AdminHeader pageName={'Dashboard'} />
      <DashboardCards/>
      <AppointmentCharts/>
    </div>
    
  )
}

export default Dashboard