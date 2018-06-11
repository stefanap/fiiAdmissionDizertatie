  import React, { Component } from "react";
  export default class setting extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		  id: this.props.id,
        startDate : this.props.startDate,
        endDate : this.props.endDate
    };
  }

  render() { 
   return null; 
}
}