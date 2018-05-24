import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Register.css";
import "./Login.css";
import ReactDOM from 'react-dom';
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
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

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
          console.log(data); localStorage.setItem('token', data.access_token)})     

    }


    function getQR(username)
    {
      const QRAPI = 'https://localhost:8085/users/'+username+'/qr-code'
      console.log(QRAPI);
      let config = {
      method: 'GET',
      }

    fetch(QRAPI, config)
   .then(response =>
      response.text()) .then((data) => { 
         ReactDOM.render(<h1><img id='qr' src={data}></img></h1>, document.getElementById('QRCode'))}) 

    }




class Login extends Component {

  constructor(props) {
    super(props);

    // this.state = {
    //   users: [], data: []
    // };

     this.state = { firstStepState: 'shown' };
  }
    
    handleSubmit(e){

    this.secondFieldset.className='hidden';
    this.qr.className='hidden';
    this.thirdFieldset.className='';
    e.preventDefault();
    var username = this.uname.value;
    var password = this.password.value;
    var code = this.code.value;

    var token =localStorage.getItem('token');
    if(token == 'undefined')
    refreshToken(username,password,code);
    token =  localStorage.getItem('token');
    console.log(token);
    if(token == 'undefined')
      ReactDOM.render(<h1>Login failed</h1>, document.getElementById('thirdStep'));
 }

    requestQr(e){
      var username = this.uname.value;
      var password = this.password.value;
      this.firstFieldset.className='hidden';
      this.secondFieldset.className='';
      getQR(username);
  
 }


  render() {

     const validate = value => ({
  error: !value || !/Hello World/.test(value) ? "Input must contain 'Hello World'" : null,
  warning: !value || !/^Hello World$/.test(value) ? "Input should equal just 'Hello World'" : null,
  success: value && /Hello World/.test(value) ? "Thanks for entering 'Hello World'!" : null
})

    return (
      <Form>
  {formApi => (
    <form class="form-style-5">
   
<fieldset  ref={(ref) => this.firstFieldset = ref}>
<legend><span class="number">1</span> Candidate Info</legend>
<input type="text" ref={(ref) => this.uname = ref} name="field1" placeholder="Username*"/>
<input type="password" ref={(ref) => this.password = ref} name="field2" placeholder="Password *"/>
<input type="button" class="next" value="Next"  onClick={this.requestQr.bind(this)}/>
</fieldset>
<fieldset id="QRCode" ref={(ref) => this.qr = ref}>
    </fieldset>
<fieldset id="secondStep" class='hidden' ref={(ref) => this.secondFieldset = ref}>
        <h3>Please scan an introduce the recieved numeric code</h3>
        <input type="text"  ref={(ref) => this.code = ref} name="field3" placeholder="Validation Code" />
        <input type="submit" onClick={this.handleSubmit.bind(this)} value="Apply" />
           </fieldset>
        <fieldset id="thirdStep" class='hidden' ref={(ref) => this.thirdFieldset = ref}>
        <h3>You have logged in!</h3>
    </fieldset>










      
    </form>
  )}
</Form>
    );
  }
}
 
export default Login;

