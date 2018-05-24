import React from 'react';
import ReactDOM from 'react-dom';
//import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
//import Menu from "./components/Menu";
import Main from "./components/Main";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";


ReactDOM.render(
	<div><Main/></div>, document.getElementById('root')
	);
registerServiceWorker();

//ReactDOM.render(
  //<Menu/>, 
  //document.querySelector("#container")
//);
