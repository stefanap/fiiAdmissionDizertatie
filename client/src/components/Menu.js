  import React, { Component } from "react";
  import {
    Route,
    NavLink,
    HashRouter
  } from "react-router-dom";
  import Register from "./Register";
  import Admission from "./Admission";
  import Login from "./Login";
  import Announcements from "./Announcements";
  import Profile from "./Profile";
  import ManageUsers from "./ManageUsers";
  import ViewUsers from "./ViewUsers";
  import Settings from "./Settings";
  import Reports from "./Reports";
  import "./Menu.css";
  import fii from '../fii.png';
  import sweetAlert from 'sweetalert';
import LocalizedStrings from 'react-localization';


var english={
   login:"Login",
   register:"Register",
   profile:"Profile",
   announcements:"Announcements",
   manage:"Manage users",
   registerAdmission:"Register for admission",
   logout:"Logout",
   settings:"Settings",
   welcome:"Welcome",
   notLoggedIn:"You are not logged in",
   reports:"Reports"
 }

 var romanian={
   login:"Logare",
   register:"Inregistrare",
   profile:"Profil",
   announcements:"Anunturi",
   manage:"Administreaza userii",
   registerAdmission:"Inregistrare penru admitere",
   logout:"Delogare",
   settings:"Setari",
   welcome:"Bun venit",
   notLoggedIn:"utilizator nelogat",
   reports:"Rapoarte"
 }
let strings = new LocalizedStrings({
 en:english,
 ro: romanian
});

  require('react-bootstrap');

 function logout()
 {
var language=localStorage.getItem('language');
localStorage.clear();
localStorage.setItem('language',language);
window.location.reload();

 }

  class Main extends Component {

    constructor(props) {
    super(props);
    this.state = {
      show:strings.notLoggedIn
    };
    document.onmousemove = this.resetTimer;
    document.onclick = this.resetTimer;
    var language=localStorage.getItem('language');
     this.resetTimer = this.resetTimer.bind(this);
    console.log(language);
    if(language=='ro')
    strings.setLanguage('ro');
    else 
    strings.setLanguage('en');
    if (localStorage.getItem('username')!= null) this.state.show=strings.welcome;
    }



resetTimer() {
  var timeout;
  clearTimeout(timeout);
  timeout = setTimeout(function(){localStorage.clear();  sweetAlert('Your session has expired, please log in again!'); window.location.reload();}, 30*60*1000);
  //this.forceUpdate();
}

    changeLanguage()
    {
    var language=localStorage.getItem('language');
    if(language=='en')//strings._language=='en')
    {
    language='ro';
    }
    else
    {//strings.setLanguage('en');
    language='en';
    }
    localStorage.setItem('language',language);
    strings.setLanguage(language);
    window.location.reload();
    //this.forceUpdate();
    }


    render() {
      return (

        <div>
       
               

<HashRouter>
<div class="row">
<div className="leftMenu col-md-2">
  <div className="App">
                        <header className="App-header">
                                <img src={fii} className="App-logo" alt="logo" />
                                <br/>
                                 <br/>
                                <p> {this.state.show} </p>
                        </header>
                        <p className="App-intro">
                        </p>
                </div>
                <ul>
                                        <li class="var_nav"><NavLink to="/login">
                                                <div class="link_bg"></div>
                                                <div class="link_title">
                                                        <div class="icon"> 
                                                                <i class="glyphicons glyphicons-unlock"></i>
                                                        </div>
                                                        <a ><span>{strings.login}</span></a>
                                                </div>
                                        </NavLink>
                                </li>
                                <li class="var_nav"><NavLink exact to="/">
                                        <div class="link_bg"></div>
                                        <div class="link_title">
                                                <div class="icon"> 
                                                        <i class="icon-lightbulb icon-2x"></i>
                                                </div>
                                                <a ><span>{strings.register}</span></a>
                                        </div>
                                </NavLink>
                        </li>
                        <li class="var_nav"><NavLink to="/announcements">
                                <div class="link_bg"></div>
                                <div class="link_title">
                                        <div class="icon"> 
                                                <i class="icon-wrench icon-2x"></i>
                                        </div>
                                        <a ><span>{strings.announcements}</span></a>
                                </div>
                        </NavLink>
                </li>
                <li class="var_nav"><NavLink to="/profile">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a ><span>{strings.profile}</span></a>
                        </div>
                        </NavLink>
                </li>
                <li class="var_nav"><NavLink to="/admission">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a ><span>{strings.registerAdmission}</span></a>
                        </div>
                        </NavLink>
                </li>
                <li class="var_nav"><NavLink to="/viewUsers">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a ><span>{strings.manage}</span></a>
                        </div>
                        </NavLink>
                </li>
                <li class="var_nav"><NavLink to="/settings">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a ><span>{strings.settings}</span></a>
                        </div>
                        </NavLink>
                </li>
                 <li class="var_nav"><NavLink to="/reports">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a ><span>{strings.reports}</span></a>
                        </div>
                        </NavLink>
                </li>
                <li class="var_nav">
                        <div class="link_bg"></div>
                        <div class="link_title">
                                <div class="icon"> 
                                        <i class="icon-briefcase icon-2x"></i>
                                </div>
                                <a onClick={() => { logout() }}><span>{strings.logout}</span></a>
                        </div>
                </li>
        </ul>

</div>
        <div className="content col-md-10">
                <Route exact path="/" component={Register}/>
                <Route path="/login" component={Login}/>
                <Route path="/announcements" component={Announcements}/>
                <Route path="/profile" component={Profile}/>
                <Route path="/admission" component={Admission}/>
                <Route path="/viewUsers" component={ManageUsers}/>
                <Route path="/settings" component={Settings}/>
                <Route path="/reports" component={Reports}/>
        </div>
               <button class='changeLanguageButton' onClick={() => {  this.changeLanguage() }}>Translate</button>
        </div>

</HashRouter>



        </div>



        );
      }
    }

    export default Main;