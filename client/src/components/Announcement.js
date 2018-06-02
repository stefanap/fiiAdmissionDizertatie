  import React, { Component } from "react";
  export default class announcement extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		  id: this.props.id,
        info : this.props.info,
        expiryDate : this.props.expiryDate,
        publishdate : this.props.publishDate
    };
  }

  render() { 
   return null; 
}
}