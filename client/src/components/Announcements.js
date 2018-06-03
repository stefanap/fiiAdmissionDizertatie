import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Announcements.css";
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';
import Announcement from './Announcement.js'
const base64 = require('base-64');


class Announcements extends Component {


 constructor(props) {
    super(props);
    this.state = {
      announcements: [],
      showModal: false,
      Date: moment(),
      showButton : false
    };
    this.getAnnouncements();
    this.handleOpenModal = this.handleOpenModal.bind(this);
    this.handleCloseModal = this.handleCloseModal.bind(this);
    this.handlePostAnnouncement = this.handlePostAnnouncement.bind(this);
    this.handleChange = this.handleChange.bind(this);
    var user = JSON.parse(localStorage.getItem('user'));
    if(user.roles[0].roleName == 'ADMIN_USER')
      this.state.showButton=true;
  }

  handleChange(date) {
    this.setState({
      Date: date
    });
  }
  
  handleOpenModal () {
    this.setState({ showModal: true });
  }
  
  handleCloseModal () {
    this.setState({ showModal: false });
  }

  handlePostAnnouncement () {
    this.setState({ showModal: false });
    var description = this.description.value;
    var props ={}
    props.info = description;
    props.expiryDate = this.state.Date.format("X");
    var announcement= new Announcement(props);
    var token = base64.decode(localStorage.getItem('token'));
    let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body:JSON.stringify(announcement.state)
    }
    const API='https://localhost:8085/fii/announcements'
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {
          console.log(data);
       if (typeof data.statusCode != 'undefined')
          {
            ReactDOM.render(<p>Could not create announcement. Please try again. </p>, document.getElementById('messageResult'));
          }
          else
             this.getAnnouncements();
        }
      })
  }
  

  getAnnouncements()
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
    if(this.state.announcements.length ==0) 
    { 
    return null;
    }
    else 
    {
    return (
      <div>
<ul>
     {
     this.state.announcements.map(function(announcement, i){
       return <li class="announcement" key={i}>{announcement.info}</li>
     })}
   </ul>
  <div>
        <button id="create" className={this.state.showButton ? '' : 'hidden'} onClick={this.handleOpenModal}>Create announcement</button>
        <Modal
           isOpen={this.state.showModal}
           contentLabel="Modal"
           style={{
              content: {
                width: '300px',
                height:'350px',
                left: '700px'
              }
            }}
        >
           <textarea ref={(ref) => this.description = ref} name="comment" placeholder=
           "Enter announcement here..."></textarea>
           Select expiry date <DatePicker
        selected={this.state.Date}
        onChange={this.handleChange}
    />
          <button onClick={this.handleCloseModal}>Close Modal</button>
          <button onClick={this.handlePostAnnouncement}>Post</button>
        </Modal>
          <span id="messageResult"></span> 
      </div>
</div>
    )
  }}
}


 
export default Announcements;