import React, { Component } from 'react';
import fii from './fii.png';
import './App.css';
import './Css/Style.css'
//import Menu from './components/menu.jsx';
const API = 'https://localhost:8085/oauth/token'
const base64 = require('base-64');
var qs = require('qs');

class App extends Component {
	constructor(props) {
		super(props);

		this.state = {
			users: [], data: []
		};
	}

	render() {

//const {users, isLoading} = this.state;


//if (isLoading) {
// return <p>Loading...</p>;
//}

return (
	//<div className="App">
	//<header className="App-header">
	//<img src={fii} className="App-logo" alt="logo" />
	//<h1 className="App-title">Welcome to Fii Iasi</h1>
	//</header>
	//<p className="App-intro">
	//</p>
	//<div>
	<ul>
	{this.state.users.map(user => (
		<li key={user.id}>{user.firstName}</li>
		))}
		</ul>
		//</div>
		//</div>
		);
}

componentDidMount() {

  /*fetch(API,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
		'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')
      },
      body: qs.stringify({
        'grant_type': 'password',
        'username': 'admin.admin',
        'password': 'jwtpass'
      })
    }) .then(response =>
    response.json());
    */


////bun
    

    /*let config = {
    	method: 'POST',
    	headers: { 'Content-Type':'application/x-www-form-urlencoded', 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')},
    	body: qs.stringify({
    		'grant_type': 'password',
    		'username': 'admin.admin',
    		'password': 'jwtpass'
    	})
    }

    fetch(API, config)
    .then(response =>
    	response.json()) .then((data) => {	 localStorage.setItem('token', data.access_token);
    		this.setState( { data });})
*/





/////////////////







  /*var payload = {
'grant_type': 'password',
'username': 'admin.admin',
'password': 'jwtpass'
};

var data = new FormData();
data.append( "json", JSON.stringify( payload ) );

fetch(API,
{
method: "POST",
headers: {
'Content-Type': 'application/x-www-form-urlencoded',
'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100')
},
body: data
})
.then(function(res){ return res.json(); })
.then(function(data){ alert( JSON.stringify( data ) ) })


 var form = new FormData();
form.append('grant_type', 'password');

var obj = {  
method: 'POST',
headers: {
'Content-Type': 'application/x-www-form-urlencoded',
'Authorization': 'Basic '+base64.encode('testjwtclientid:XY7kmzoNzl100')
},
body: form
}  

fetch(API, obj)  
.then(response => response.json())
.then(data => this.setState({data}));*/




//bun




/*console.log(localStorage.getItem('token'))

let userConfig = {
	method: 'GET',
	headers: {  'Authorization': 'Bearer '+localStorage.getItem("token")}
}

fetch('https://localhost:8085/fii/users', userConfig)
   //.then(data => this.setState({users: data, isLoading: false}));
   .then(response => response.json())
   .then((users) => {	console.log(users);
   	this.setState( { users });})
*/

///////////////////

  /*fetch(API, { 
method: 'post', 
mode: 'no-cors',
headers: new Headers({
 'Authorization': 'Basic '+btoa('testjwtclientid:XY7kmzoNzl100'), 
 'Content-Type': 'application/json'
}), 
body: 'username=admin.admin&password=jwtpass&grant_type=password'
})
.then(response => response.json())
.then(data => this.setState({ hits: data.hits }));*/
}
}

export default App