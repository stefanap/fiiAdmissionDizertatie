import React, { Component } from "react";
import { Form, Text } from 'react-form';
import "./Home.css";

class Home extends Component {

 handleSubmit(e){
    alert('a');
 }

  render() {

  	 const validate = value => ({
  error: !value || !/Hello World/.test(value) ? "Input must contain 'Hello World'" : null,
  warning: !value || !/^Hello World$/.test(value) ? "Input should equal just 'Hello World'" : null,
  success: value && /Hello World/.test(value) ? "Thanks for entering 'Hello World'!" : null
})

    return (
      <Form>
  {formApi => (
    <form onSubmit={formApi.submitForm} id="form1" className="mb-4">
      <label htmlFor="hello">Nume</label>
      <Text field="hello" id="hello" validate={validate} />
      <label htmlFor="hello">Prenume</label>
      <Text field="hello" id="hello" validate={validate} />
      <button onClick={this.handleSubmit.bind(this)} type="submit" className="btn btn-primary">
        Submit
      </button>
    </form>
  )}
</Form>
    );
  }
}
 
export default Home;