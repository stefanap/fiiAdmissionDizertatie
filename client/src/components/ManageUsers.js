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
const base64 = require('base-64');

class Announcements extends Component {


 constructor(props) {
    super(props);
    this.state = {
      users: []
    };
    this.getUsers();
  }

  changeUserRole(id,role){
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
<table>
      <tr>
    <th>Username</th>
    <th>First Name</th>
    <th>Last name</th>
    <th>Email</th>
    <th>Status</th>
    <th>Role Name</th>
  </tr>
  {
     this.state.users.map((user, i) => {
      console.log(user);
       return <tr key={i}>
       <td>{user.username}</td>
       <td>{user.firstName}</td>
       <td>{user.lastName}</td>
       <td>{user.email}</td>
       <td>{user.admissionStatus}</td>
       <td>{user.roles[0].roleName} <button class='changeRoleButton' onClick={() => { this.changeUserRole(user.id,user.roles[0].roleName) }}>Change</button></td>
       </tr>
     })}
   </table>
   </div>
    )
  }
}


 
export default Announcements;