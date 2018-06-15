import React, { Component } from "react";
import moment from 'moment';
import DatePicker from 'react-datepicker';
import Setting from './Setting.js'
import sweetAlert from 'sweetalert';
const base64 = require('base-64');


class Settings extends Component {
      constructor(props) {
        super(props);
         this.state = {
      startDate: moment(),
      endDate: moment()
    }
        this.handleStartDateChange = this.handleStartDateChange.bind(this);
        this.handleEndDateChange = this.handleEndDateChange.bind(this);
        this.handlePostDate = this.handlePostDate.bind(this);
        this.getSettings();
    }

  handleStartDateChange(date) {
    this.setState({
      startDate: date
    });
  }

    handleEndDateChange(date) {
    this.setState({
      endDate: date
    });
  }

  handlePostDate () {
      if(this.compareTime( this.state.startDate,this.state.endDate)==false){
        sweetAlert ("End date must be after start date");
      }
      else{
        var props ={}
    props.startDate = this.state.startDate.format("X");
    props.endDate = this.state.endDate.format("X");
    var setting= new Setting(props);
    var token = base64.decode(localStorage.getItem('token'));
    let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body:JSON.stringify(setting.state)
    }
    const API='https://localhost:8085/fii/settings'
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => 
        {
       if (typeof data.statusCode != 'undefined')
          {
            //ReactDOM.render(<p>Could not create announcement. Please try again. </p>, document.getElementById('messageResult'));
          }
          else
            { this.getSettings()}
      })

  }}

  getSettings()
    {
      var token = base64.decode(localStorage.getItem('token'));
      const API = 'https://localhost:8085/fii/settings';
      let config = {
      method: 'GET',
      headers: { 'Authorization': 'Bearer '+ token}
    }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        console.log(data);
        console.log(data.message);
        if(typeof data.message == 'undefined')
        {
        var start=this.convertTimestampToMoment(data.startDate);
        var end=this.convertTimestampToMoment(data.endDate);
        this.setState( { startDate:start, endDate:end})
      }
        else
        {
          this.setState( { startDate:moment(), endDate:moment()})
        }
      }) 
    }

    convertTimestampToMoment(date){
    var newdate= new Date(date*1000);
    return moment(newdate);
    }

    compareTime(time1, time2) {
    return time1.isBefore(time2); 
}



 render() { 
    return <div>Select start date <DatePicker
        selected={this.state.startDate}
        onChange={this.handleStartDateChange}
    />

    Select start date <DatePicker
        selected={this.state.endDate}
        onChange={this.handleEndDateChange}
    />
     <button onClick={this.handlePostDate}>Done</button>
    </div>
      }
    }

export default Settings;
