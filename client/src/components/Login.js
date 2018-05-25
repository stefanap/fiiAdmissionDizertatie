import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Register.css";
import "./Login.css";
import ReactDOM from 'react-dom';
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
var qs = require('qs');
var sha256 = require('sha256');



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

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
          console.log(data); localStorage.setItem('token', data.access_token)})     

    }


function proceedWithToken(username,password,code)
{var token =localStorage.getItem('token');
    if(token == 'undefined')
    refreshToken(username,password,code);
    token =  localStorage.getItem('token');
    if(token == 'undefined')
      ReactDOM.render(<h1>Login failed</h1>, document.getElementById('thirdStep'));
}

class Login extends Component {

  constructor(props) {
    super(props);

    // this.state = {
    //   users: [], data: []
    // };

     this.state = { firstStepState: 'shown' };
  }

  checkUser(){
  const GETAPI = 'https://localhost:8085/users/user/'+this.uname.value;
      let config = {
      method: 'GET',
      }

    fetch(GETAPI, config)
   .then(response =>
      response.json()) .then((data) => { 
        if(data.id=='undefined'){
          this.secondFieldset.className='hidden';
            this.thirdFieldset.className='';
            ReactDOM.render(<h3>Bad credentials</h3>, document.getElementById('thirdStep'));
          }
        if(data.password !== sha256(this.password.value)){
            this.secondFieldset.className='hidden';
            this.thirdFieldset.className='';
            ReactDOM.render(<h3>Bad credentials</h3>, document.getElementById('thirdStep'));
        }
        else
          {
           this.firstFieldset.className='hidden';
           this.secondFieldset.className='';
           localStorage.setItem('role',data.roles[0].roleName)}
})
}

    
    handleSubmit(e){

    this.secondFieldset.className='hidden';
    this.thirdFieldset.className='';
    e.preventDefault();
    var username = this.uname.value;
    var password = this.password.value;
    var code = this.code.value;
    proceedWithToken(username,password,code);
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
<legend><span class="number">1</span> Candidate Info</legend>
<input type="text" ref={(ref) => this.uname = ref} name="field1" placeholder="Username*"/>
<input type="password" ref={(ref) => this.password = ref} name="field2" placeholder="Password *"/>
<input type="button" class="next" value="Next" onClick={this.checkUser.bind(this)}/>
</fieldset>
<fieldset id="QRCode" ref={(ref) => this.qr = ref}>
    </fieldset>
<fieldset id="secondStep" class='hidden' ref={(ref) => this.secondFieldset = ref}>
        <h3>Please scan an introduce the recieved numeric code</h3>
        <input type="text"  ref={(ref) => this.code = ref} name="field3" placeholder="Validation Code" />
        <input type="submit" onClick={this.handleSubmit.bind(this)} value="Apply" />
           </fieldset>
        <fieldset id="thirdStep" class='hidden' ref={(ref) => this.thirdFieldset = ref}>
    </fieldset>










      
    </form>
  )}
</Form>
    );
  }
}
 
export default Login;

