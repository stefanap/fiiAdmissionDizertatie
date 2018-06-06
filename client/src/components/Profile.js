import React, { Component } from "react";
import { Redirect } from 'react-router';
import { Form, Text } from 'react-form';
import "./Profile.css";
var qs = require('qs');
const base64 = require('base-64');

// function refreshToken(username,password,code){

//   let config = {
//     method: 'POST',
//     headers: { 'Content-Type':'application/x-www-form-urlencoded', 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')},
//     body: qs.stringify({
//       'grant_type': 'password',
//       'username': username,
//       'password': password,
//       'verificationCode': code
//     })
//   }

//   const tokenAPI = 'https://localhost:8085/oauth/token'

//   fetch(tokenAPI, config)
//   .then(response =>
//     response.json()) .then((data) => { 
//       console.log(data); localStorage.setItem('token', data.access_token)})     

//   }

    class Profile extends Component {
      constructor(props) {
        super(props);
        this.state = {
      descriptionText: 'Please confirm your admission status below',
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
        if(this.status.value==1) newStatus='Confirmed';
        if(this.status.value==2) newStatus='Withdrawal';
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
  <div class="username">username: {username}</div>
  <div class="standardField">name: {firstName}{' '}{lastName}</div>
  <div class="standardField">email: {email}</div>
  <div class="bio">
  {role}
  </div>
  <div class="description">{this.state.descriptionText}
  </div>
  <Form>
  <form class="statusForm">
  <select ref={(ref) => this.status = ref}>
  <option value="0">Confirmed</option>
  <option value="1">Withdrawal</option>
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


