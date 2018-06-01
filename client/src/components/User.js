  import React, { Component } from "react";
  export default class user extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
          userName : this.props.username,
          firstName : this.props.firstName,
          lastName : this.props.lastName,
          password : this.props.password, 
          email : this.props.email
    };
  }

  render() { 
   return null; 
}
}


