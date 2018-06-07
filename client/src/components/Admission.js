  import React, { Component } from "react";
  import { Form, Text } from 'react-form';
  import "./Register.css";
  import ReactDOM from 'react-dom';
  import AdmissionData from './AdmissionData.js'
  import superagent from 'superagent';
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

 function uploadDocuments(file){
 	 var token = base64.decode(localStorage.getItem('token'));
  let config = {
      method: 'POST',
      headers: { 'Content-Type':'form-data','Authorization': 'Bearer '+ token},
      body: JSON.stringify({
        documentType:'pdf',
        file: file
      })
    }

   //  superagent.post(UploadDocumentsAPI)
   //  .set({'Authorization': 'Bearer ' + token})
   // .attach('file',file) // file_object can be File or Blob
   // .then(result => {})

     fetch(UploadDocumentsAPI, config)
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


   function register(admissionData){
   	var token= base64.decode(localStorage.getItem('token'));
      console.log(JSON.stringify(admissionData.state));
  let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body: JSON.stringify(admissionData.state)
    }

     fetch(API, config)
    .then(response =>
      response.text()) .then((data) => { 
          if (typeof data.statusCode == 'undefined')
          {
             ReactDOM.render(<p>'Register successfull'</p>, document.getElementById('messageResult'));
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

   redirect() {
        this.props.history.push("/login")
    }

  handleSubmit(e){
  	 e.preventDefault();
     var props ={}
     props.cnp=this.cnp.value
     props.adress=this.adress.value
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
     /*register(admissionData);*/
     var bacDiploma= this.bac.value;
     uploadDocuments(bacDiploma);
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
    <form class="form-style-5" ref={(ref) => this.registerForm = ref}>
   
  <fieldset>
  <legend><span class="number">!</span>{strings.admissionData}</legend>
  <input type="number" ref={(ref) => this.cnp = ref} name="cnp" placeholder="Cnp *" required/>
  <input type="text" ref={(ref) => this.adress = ref} name="address" placeholder="Adress *" required/>
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
  <input type="submit" onClick={this.handleSubmit.bind(this)} value={strings.apply} />      
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