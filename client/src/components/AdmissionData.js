  import React, { Component } from "react";
  export default class admissionData extends Component {
  constructor(props) {
  	super(props);
  	this.state = {
  		    cnp: this.props.cnp,
          address : this.props.address,
          examSubject : this.props.examSubject,
          telephone : this.props.telephone,
          bacGrade : this.props.bacGrade, 
          generalGrade : this.props.generalGrade,
          highschool: this.props.highschool,
          civil_state : this.props.civil_state,
          language: this.props.language,
          hasDisabilities: this.props.hasDisabilities,
          additionalInformation : this.props.additionalInformation,
          admissionType: this.props.admissionType,
          country : this.props.country,
          region: this.props.region,
          city: this.props.city
    };
  }

  render() { 
   return null; 
}
}


