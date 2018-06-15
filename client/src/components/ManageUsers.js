import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Announcements.css";
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import Announcement from './Announcement.js'
import "./ManageUsers.css";
import User from './User.js'
import ReactTable from "react-table";
import "react-table/react-table.css";
import { ReactTableDefaults } from "react-table";
import LocalizedStrings from 'react-localization';

let strings = new LocalizedStrings({
 en:{
   address:"Address",
   telephone:"Telephone",
   examSubject:"Exam subject",
   bacGrade: "Bacalaureat grade",
   highschool: "Highschool",
   civil_state: "Civil state",
   section: "Section",
   hasDisabilities:"Has disabilities",
   additionalInformation:"Additional information",
   admissionType:"Admission type",
   country:"Country",
   region:"Region",
   city:"City"

 },
 ro: {
   address:"Adresa",
   telephone:"Telefon",
   examSubject:"Subiect examen",
   bacGrade: "Nota bacalaureat",
   highschool: "Liceu",
   civil_state: "Stare civila",
   section:"Sectiune",
   hasDisabilities:"Are disabilitati",
   additionalInformation:"Informatii aditionale",
   admissionType:"Tip admitere",
   country:"Tara",
   region:"Regiune",
   city:"Oras"
 }
});

Object.assign(ReactTableDefaults, {
  defaultPageSize: 7,
  filterable:true,
  style: {}
});


const base64 = require('base-64');
const base64js = require('base64-js')

class Announcements extends Component {

 constructor(props) {
    super(props);
     var language=localStorage.getItem('language');
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');
    this.state = {
      showModal: false,
      users: [],
      admission: {}
    };
    this.getUsers();
     this.handleOpenModal = this.handleOpenModal.bind(this);
    this.handleCloseModal = this.handleCloseModal.bind(this);
  }

 handleOpenModal (id) {
    this.setState({ showModal: true });
    this.getAdmissionData(id);
    this.getUserDocuments(id);
  }
  
  handleCloseModal () {
    this.setState({ showModal: false });
  }

   getAdmissionData(id)
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/admission/'+id;
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        console.log('bbbbbb',data);
        var adm =data;
        adm.country=data.country.country;
        adm.region=data.region.region;
        adm.city=data.city.city;
        adm.highschool=adm.highschool.highSchoolName;
        adm.hasDisabilities=data.hasDisabilities;
        this.setState( { admission:adm})}) 
    }


  getUserDocuments(id)
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/documents/users/'+id;
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        //var plswork=btoa(String.fromCharCode.apply(null, new Uint8Array(data[0].content)));
        console.log('bytes',(data[0].content));
        var plswork=base64js.fromByteArray(data[0].content);
        console.log('toString64',plswork);
 ReactDOM.render(<img src={"data:image/png;base64,"+plswork}></img>, document.getElementById('messageResult'));
       })
    }
  

 changeUserRole(id){
  console.log('here',id);
 var token = base64.decode(localStorage.getItem('token'));
    let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body:{}
    }
    const API='https://localhost:8085/fii/users/updateRole/'+id;
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {
          console.log(data);
       if (typeof data.statusCode != 'undefined')
          {
            ReactDOM.render(<p>Could not change user role. Please try again. </p>, document.getElementById('messageResult'));
          }
          else
             this.getUsers();
        }
      })
}

  getUsers()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/users';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        this.setState( { users:data})}) 
    }


  render() {
    return (
       <div>
         <span id="messageResult"></span> 
      <ReactTable
          data={this.state.users}
          columns={[
            
                {
                  Header: "First Name",
                  accessor: "firstName"
                },
                {
                  Header: "Last Name",
                  accessor: "firstName"
                },
                {
                  Header: "User Name",
                  accessor: "username"
                },
                 {
                  Header: "Email",
                  accessor: "email"
                },
                {
                  Header: "Admission Status",
                  accessor: "admissionStatus"
                },
                {
                  Header: "Role",
                  id: "roleName",
                  accessor: d => d.role.roleName
                },
                 {
        id: 'edit',
        accessor: "id",
        filterable: false,
        Cell: ({value}) => (<div><button class='changeRoleButton' onClick={() => { this.changeUserRole(value) }}>Change Role</button> <button class='changeRoleButton' onClick={() => { this.handleOpenModal(value) }}>View complete data</button></div>)
      }
            ]}
          defaultPageSize={10}
          className="-striped -highlight"
        />
          <span id="messageResult"></span> 
           <Modal
           isOpen={this.state.showModal}
           contentLabel="Modal"
           style={{
              content: {
                width: '400px',
                height:'500px',
                left: '600px'
              }
            }}
        >
           <table>
       <tr><td>Cnp:</td><td> {this.state.admission.cnp}</td></tr>
       <tr><td>{strings.address}:</td><td> {this.state.admission.address}</td></tr>
        <tr><td>{strings.examSubject}:</td><td> {this.state.admission.examSubject}</td></tr>
         <tr><td>{strings.telephone}: </td><td>{this.state.admission.telephone}</td></tr>
        <tr><td>{strings.bacGrade}: </td><td>{this.state.admission.bacGrade}</td></tr>
         <tr><td>{strings.highschool}:</td><td> {this.state.admission.highschool}</td></tr>
        <tr><td>{strings.civil_state}: </td><td>{this.state.admission.civil_state}</td></tr>
         <tr><td>{strings.section}: </td><td>{this.state.admission.language}</td></tr>
        <tr><td>{strings.hasDisabilities}: </td><td>{this.state.admission.hasDisabilities}</td></tr>
        <tr><td>{strings.additionalInformation}: </td><td>{this.state.admission.additionalInformation}</td></tr>
        <tr><td>{strings.admissionType}: </td><td>{this.state.admission.admissionType}</td></tr>
        <tr><td>{strings.country}: </td><td>{this.state.admission.country}</td></tr>
        <tr><td>{strings.region}: </td><td>{this.state.admission.region}</td></tr>
        <tr><td>{strings.city}: </td><td>{this.state.admission.city}</td></tr>
       
      </table>
          <button onClick={this.handleCloseModal}>Close Modal</button>
        </Modal>
          <span id="messageResult"></span> 
      </div>

    )
  }
}


 
export default Announcements;