import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import FileInput from './FilesInput';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';

export default function MainForm() {
  const [responseMessage, setResponseMessage] = useState('');
  
   
  const handleSubmit = async () => {
    const textInput = document.getElementById('text');
    const value = textInput.value;
    const data = {
      text: value
    };
  
    try {
      await fetch('http://localhost:8080/api/form', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: value
      });
  
      const response = await fetch('http://localhost:8080/get');
      const responseData = await response;
  
      setResponseMessage(responseData);
    } catch (error) {
      console.error(error);
      // Handle error if necessary
    }
  };
  
 
  

  return (
    <div className="container text-center">
      <div className="col-md-9 mx-auto">
        <label htmlFor="text">Saisir le texte :</label> <br />
        <input id="text" name="text" rows={7} />
        <FileInput />
        <button type="button" onClick={handleSubmit}>Comparer</button>

        {responseMessage && <p>{responseMessage}</p>}
      </div>
    </div>
  )};
