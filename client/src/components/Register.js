  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import ReactDOM from 'react-dom';
  import User from './User.js'
  import LocalizedStrings from 'react-localization';
  import sweetAlert from 'sweetalert';
  const API = 'https://localhost:8085/register'
  const base64 = require('base-64');
  var qs = require('qs');

  let strings = new LocalizedStrings({
 en:{
   userData:"User Data",
   apply:"Register",
   success: "Register successfull",
   failed :"Register failed",
   somethingWrong:"Something went wrong, please try again",
   enable: "I want to enable two factor authentication with my mobile device",
   firstName: "First Name *",
   lastName: "Last Name *",
   username: "Username *",
   password: "Password *",
   retypepass: "Re-type password *"
 },
 ro: {
   userData:"Date utilizator",
   apply:"Inregistreaza-te",
   success: "Inregistrarea a fost facuta cu succes",
   failed :"Inregistrarea a esuat",
   somethingWrong:"A aparut o eroare, va rugam incercati din nou",
   enable: "Vreau sa permit autentificarea in 2 factori cu dispozitivul mobil",
   firstName: "Prenume *",
   lastName: "Nume *",
   username: "Nume utilizator *",
   password: "Parola *",
   retypepass: "Reintroduceti parola *"
 }
});

  class Register extends Component {

  constructor(props) {
    super(props);
    var language=localStorage.getItem('language');
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');
    this.handlePassKeyUp = this.keyUpHandler.bind(this, 'checkPassword');
    this.handleInputChange = this.handleInputChange.bind(this);
    this.state = { twoFA: true};
  }

  register(user){
      console.log(JSON.stringify(user.state));
  let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json'},
      body: JSON.stringify(user.state)
    }

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => {
          if(data.statusCode == 400)
          {
            var failed=strings.failed;
            var message=data.message;
            sweetAlert(failed,message,"error");
          }
          else if (typeof data.statusCode == 'undefined')
          {
            if(this.state.twoFA==true)
             ReactDOM.render(<img id='qr' src={data.url}></img>, document.getElementById('QRCode'));
           else
           {
            var success=strings.success;
            sweetAlert(success,"","success");
          }
          }
         else
          {
            var failed=strings.failed;
            var somethingWrong=strings.somethingWrong;
             sweetAlert(failed,somethingWrong,"error");
          }

    })
  }

 handleInputChange(event) {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;

    this.setState({
      [name]: value
    });
  }

  handleSubmit(e){
    
     e.preventDefault();
     this.registerForm.className='hidden';
  	 var username = this.uname.value;
  	 var firstname = this.firstname.value;
     var lastname = this.lastname.value;
     var password = this.password.value;
     var email = this.email.value;
     var twoFA=this.state.twoFA;
     var props ={}
     props.username = username;
     props.firstName = firstname;
     props.lastName = lastname;
     props.password = password;
     props.email = email;
     props.has2FA=twoFA;
     var user= new User(props);
     this.register(user);
  }

keyUpHandler(refName, e) {
        var password = this.password.value;
        var checkpassword= this.checkpassword.value;
        if(password === checkpassword){
        ReactDOM.render(<p>Paswords match</p>, document.getElementById('passwordValidation'));
    }else{
        ReactDOM.render(<p>Passwords don't match</p>, document.getElementById('passwordValidation'));
    }
    }

  render() {

    return (
    	<div>
      <Form>
  {formApi => (
    <form onSubmit={this.handleSubmit.bind(this)} class="form-style-5" ref={(ref) => this.registerForm = ref} autocomplete="off">
   
  <fieldset>
  <legend><span class="number">!</span>{strings.userData}</legend>
  <input type="text" ref={(ref) => this.firstname = ref} name="field1" placeholder={strings.firstName} required/>
  <input type="text" ref={(ref) => this.lastname = ref} name="field2" placeholder={strings.lastName} required/>
  <input type="email" ref={(ref) => this.email = ref} placeholder="Email *" required="required"/>
  <input type="text" ref={(ref) => this.uname = ref} name="field4" placeholder={strings.username} required/>
  <input type="password" ref={(ref) => this.password = ref} name="field5" placeholder={strings.password} required/>
  <input id="checkpassword" ref={(ref) => this.checkpassword = ref} type="password" name="field6" placeholder={strings.retypepass} onKeyUp={this.handlePassKeyUp} required/> 
  <span id="passwordValidation"></span> 
 <label>
          {strings.enable}
          <input
            name="twoFA"
            type="checkbox"
            checked={this.state.twoFA}
            onChange={this.handleInputChange} />
        </label>
  </fieldset>
  <input type="submit" value={strings.apply} />      
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

