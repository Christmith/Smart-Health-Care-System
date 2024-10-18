import React from 'react';
import { Outlet } from 'react-router-dom';
import SideBar from './SideBar';

export default function CustomerLayout(props) {

    return(
        <>
            <SideBar page={props.page} menu={props.menu} user={"user"}/>

            <div style={{flex: 'auto', position: 'relative', height: '100vh', marginLeft: '300px'}}>
                <Outlet/>
            </div>


        </>
    )
}