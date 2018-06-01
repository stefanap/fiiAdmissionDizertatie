import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Announcements.css";
import ReactDOM from 'react-dom';
import Modal from 'react-modal';


class Contact extends Component {


 constructor(props) {
    super(props);
    this.state = {
      announcements: [],
      showModal: false
    };
      this.getAnnouncements();
       this.handleOpenModal = this.handleOpenModal.bind(this);
    this.handleCloseModal = this.handleCloseModal.bind(this);
  }
  
  handleOpenModal () {
    this.setState({ showModal: true });
  }
  
  handleCloseModal () {
    this.setState({ showModal: false });
    var token = localStorage.getItem('token');
    let config = {
      method: 'POST',
      headers: { 'Content-Type':'application/json','Authorization': 'Bearer '+ token},
      body: {}
    }
    const API='https://localhost:8085/users'
    fetch(API, config)
    .then(response =>
      response.json()) .then((data) => { 
        {

        }
      })
  }
  

  getAnnouncements()
    {
      const API = 'https://localhost:8085/announcements'
      let config = {
      method: 'GET',
      }

    fetch(API, config)
   .then(response =>
      response.json()) .then((data) => { 
        this.setState( { announcements:data})}) 

    }


  render() {
    return (
      <div>
<ul>

     {this.state.announcements.map(function(announcement, i){
       return <li class="announcement" key={i}>{announcement.info}</li>
     })}
   </ul>
  <div>
        <button onClick={this.handleOpenModal}>Trigger Modal</button>
        <Modal
           isOpen={this.state.showModal}
           contentLabel="Modal"
           style={{
              content: {
                width: '300px',
                height:'300px',
                left: '700px'
              }
            }}
        >
           <textarea name="comment" placeholder=
           "Enter announcement here..."></textarea>
          <button onClick={this.handleCloseModal}>Close Modal</button>
        </Modal>
      </div>
</div>
    )
  }
}


 
export default Contact;