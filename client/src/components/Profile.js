import React, { Component } from "react";
import { Redirect } from 'react-router';
import { Form, Text } from 'react-form';
import "./Profile.css";
import moment from 'moment';
import LocalizedStrings from 'react-localization';
var qs = require('qs');
const base64 = require('base-64');
let strings = new LocalizedStrings({
 en:{
   confirm: "Please confirm your admission status below",
   pending:"Pending",
   withdrawal:"Withdrawal",
   username:"Username",
   name:"Name"
 },
 ro: {
 confirm: "Va rugam confirmati statusul inscrierii la admitere",
 pending:"In asteptare",
 withdrawal:"Retras",
 username:"Nume utilizator",
 name:"Nume"
 }
});


    class Profile extends Component {
      constructor(props) {
        super(props);
        var language=localStorage.getItem('language');
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');
    this.state = {
      announcements: [],
      showModal: false,
      Date: moment(),
      showButton : false
    };
        this.state = {
      user: JSON.parse(localStorage.getItem('user'))
    };
if(localStorage.getItem('user')==null)
      this.redirect();
      }

       redirect() {
        this.props.history.push("/login")
    }

   updateStatus(user,token){
    let config = {
      method: 'PUT',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body: JSON.stringify(user)
    }
    const API='https://localhost:8085/users'
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {
          this.submitButton.disabled='disabled';
          //this.buttonStyle = {'background-color':'gray'};
        }
      })
    }

      handleSubmit(e){
        e.preventDefault();
        var token =base64.decode(localStorage.getItem('token'));
        token =  localStorage.getItem('token');
        var newStatus;
        if(this.status.value==1) newStatus='PENDING';
        if(this.status.value==2) newStatus='WITHDRAWAL';
        if(typeof newStatus!="undefined"){
          this.updateStatus(this.state.user,token);
      }}

        render() {
          var role;
          var username;
          var firstName;
          var lastName;
          var email;
          if(this.state.user!=null&&this.state.user.role) 
            {role=this.state.user.role.roleName;
              username=this.state.user.username;
              firstName=this.state.user.firstName;
              lastName=this.state.user.lastName;
              email=this.state.user.email;
            }
         return (
  <div>
  <div class="user-profile">
  <img class="avatar" src="https://cdn2.iconfinder.com/data/icons/reading/512/round-level-phd-avatar-university-student-512.png" alt="Ash" />
  <div class="username">{strings.username}: {username}</div>
  <div class="standardField">{strings.name}: {firstName}{' '}{lastName}</div>
  <div class="standardField">Email: {email}</div>
  <div class="bio">
  {role}
  </div>
  <div class="description">{strings.confirm}
  </div>
  <Form>
  <form class="statusForm">
  <select ref={(ref) => this.status = ref}>
  <option value="0">{strings.pending}</option>
  <option value="1">{strings.withdrawal}</option>
  </select>
  <button type="submit" ref={(ref) => this.submitButton = ref} onClick={this.handleSubmit.bind(this)} className="btn btn-primary">
  Submit
  </button>
  </form>
  </Form>
  </div>
  </div>
  )
  }
}

export default Profile;


