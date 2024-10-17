import React from "react";
import ProfilePic from "../../assets/Ellipse7.png"


export default function AdminHeader({pageName}) {

    const date = Date().split(' ');
    const dateString = `${date[0]} ${date[1]} ${date[2]}, ${date[3]}`;
    const user = 'Asiri Alwis'

    return(

        <div className="d-flex border-bottom border-dark border-3" style={{backgroundColor: '#EEEEEE', '--bs-border-opacity': .5}}>
            <p className="mt-0 mb-0 pt-2 fs-2 flex-grow-2 text-center" style={{width: '350px'}} >{pageName}</p>
            <h6 className="mt-4 text-muted flex-grow-2 ps-3" style={{width: '350px'}} >{dateString}</h6>
            <div className="mt-2 d-flex flex-grow-1 justify-content-end pe-4" style={{width: '350px'}} >
                <div>
                    <h6 className="mt-1 pe-3 mb-0 clickable">{user}</h6>
                    <p className="pt-0 text-muted clickable" style={{fontSize: '13px'}}>View Profile</p>
                </div>
                <img className="clickable" src={ProfilePic} alt="user pic" width="40" height="38" />
            </div>
        </div>

    )

}