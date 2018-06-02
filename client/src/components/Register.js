  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import ReactDOM from 'react-dom';
  import User from './User.js'
  const API = 'https://localhost:8085/register'
  const base64 = require('base-64');
  var qs = require('qs');

    function register(user){
      console.log(JSON.stringify(user.state));
  let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json'},
      body: JSON.stringify(user.state)
    }

     fetch(API, config)
    .then(response =>
      response.text()) .then((data) => { 
          if (typeof data.statusCode == 'undefined')
          {
             ReactDOM.render(<h1><img id='qr' src={data}></img></h1>, document.getElementById('QRCode'));
          }
          else if(data.statusCode == '400')
          {
            ReactDOM.render(<p>{data.message}</p>, document.getElementById('messageResult'));
          }
         else
          {
            ReactDOM.render(<p>Something went wrong, please try again</p>, document.getElementById('messageResult'));
          }

    })
  }

  class Register extends Component {

  constructor(props) {
    super(props);
    this.handlePassKeyUp = this.keyUpHandler.bind(this, 'checkPassword');
    this.state = {};
  }

  handleSubmit(e){

      this.registerForm.className='hidden';
      e.preventDefault();
  	 var username = this.uname.value;
  	 var firstname = this.firstname.value;
     var lastname = this.lastname.value;
     var password = this.password.value;
     var email = this.email.value;
     var props ={}
     props.username = username;
     props.firstName = firstname;
     props.lastName = lastname;
     props.password = password;
     props.email = email;
     console.log('test');
     console.log(props);
     var user= new User(props);
     register(user);
  }

keyUpHandler(refName, e) {
        var password = this.password.value;
        var checkpassword= this.checkpassword.value;
        //var goodColor = "#85AD33";
        //var badColor = "#DB4D4D";
        if(password === checkpassword){
        //checkpassword.style.backgroundColor = goodColor;
        //message.style.color = goodColor;
        ReactDOM.render(<p>Paswords match</p>, document.getElementById('passwordValidation'));
    }else{
        //checkpassword.style.backgroundColor = badColor;
        //message.style.color = badColor;
        ReactDOM.render(<p>Passwords don't match</p>, document.getElementById('passwordValidation'));
    }
    }

  render() {

    return (
    	<div>
      <Form>
  {formApi => (
    <form class="form-style-5" ref={(ref) => this.registerForm = ref}>
   
  <fieldset>
  <legend><span class="number">1</span> Candidate Info</legend>
  <input type="text" ref={(ref) => this.firstname = ref} name="field1" placeholder="First Name *" required/>
  <input type="text" ref={(ref) => this.lastname = ref} name="field2" placeholder="Last Name *" required/>
  <input type="email" ref={(ref) => this.email = ref} name="field3" placeholder="Email *" required/>
  <input type="text" ref={(ref) => this.uname = ref} name="field4" placeholder="Username*" required/>
  <input type="password" ref={(ref) => this.password = ref} name="field5" placeholder="Password *" required/>
  <input id="checkpassword" ref={(ref) => this.checkpassword = ref} type="password" name="field6" placeholder="Retype Password *" onKeyUp={this.handlePassKeyUp} required/> 
  <span id="passwordValidation"></span> 
  </fieldset>
  <input type="submit" onClick={this.handleSubmit.bind(this)} value="Apply" />      
    </form>
  )}
  </Form>
  <span id="messageResult"></span> 
  <div id="QRCode" ref={(ref) => this.qr = ref}>
    </div>
    </div>
    );
  }
  }

  export default Register;

