import React, { Component } from "react";
import { Form, Text } from 'react-form';
import ReactDOM from 'react-dom';
import {Bar} from 'react-chartjs-2';
import "./Reports.css";
import moment from 'moment';
import {Doughnut} from 'react-chartjs-2';
import {Pie} from 'react-chartjs-2';
const base64 = require('base-64');
  require('react-bootstrap');

function convertTimestampToMoment(date){
    var date= new Date(date/1000*1000);
    var day = date.getDate(); 
    var month = date.getMonth(); 
    var year = date.getFullYear()
    return day+'/'+month+'/'+year;
    }
      function download(){
    var download = document.getElementById("download");
    var image = document.getElementById("myCanvas").toDataURL("image/png")
                    .replace("image/png", "image/octet-stream");
        download.setAttribute("href", image);
        //download.setAttribute("download","archive.png");
  }


export default class Reports extends Component {


 constructor(props) {
    super(props);
    this.state = {
    };
    this.getDataPerDays();
    this.getDataPerSection();
     this.getDataPerAdmissionType();
}

  getRegisterPerDays()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/statistics/registers-per-day';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    return fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        var keys = Object.keys(data);
        keys.forEach(function(part, index, theArray) {
  theArray[index] = convertTimestampToMoment(part);
});
        var values=Object.values(data);
        var chartdata = {
  labels: keys,
  datasets: [
    {
      label: 'Registration per days',
      backgroundColor: 'rgba(255, 153, 153,0.2)',
      borderColor: 'rgba(255,99,132,1)',
      borderWidth: 1,
      hoverBackgroundColor: 'rgba(255,99,132,0.4)',
      hoverBorderColor: 'rgba(255,99,132,1)',
      data: values
    }
  ]
};
        return chartdata;
        })    
    }


    getDataPerDays()
    {
     this.getRegisterPerDays().then(response => this.setState( { dataPerDays:response}));
    }

    getRegisterPerSection()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/statistics/registers-per-section';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    return fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        var keys = Object.keys(data);
        var values=Object.values(data);
        var chartdata = {
  labels: keys,
  datasets: [
    {
      label: 'Registration per section',
      backgroundColor: [
    '#FF6384',
    '#36A2EB'
    ],
    hoverBackgroundColor: [
    '#FF6384',
    '#36A2EB'
    ],
      data: values
    }
  ]
};
        return chartdata;
        })    
    }

     getDataPerSection()
    {
     this.getRegisterPerSection().then(response => this.setState( { dataPerSection:response}));
    }


 getRegisterPerAdmissionType()
    {
      var token=base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/statistics/registers-per-admission-type';
      let config = {
      method: 'GET',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token}
      }

    return fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        var keys = Object.keys(data);
        var values=Object.values(data);
        var chartdata = {
  labels: keys,
  datasets: [
    {
      label: 'Registration per admission type',
      backgroundColor: [
    '#ff80bf',
    '#80bfff'
    ],
    hoverBackgroundColor: [
    '#ff80bf',
    '#80bfff'
    ],
      data: values
    }
  ]
};
        return chartdata;
        })    
    }

     getDataPerAdmissionType()
    {
     this.getRegisterPerAdmissionType().then(response => this.setState( { dataPerAdmissionType:response}));
    }

    exportBar(){
    //var url_base64 = this.bar.chartInstance.chart.ctx;
    var image= this.bar.chartInstance.toBase64Image();
    this.download1.href= image;
    //toDataURL("image/jpg"); 
    //ReactDOM.render(<img id='qr' src={image}></img>, document.getElementById('img'));
    //console.log('ee',url_base64);
    //var link2={}; link2.href = url_base64;
    }

      exportDonought(){
    var image= this.donought.chartInstance.toBase64Image();
    this.download2.href= image;
    }

      exportPie(){
    var image= this.pie.chartInstance.toBase64Image();
    this.download3.href= image;
    }


  render() {
    return (
      <div>
      <div class="chart">
 <Bar ref={(ref) => this.bar = ref}
    data={this.state.dataPerDays}
    width={200}
    height={300}
  options= {{
    maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }}
/>
</div>
<a class="export" ref={(ref) => this.download1 = ref} download="RegistrationsPerDays.jpg" onClick={() => { this.exportBar() }}>Export</a>
<div class="chart" ref={(ref) => this.charts = ref}>
 <Doughnut ref={(ref) => this.donought = ref}
    data={this.state.dataPerSection}
    width={200}
    height={300}
    options={{
        maintainAspectRatio: false,
    }}
/>
</div>
<a class="export" ref={(ref) => this.download2 = ref} download="RegistrationsPerSections.jpg" onClick={() => { this.exportDonought() }}>Export</a>
<div class="chart">
 <Pie ref={(ref) => this.pie = ref}
    data={this.state.dataPerAdmissionType}
    width={200}
    height={300}
    options={{
        maintainAspectRatio: false
    }}

/>
</div>
<a class="export" ref={(ref) => this.download3 = ref} download="RegistrationsPerAdmissionType.jpg" onClick={() => { this.exportPie() }}>Export</a>
</div>
   );
      }
    }


 
