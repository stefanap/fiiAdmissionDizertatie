import React, { Component } from "react";
import { Form, Text } from 'react-form';
import ReactDOM from 'react-dom';
const base64 = require('base-64');


export default class Reports extends Component {


 constructor(props) {
    super(props);
    this.state = {
      registerPerDays: [],
    };
    this.getRegisterPerDays();
  }

 

  getReports()
    {
      const API = 'https://localhost:8085/announcements';
      let config = {
      method: 'GET'
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        this.setState( { announcements:data})}) 
    }


  render() {
    return (
      <div>

</div>
   );
      }
    }


 
