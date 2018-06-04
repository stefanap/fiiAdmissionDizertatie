import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Announcements.css";
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import Announcement from './Announcement.js'
import "./ManageUsers.css";
import User from './User.js'
import ReactTable from "react-table";
import "react-table/react-table.css";
import { ReactTableDefaults } from "react-table";

Object.assign(ReactTableDefaults, {
  defaultPageSize: 10,
  filterable:true,
  style: {}
});


const base64 = require('base-64');


class Announcements extends Component {

 constructor(props) {
    super(props);
    this.state = {
      users: []
    };
    this.getUsers();
  }

  changeUserRole(value){
    console.log(value);
  var id=value.id;
  var role;
  if(typeof value.role!=='undefined')
  role=value.role.roleName;
  if(role=='STANDARD_USER') role='ADMIN_USER';
  else role='STANDARD_USER';
  var props ={}
     var userRole={};
     userRole.roleName=role;
     console.log(userRole);
     var roles=[userRole];
     props.roles = roles;
     var user= new User(props);
     console.log(user);
 var token = base64.decode(localStorage.getItem('token'));
    let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body:JSON.stringify(user.state)
    }
    const API='https://localhost:8085/fii/users/updateRole/'+id;
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {
          console.log(data);
       if (typeof data.statusCode != 'undefined')
          {
            //ReactDOM.render(<p>Could not change user role. Please try again. </p>, document.getElementById('messageResult'));
          }
          else
             this.getUsers();
        }
      })
}

  getUsers()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/users';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        console.log(data);
        this.setState( { users:data})}) 
    }


  render() {
    return (
       <div>
      <ReactTable
          data={this.state.users}
          columns={[
            
                {
                  Header: "First Name",
                  accessor: "firstName"
                },
                {
                  Header: "Last Name",
                  accessor: "firstName"
                },
                {
                  Header: "User Name",
                  accessor: "username"
                },
                 {
                  Header: "Email",
                  accessor: "email"
                },
                {
                  Header: "Admission Status",
                  accessor: "admissionStatus"
                },
                {
                  Header: "Role",
                  id: "roleName",
                  accessor: d => d.role.roleName
                },
                 {
        id: 'edit',
        accessor: "row",
        Cell: ({value}) => (<button onClick={console.log('clicked value', value)}>Change Role</button>)
      }
            ]}
          defaultPageSize={10}
          className="-striped -highlight"
        />
      </div>

    )
  }
}


 
export default Announcements;