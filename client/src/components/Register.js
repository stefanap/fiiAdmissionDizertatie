import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Register.css";
import ReactDOM from 'react-dom';
const API = 'https://localhost:8085/users'
const base64 = require('base-64');
var qs = require('qs');

// function refreshToken(){
// let config = {
//       method: 'POST',
//       headers: { 'Content-Type':'application/x-www-form-urlencoded', 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')},
//       body: qs.stringify({
//         'grant_type': 'password',
//         'username': 'admin.admin',
//         'password': 'jwtpass'
//       })
//     }

//      fetch(API, config)
//     .then(response =>
//       response.json()) .then((data) => { 
//           localStorage.setItem('token', data.access_token)})     

//     }
    

    function register(username,firstname,lastname,password,email){
    	console.log('register')
let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json'},
      body: JSON.stringify({
    "username": username,
    "password": password,
    "firstName": firstname,
    "lastName": lastname,
    "email": email,
    "admissionStatus": null,
    "secret": "K3WAGWHIGNZKZCDN",
    "roles": [
    ]
      })
    }

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
      	console.log(data);
          //if (response.ok)
          {
            var id= data.id;
            console.log(id)
          	getQR(id)
          }


    })
}


function getQR(id)
    {
      const QRAPI = 'https://localhost:8085/users/'+id+'/qr-code'
      let config = {
      method: 'GET',
      }

    fetch(QRAPI, config)
   .then(response =>
      response.text()) .then((data) => { 
         ReactDOM.render(<h1><img id='qr' src={data}></img></h1>, document.getElementById('QRCode'))}) 

    }

class Register extends Component {

  constructor(props) {
    super(props);

    this.state = {
      users: [], data: []
    };
  }

 handleSubmit(e){

      this.registerForm.className='hidden';
      e.preventDefault();
 	 var username = this.uname.value;
 	 var firstname = this.firstname.value;
     var lastname = this.lastname.value;
     var password = this.password.value;
     var email = this.email.value;
     register(username,firstname,lastname,password,email);

    // var token =localStorage.getItem('token');
    // if(token==null)
    //   refreshToken();
    // token =  localStorage.getItem('token');



    // let config = {
    //   method: 'POST',
    //   headers: { 'Content-Type':'application/json', 'Authorization': 'Bearer ${localStorage.getItem('token')}')},
    //   body: qs.stringify({
    //     'grant_type': 'password',
    //     'username': 'admin.admin',
    //     'password': 'jwtpass'
    //   })
    // }


    // fetch(API, config)
    // .then(response =>
    //   response.json()) .then((data) => { 
    //     if(data.access_token !=null) {
    //       localStorage.setItem('token', data.access_token);
    //     this.setState( { data });}
    //     else
    //     {

    //     })
 }

  render() {

    return (
    	<div>
      <Form>
  {formApi => (
    <form class="form-style-5" ref={(ref) => this.registerForm = ref}>
   
<fieldset>
<legend><span class="number">1</span> Candidate Info</legend>
<input type="text" ref={(ref) => this.firstname = ref} name="field1" placeholder="First Name *"/>
<input type="text" ref={(ref) => this.lastname = ref} name="field2" placeholder="Last Name *"/>
<input type="email" ref={(ref) => this.email = ref} name="field3" placeholder="Email *"/>
<input type="text" ref={(ref) => this.uname = ref} name="field4" placeholder="Username*"/>
<input type="password" ref={(ref) => this.password = ref} name="field5" placeholder="Password *"/>
</fieldset>
<input type="submit" onClick={this.handleSubmit.bind(this)} value="Apply" />









      
    </form>
  )}
</Form>
<div id="QRCode" ref={(ref) => this.qr = ref}>
    </div>
    </div>
    );
  }
}
 
export default Register;

