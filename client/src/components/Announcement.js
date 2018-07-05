  import React, { Component } from "react";
  export default class announcement extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		  id: this.props.id,
        info : this.props.info,
        expiry_date : this.props.expiry_date,
        publishdate : this.props.publishDate
    };
  }

  render() { 
   return null; 
}
}