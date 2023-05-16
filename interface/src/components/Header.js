import React from 'react';
import logo from '../images/Logo_inpt.PNG';
import  '../style/Header.css';


export default function Header() {
  return (
    <header>
      <div className="container">
        <div className="header-content">
          <a  href="#">
            <img className='logo' src={logo} alt="Logo" />
          </a>
          <div className="text-container">
            <h1 className="display-4 heading">Detection du Plagiat</h1>
            <p className="lead subheading">
            Découvrez si votre travail a été copié avec notre puissant outil de détection de plagiat.
            </p>
          </div>
        </div>
      </div>

      <hr></hr>
      <hr></hr>
    </header>
  
  )
}
