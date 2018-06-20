  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import ReactDOM from 'react-dom';
  import AdmissionData from './AdmissionData.js'
  import superagent from 'superagent';
  import sweetAlert from 'sweetalert';
  import axios, { post } from 'axios';
  import LocalizedStrings from 'react-localization';

  let strings = new LocalizedStrings({
 en:{
   admissionData:"Admission Data",
   apply:"Register"
 },
 ro: {
   admissionData:"Date pentru admitere",
   apply:"Inregistreaza-te"
 }
});

  const base64 = require('base-64');
  const API = 'https://localhost:8085/fii/admission'
  const UploadDocumentsAPI='https://localhost:8085/fii/documents'


function createCompleteFileType(ext)
{
	switch (ext.toLowerCase()) {
    case 'pdf': return 'application/pdf';
    case 'png': return 'image/png';
    case 'jpg' : return 'image/jpg';
    case 'jpeg' : return 'image/jpeg'
        return true;
}}

 function uploadDocument(file){
var token = base64.decode(localStorage.getItem('token'));
var ext=file.split('.').pop();
var fileType= createCompleteFileType(ext);
const formData = new FormData();
formData.append('file', file)
formData.append('documentType',fileType);
const config = {
    headers: {
            'Content-type': 'multipart/form-data',
            'Authorization': 'Bearer ' + token
    }
    }
    return  post(UploadDocumentsAPI, formData,config)





// var formData = new FormData();
// formData.append("file", file);
// formData.append("documentType", ext);
// var xhr = new XMLHttpRequest();
// xhr.open("POST", UploadDocumentsAPI);
// xhr.setRequestHeader('Authorization', 'Bearer ' + token);
// xhr.setRequestHeader( 'Accept','application/json, text/plain, */*');
// xhr.send(formData);



// const data = new FormData();
// var ext=file.split('.').pop();
// var fileType= createCompleteFileType(ext);
// console.log(fileType);
// data.append('action', 'ADD');
// data.append('param', 0);
// data.append('secondParam', 0);
// data.append('file', new Blob([file], { 'type': fileType }));
// data.append('documentType',fileType );
// axios.post(UploadDocumentsAPI, data, { headers: {  Authorization: "Bearer " + token } });
}

  
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
    if(localStorage.getItem('user')==null)
    	this.redirect();
    this.getCountries();
    var language=localStorage.getItem('language');
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');

  }

   register(admissionData){
   	var token= base64.decode(localStorage.getItem('token'));
      console.log(JSON.stringify(admissionData.state));
  let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body: JSON.stringify(admissionData.state)
    }

     fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
      	var code=data.statusCode;
          if (typeof code == 'undefined')
          {
             sweetAlert("Admission data submitted","","success");
          }
          else if(code == 400)
          {
            sweetAlert("Error","You have already submitted admission data","error");
            this.registerForm.className='hidden';
          }
         else
          {
           sweetAlert("Error","Something went wrong, please try again","error");
          }

    })
  }



   redirect() {
        this.props.history.push("/login")
    }

  handleSubmit(e){
    e.preventDefault();
     var props ={}
     props.cnp=this.cnp.value
     props.address=this.address.value
     props.examSubject=this.examSubject.value
     props.telephone=this.telephone.value
     props.bacGrade=this.bacGrade.value
     props.generalGrade=this.generalGrade.value
     props.language=this.language.options[this.language.selectedIndex].text
     var hasDisabilities = this.hasDisabilities.options[this.hasDisabilities.selectedIndex].text
     props.hasDisabilities=(hasDisabilities == 'true');
     props.civilState=this.civilState.options[this.civilState.selectedIndex].text
     props.admissionType=this.admissionType.options[this.admissionType.selectedIndex].text
     var country={}
     country.id=this.country.value;
     country.country=this.country.options[this.country.selectedIndex].text
     props.country=country
     var region={}
     region.id=this.region.value
     region.region=this.region.options[this.region.selectedIndex].text
     props.region=region
     var city={}
     city.id=this.city.value
     city.city=this.city.options[this.city.selectedIndex].text
     props.city=city
     var highschool={}
     highschool.id=this.highschool.value
     highschool.highSchoolName=this.highschool.options[this.highschool.selectedIndex].text
     props.highschool=highschool
     props.additionalInformation=this.additionalInformation.value
     var admissionData= new AdmissionData(props);
     console.log('props',props);
     this.register(admissionData);
     var bacDiploma= this.bac.value;
     var birthCertificate = this.birthCert.value;
     var idCard=this.idCard.value;
     var marriageCert=this.marriageCert.value;
     uploadDocument(bacDiploma);
     uploadDocument(birthCertificate);
     uploadDocument(idCard);
     uploadDocument(marriageCert);
     this.registerForm.className='hidden';
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
      	if(data.length!=0 && typeof data.length!=='undefined'){
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
    <form onSubmit={this.handleSubmit.bind(this)} class="form-style-5" ref={(ref) => this.registerForm = ref}>
   
  <fieldset>
  <legend><span class="number">!</span>{strings.admissionData}</legend>
  <input type="number" ref={(ref) => this.cnp = ref} name="cnp" placeholder="Cnp *" required/>
  <input type="text" ref={(ref) => this.address = ref} name="address" placeholder="Address *" required/>
  <input type="text" ref={(ref) => this.examSubject = ref} name="examSubject" placeholder="Exam Subject *" required/>
  <input type="number" ref={(ref) => this.telephone = ref} name="telephone" placeholder="Telephone *" required/>
  <input type="number" ref={(ref) => this.bacGrade = ref} name="bacGrade" placeholder="Bacalaureat Grade *" required/>
  <input type="number" ref={(ref) => this.generalGrade = ref} name="generalGrade" placeholder="General Grade *" required/>
   Please select whether the candidate has disabilities<select ref={(ref) => this.hasDisabilities = ref}>
  <option value="0">Yes</option>
  <option value="1">No</option>
  </select>
  <input type="text" ref={(ref) => this.additionalInformation = ref} name="additionalInformation" placeholder="Additional Information"/>
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
    <p>Upload bac diploma *</p>
  <input ref={(ref) => this.bac = ref} multiple type="file" name="pic" accept='application/pdf,image/png,image/jpeg,image/jpg' />
  <br/>
  <p>Upload birth certificate *</p>
  <input type="file" ref={(ref) => this.birthCert = ref} name="pic" accept="application/pdf,image/png,image/jpeg,image/jpg" />
  <br/>
  <p>Upload identity card *</p>
  <input type="file" ref={(ref) => this.idCard = ref} name="pic" accept="application/pdf,image/png,image/jpeg,image/jpg"/>
  <br/>
  <p>Upload marriage certificate</p>
  <input type="file"  ref={(ref) => this.marriageCert = ref} name="pic" accept="application/pdf,image/png,image/jpeg,image/jpg"/>
 
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