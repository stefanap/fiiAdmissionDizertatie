import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Register.css";
import "./Login.css";
import ReactDOM from 'react-dom';
import User from './User.js'
import LocalizedStrings from 'react-localization';
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
var qs = require('qs');
var sha256 = require('sha256');

let strings = new LocalizedStrings({
 en:{
   userData:"User Data",
   login:"Login"
 },
 ro: {
   userData:"Date utilizator",
   login:"Logare"
 }
});

function getUser(token){
  const API = 'https://localhost:8085/fii/users/principal';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      }
    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
     if (typeof data.statusCode == 'undefined')
        {
             console.log(data);
             var user= new User(data);
             localStorage.setItem('user',JSON.stringify(user.state));
        }
})
}

// function proceedWithToken(username,password,code)
// {var token =localStorage.getItem('token');
//     if(typeof token == 'undefined')
//     refreshToken(username,password,code);
//     token =  localStorage.getItem('token');
//     if(typeof token == 'undefined')
//       ReactDOM.render(<h1>Login failed</h1>, document.getElementById('thirdStep'));
// }

export default class Login extends Component {

  constructor(props) {
    super(props);
    var language=localStorage.getItem('language');
    console.log(language);
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');

    // this.state = {
    //   users: [], data: []
    // };

     this.state = { firstStepState: 'shown' };
  }

//   checkUser(){
//     console.log('check');
//   const GETAPI = 'https://localhost:8085/fii/users/'+this.uname.value;
//       let config = {
//       method: 'GET',
//       }
// console.log('check2');
//     fetch(GETAPI, config)
//    .then(response =>
//       response.json()) .then((data) => { 
//         if(data.statusCode == '400'||data.statusCode == '404'){
//           //this.secondFieldset.className='hidden';
//             //this.thirdFieldset.className='';
//             ReactDOM.render(<h3>Invalid username</h3>, document.getElementById('validation'));
//           }
//         else if (typeof data.statusCode == 'undefined')
//           {
//              var user= new User(data);
//            if(user.state.password != sha256(this.password.value)){
//             //this.secondFieldset.className='hidden';
//             //this.thirdFieldset.className='';
//             ReactDOM.render(<h3>Invalid password</h3>, document.getElementById('validation'));}
//            else {
//             console.log('else');
//            this.firstFieldset.className='hidden';
//            this.secondFieldset.className='';
//            localStorage.setItem('role',data.roles[0].roleName)
//          }}
//         else {
//            ReactDOM.render(<h3>Something went wrong, please try again</h3>, document.getElementById('validation'));
//         }
// })
// }



    getToken(username,password,code){

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

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 

        if(typeof data.access_token!= 'undefined')
        {
          localStorage.setItem('token', base64.encode(data.access_token));
          getUser(data.access_token);
          this.firstFieldset.className='hidden';
          this.secondFieldset.className='hidden';
          ReactDOM.render(<h1>Login successfull</h1>, document.getElementById('thirdStep'));
        }
          else
          {
            ReactDOM.render(<h1>Login failed</h1>, document.getElementById('thirdStep'));
          }     

    })
}

    handleSubmit(e){
    /*this.secondFieldset.className='hidden';
    this.thirdFieldset.className='';*/
    e.preventDefault();
    var username = this.uname.value;
    var password = this.password.value;
    var code = this.code.value;
    this.getToken(username,password,code);
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
    localStorage.setItem('code', code);
 }


  render() {

    return (
      <Form>
  {formApi => (
    <form class="form-style-5">
   
<fieldset  ref={(ref) => this.firstFieldset = ref}>
<legend><span class="number">!</span>{strings.userData}</legend>
<input type="text" ref={(ref) => this.uname = ref} name="field1" placeholder="Username*"/>
<input type="password" ref={(ref) => this.password = ref} name="field2" placeholder="Password *"/>
</fieldset>
<fieldset id="QRCode" ref={(ref) => this.qr = ref}>
    </fieldset>
<fieldset id="secondStep" ref={(ref) => this.secondFieldset = ref}>
        <input type="text"  ref={(ref) => this.code = ref} name="field3" placeholder="Validation Code" />
        <input type="submit" onClick={this.handleSubmit.bind(this)} value={strings.login} />
           </fieldset>
        <fieldset id="thirdStep" ref={(ref) => this.thirdFieldset = ref}>
    </fieldset>      
    </form>
  )}
</Form>
    );
  }
}
 


