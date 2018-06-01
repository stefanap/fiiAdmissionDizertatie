import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Profile.css";
var qs = require('qs');

function refreshToken(username,password,code){

  let config = {
    method: 'POST',
    headers: { 'Content-Type':'application/x-www-form-urlencoded', 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')},
    body: qs.stringify({
      'grant_type': 'password',
      'username': username,
      'password': password,
      'verificationCode': code
    })
  }

  const tokenAPI = 'https://localhost:8085/oauth/token'

  fetch(tokenAPI, config)
  .then(response =>
    response.json()) .then((data) => { 
      console.log(data); localStorage.setItem('token', data.access_token)})     

  }


  function register(data,token){
    console.log('register')
    console.log(token);
    let config = {
      method: 'PUT',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body: JSON.stringify(data)
    }
    const API='https://localhost:8085/users'
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {
          console.log(data)
        }


      })
    }
    class Profile extends Component {
      constructor(props) {
        super(props);
        this.state = {
      descriptionText: 'Please confirm your admission status below'
    };
      }

      handleSubmit(e){
        e.preventDefault();
        var token =localStorage.getItem('token');
        if(token=='undefined'||token==null)
          refreshToken(localStorage.getItem('username'),localStorage.getItem('password'),localStorage.getItem('code'));
        token =  localStorage.getItem('token');
        var newStatus;
        if(this.status.value==1) newStatus='Confirmed';
        if(this.status.value==2) newStatus='Withdrawal';
        console.log(newStatus);
        if(typeof newStatus!=="undefined"){
        const GETAPI = 'https://localhost:8085/users/user/'+localStorage.getItem('username');
        let config = {
          method: 'GET',
        }

        fetch(GETAPI, config)
        .then(response =>
          response.json()) .then((data) => { 
            data.status=newStatus;
            var userdata=data;
            register(userdata,token);
          })

        }
      }

        render() {
          return (
  <div>
  <h1 class="title-pen"> User Profile</h1>
  <div class="user-profile">
  <img class="avatar" src="https://cdn2.iconfinder.com/data/icons/reading/512/round-level-phd-avatar-university-student-512.png" alt="Ash" />
  <div class="username">{localStorage.getItem('username')}</div>
  <div class="bio">
  {localStorage.getItem('role')}
  </div>
  <div class="description">{this.state.descriptionText}
  </div>
  <Form>
  <form id="statusForm">
  <select ref={(ref) => this.status = ref}>
  <option value="0">Select admission status:</option>
  <option value="1">Confirmed</option>
  <option value="2">Withdrawal</option>
  </select>
  <button type="submit" onClick={this.handleSubmit.bind(this)} className="btn btn-primary">
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


