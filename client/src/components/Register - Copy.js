import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Home.css";
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
var qs = require('qs');

function refreshToken(){
let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/x-www-form-urlencoded', 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')},
      body: qs.stringify({
        'grant_type': 'password',
        'username': 'admin.admin',
        'password': 'jwtpass'
      })
    }

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
          localStorage.setItem('token', data.access_token)})     

    }
    

class Home extends Component {

  constructor(props) {
    super(props);

    this.state = {
      users: [], data: []
    };
  }

 handleSubmit(e){

    var token =localStorage.getItem('token');
    if(token==null)
      refreshToken();
    token =  localStorage.getItem('token');
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

  	 const validate = value => ({
  error: !value || !/Hello World/.test(value) ? "Input must contain 'Hello World'" : null,
  warning: !value || !/^Hello World$/.test(value) ? "Input should equal just 'Hello World'" : null,
  success: value && /Hello World/.test(value) ? "Thanks for entering 'Hello World'!" : null
})

    return (
      <Form>
  {formApi => (
    <form class="form-style-5">
   
<fieldset>
<legend><span class="number">1</span> Candidate Info</legend>
<input type="text" name="field1" placeholder="Your Name *"/>
<input type="email" name="field2" placeholder="Your Email *"/>
<textarea name="field3" placeholder="About yourself"></textarea>
<label for="job">Interests:</label>
<select id="job" name="field4">
<optgroup label="Indoors">
  <option value="fishkeeping">Fishkeeping</option>
  <option value="reading">Reading</option>
  <option value="boxing">Boxing</option>
  <option value="debate">Debate</option>
  <option value="gaming">Gaming</option>
  <option value="snooker">Snooker</option>
  <option value="other_indoor">Other</option>
</optgroup>
<optgroup label="Outdoors">
  <option value="football">Football</option>
  <option value="swimming">Swimming</option>
  <option value="fishing">Fishing</option>
  <option value="climbing">Climbing</option>
  <option value="cycling">Cycling</option>
  <option value="other_outdoor">Other</option>
</optgroup>
</select>      
</fieldset>
<fieldset>
<legend><span class="number">2</span> Additional Info</legend>
<textarea name="field3" placeholder="About Your School"></textarea>
</fieldset>
<input type="submit" onClick={this.handleSubmit.bind(this)} value="Apply" />









      
    </form>
  )}
</Form>
    );
  }
}
 
export default Home;

