import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Announcements.css";



class Contact extends Component {
 constructor(props) {
    super(props);
    this.state = {
      announcements: []
    };
      this.getAnnouncements();
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
</div>
    )
  }
}
 
export default Contact;