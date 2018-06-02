  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import AdmissionData from './AdmissionData.js'
  export default class admission extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		countries: []
    };
  }

  handleSubmit(e){

      this.registerForm.className='hidden';
      e.preventDefault();
     }


     getCountries()
    {
      var token = base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/countries';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        this.setState( { countries:data})}) 
    }

  render() { 
  return (
    	<div>
      <Form>
  {formApi => (
    <form class="form-style-5" ref={(ref) => this.registerForm = ref}>
   
  <fieldset>
  <legend><span class="number">!</span> Candidate Info</legend>
  <input type="number" ref={(ref) => this.firstname = ref} name="cnp" placeholder="Cnp *" required/>
  <input type="text" ref={(ref) => this.lastname = ref} name="address" placeholder="Adress *" required/>
  <input type="text" ref={(ref) => this.email = ref} name="examSubject" placeholder="Exam Subject *" required/>
  <input type="text" ref={(ref) => this.uname = ref} name="telephone" placeholder="Telephone *" required/>
  <input type="number" ref={(ref) => this.password = ref} name="bacGrade" placeholder="Bacalaureat Grade *" required/>
  <input type="number" ref={(ref) => this.password = ref} name="generalGrade" placeholder="General Grade *" required/>
   Please select whether the candidate has disabilities<select ref={(ref) => this.hasDisabilities = ref}>
  <option value="0">Yes</option>
  <option value="1">No</option>
  </select>
  <input type="text" ref={(ref) => this.password = ref} name="additionalInformation" placeholder="Additional Information"/>
   Please select civil state<select ref={(ref) => this.civilState = ref}>
  <option value="0">Single</option>
  <option value="1">Married</option>
  </select>
  Please select section based on language<select ref={(ref) => this.language = ref}>
  <option value="0">English</option>
  <option value="1">Romanian</option>
  </select>
  Please select admission type<select ref={(ref) => this.admissionType = ref}>
  <option value="0">Admitere</option>
  <option value="1">Preadmitere</option>
  </select>
  Please select country
  <select ref={(ref) => this.country = ref}>
  {this.state.countries.map(function(country, i){
       return <option value=i>{country.country}</option>
     })}
  </select>
 
 
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