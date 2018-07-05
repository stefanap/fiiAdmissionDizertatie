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
import {CSVLink, CSVDownload} from 'react-csv';

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
   city:"City",
   bacDiploma: "Bacalaureat Diploma",
   idCard: "Identity document",
   birthCert:"Birth certificate",
   marriageCert: "Marriage certificate",
   changeRole: "Change Role",
   viewAdmissionData: "View admission data"

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
   city:"Oras",
   bacDiploma: "Diploma Bacalaureat",
   idCard: "Document de identitate",
   birthCert:"Certificat de nastere",
   marriageCert: "Certificat de casatorie",
   changeRole: "Modifica rol",
   viewAdmissionData: "Vizualizare date admitere"
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
      admission: {},
      bac:{},
      idCard:{},
      birthCert:{},
      marriageCert:{},
      csvData:''
    };
    this.getUsers();
    this.getExportedCsv();
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
        var adm =data;
        if(adm!=null && typeof adm.statusCode=='undefined')
        {
        adm.country=data.country.country;
        adm.region=data.region.region;
        adm.city=data.city.city;
        adm.highschool=data.highschool.highSchoolName;
        adm.hasDisabilities=data.hasDisabilities;
        console.log(data);
        adm.civil_state=data.civil_state;
      }
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
        console.log(data);
        this.setState( { bac:data[3], birthCert: data[1], idCard:data[0], marriageCert:data[3]})}) 
    }

    getExportedCsv()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/admission/export';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    fetch(API, config)
   .then(response =>
      response.text()) .then((data) => { 
        var dataString = String(data);
        this.setState( { csvData:dataString});
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


    downloadBac(image){
    this.download1.href= image;
    }

    downloadIdCard(image){
    this.download2.href= image;
    }

    downloadBirthCert(image){
    this.download3.href= image;
    }

    downloadMarriageCert(image){
    this.download4.href= image;
    }


  render() {
    var bac=''; if(typeof this.state.bac!='undefined' && this.state.bac!=null) bac=this.state.bac;
    var idCard=''; if(typeof this.state.idCard!='undefined' && this.state.idCard!=null) idCard=this.state.idCard;
    var birthCert=''; if(typeof this.state.birthCert!='undefined' && this.state.birthCert!=null) birthCert=this.state.birthCert;
    var marriageCert=''; if(typeof this.state.marriageCert!='undefined' && this.state.marriageCert!=null) marriageCert=this.state.marriageCert;
    return (
       <div>
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
        Cell: ({value}) => (<div><button class='changeRoleButton' onClick={() => { this.changeUserRole(value) }}>{strings.changeRole}</button> <button class='changeRoleButton' onClick={() => { this.handleOpenModal(value) }}>{strings.viewAdmissionData}</button></div>)
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
                width: '800px',
                height:'600px',
                left: '600px'
              }
            }}
        >
           <table>
       <tr><td class="left">Cnp:</td><td> {this.state.admission.cnp}</td></tr>
       <tr><td class="left">{strings.address}:</td><td> {this.state.admission.address}</td></tr>
        <tr><td class="left">{strings.examSubject}:</td><td> {this.state.admission.examSubject}</td></tr>
         <tr><td class="left">{strings.telephone}: </td><td>{this.state.admission.telephone}</td></tr>
        <tr><td class="left">{strings.bacGrade}: </td><td>{this.state.admission.bacGrade}</td></tr>
         <tr><td class="left">{strings.highschool}:</td><td> {this.state.admission.highschool}</td></tr>
        <tr><td class="left">{strings.civil_state}: </td><td>{this.state.admission.civil_state}</td></tr>
         <tr><td class="left">{strings.section}: </td><td>{this.state.admission.language}</td></tr>
        <tr><td class="left">{strings.hasDisabilities}: </td><td>{String(this.state.admission.hasDisabilities)}</td></tr>
        <tr><td class="left">{strings.additionalInformation}: </td><td>{this.state.admission.additionalInformation}</td></tr>
        <tr><td class="left">{strings.admissionType}: </td><td>{this.state.admission.admissionType}</td></tr>
        <tr><td class="left">{strings.country}: </td><td>{this.state.admission.country}</td></tr>
        <tr><td class="left">{strings.region}: </td><td>{this.state.admission.region}</td></tr>
        <tr><td class="left">{strings.city}: </td><td>{this.state.admission.city}</td></tr>
        <tr><td class="left">{strings.bacDiploma1} </td><td><img class="document" src={"data:"+bac.mimeType+";base64,"+bac.content}></img><a class="download" ref={(ref) => this.download1 = ref} download="Doc1.jpg" onClick={() => { this.downloadBac("data:"+bac.mimeType+";base64,"+bac.content) }}>Download</a></td></tr>
        <br/>
        <tr><td class="left">{strings.idCard1} </td><td><img class="document" src={"data:"+idCard.mimeType+";base64,"+idCard.content}></img><a class="download" ref={(ref) => this.download2 = ref} download="Doc2.jpg" onClick={() => { this.downloadIdCard("data:"+idCard.mimeType+";base64,"+idCard.content) }}>Download</a></td></tr>
        <br/>
        <tr><td class="left">{strings.birthCert1} </td><td><img class="document" src={"data:"+birthCert.mimeType+";base64,"+birthCert.content}></img><a class="download" ref={(ref) => this.download3 = ref} download="Doc3.jpg" onClick={() => { this.downloadBirthCert("data:"+birthCert.mimeType+";base64,"+birthCert.content) }}>Download</a></td></tr>
        <br/>
        <tr><td class="left">{strings.marriageCert1} </td><td><img class="document" src={"data:"+marriageCert.mimeType+";base64,"+marriageCert.content}></img><a class="download" ref={(ref) => this.download4 = ref} download="Doc4.jpg" onClick={() => { this.downloadMarriageCert("data:"+marriageCert.mimeType+";base64,"+marriageCert.content) }}>Download</a></td></tr>
        </table>
      <br/>
          <button class='changeRoleButton' onClick={this.handleCloseModal}>Close Modal</button>
        </Modal>
          <CSVLink data={this.state.csvData}>Download full admission data</CSVLink>
      </div>

    )
  }
}


 
export default Announcements;