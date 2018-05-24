import React, { Component } from "react";
import ReactDOM from 'react-dom';
import Menu from "./Menu";
import Header from "./Header";
require('react-bootstrap');

class Main extends Component {
  render() {
    return (
       <div><Menu/></div>
    );
  }
}

export default Main;