  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import AdmissionData from './AdmissionData.js'
  const base64 = require('base-64');
  export default class admission extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		countries: [],
  		regions: [],
  		cities: [],
  		highschools: [],
  		countryId: '',
  		regionId: '',
  		cityId: '',
  		highschoolId:''
    };
    this.getCountries();
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
      	console.log(data.length);
      	if(data.legth!=0){
        this.setState( { countryId:data[0].id});
        this.getRegions(data[0].id);
    }
        this.setState( { countries:data})}) 
    }

    getRegions(countryId)
    {
      var token = base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/countries/'+countryId+'/regions';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
      	if(data.legth!=0){
      	this.setState( { regionId:data[0].id});
        this.getCities(data[0].id);
        this.getHighschools(data[0].id);
    }
        this.setState( { regions:data})}) 
    }


    getCities(regionId)
    {
      var token = base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/regions/'+regionId+'/cities';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
      	if(data.legth!=0)
      	{this.setState( { cityId:data[0].id});}
        this.setState( { cities:data})}) 
    }

    getHighschools(regionId)
    {
    	console.log('gethigh');
      var token = base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/regions/'+regionId+'/highschools';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
      	this.setState( { highschools:data});
      	if(data.length!=0)
      	{
      	this.setState( { highschoolId:data[0].id});
      }}) ;
    }

    handleCountryChange (e) {
    var id = this.country.value;
    this.setState( { countryId:id})
    this.getRegions(id);
  }

   handleRegionChange (e) {
    var id = this.region.value;
    this.setState( { regionId:id})
    this.getCities(id);
    this.getHighschools(id);
  }

  handleCityChange (e) {
    var id = this.city.value;
    this.setState( { cityId:id})
  }

   handleHighschoolChange (e) {
    var id = this.highschool.value;
    this.setState( { highschoolId:id})
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
  <select ref={(ref) => this.country = ref} onChange={(e) => {this.handleCountryChange(e)}}>
  {this.state.countries.map(function(country){
       return <option value={country.id}>{country.country}</option>
     })}
  </select>
  Please select region
  <select ref={(ref) => this.region = ref} onChange={(e) => {this.handleRegionChange(e)}}>
  {this.state.regions.map(function(region){
       return <option value={region.id}>{region.region}</option>
     })}
  </select>
  Please select city
  <select ref={(ref) => this.city = ref} onChange={(e) => {this.handleCityChange(e)}}>
  {this.state.cities.map(function(city){
       return <option value={city.id}>{city.city}</option>
     })}
  </select>
  Please select highschool
   <select ref={(ref) => this.highschool = ref} onChange={(e) => {this.handleHighschoolChange(e)}}>
  {this.state.highschools.map(function(highschool){
       return <option value={highschool.id}>{highschool.highSchoolName}</option>
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