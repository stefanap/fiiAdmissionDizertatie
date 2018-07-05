import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Register.css";
import "./Login.css";
import {updateParent} from './Menu'
import ReactDOM from 'react-dom';
import User from './User.js'
import LocalizedStrings from 'react-localization';
import sweetAlert from 'sweetalert';
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
var qs = require('qs');
var sha256 = require('sha256');

let strings = new LocalizedStrings({
 en:{
   userData:"User Data",
   login:"Login",
   loginSuccessfull: "Login successfull",
   loginFailed: "Login failed",
   username:"Username *",
   password:"Password *",
   verificationCode:"Validation code"
 },
 ro: {
   userData:"Date utilizator",
   login:"Logare",
   loginSuccessfull: "Logare realizata cu success",
   loginFailed: "Login esuat",
   username:"Username *",
   password:"Parola *",
   verificationCode:"Cod validare"
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
             console.log("user",data);
             var user= new User(data);
             updateParent(user.state);
             localStorage.setItem('user',JSON.stringify(user.state));
        }
})
}

export default class Login extends Component {

  constructor(props) {
    super(props);
    var language=localStorage.getItem('language');
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');
     this.state = { firstStepState: 'shown' };
  }

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

        this.formLogin.className='hidden';

        if(typeof data.access_token!= 'undefined')
        {
          localStorage.setItem('token', base64.encode(data.access_token));
          getUser(data.access_token);
          this.firstFieldset.className='hidden';
          this.secondFieldset.className='hidden';
          var succ=strings.loginSuccessfull;
          sweetAlert(succ,"","success");
        }
          else
          {
            var failure=strings.loginFailed
            sweetAlert(failure,"","error");
          }     

    })
}

    handleSubmit(e){
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
    <form onSubmit={this.handleSubmit.bind(this)} ref={(ref) => this.formLogin = ref} class="form-style-5" autocomplete="off">
   
<fieldset  ref={(ref) => this.firstFieldset = ref}>
<legend><span class="number">!</span>{strings.userData}</legend>
<input type="text" ref={(ref) => this.uname = ref} name="field1" placeholder={strings.username} required/>
<input type="password" ref={(ref) => this.password = ref} name="field2" placeholder={strings.password} required/>
</fieldset>
<fieldset id="secondStep" ref={(ref) => this.secondFieldset = ref}>
        <input type="text"  ref={(ref) => this.code = ref} name="field3" placeholder={strings.verificationCode} />
        <input type="submit" value={strings.login} />
           </fieldset>      
    </form>
  )}
</Form>
    );
  }
}
 


