  import React, { Component } from "react";
  export default class user extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		  id: this.props.id,
          username : this.props.username,
          firstName : this.props.firstName,
          lastName : this.props.lastName,
          password : this.props.password, 
          email : this.props.email,
          admissionStatus: this.props.admissionStatus,
          registerDate : this.props.registerDate,
          registerNumber: this.props.registerNumber,
          role: this.props.role,
          has2FA :this.props.has2FA
    };
  }

  render() { 
   return null; 
}
}


