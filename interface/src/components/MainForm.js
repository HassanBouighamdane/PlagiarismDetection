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
    const fileInput = document.getElementById('file');
    const files = fileInput.files;
    
    const formData = new FormData();
    formData.append('text', value);
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
  
    try {
      const response = await fetch('http://localhost:8080/api/form', {
        method: 'POST',
        body: formData
      });
  
      const responseData = await response.text();
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
        <textarea  id="text" name="text" rows={7} columns={9} /> <br/>
        {/* <FileInput /> */}
        <input type='file' multiple="true" id='file' ></input>
        
        <button type="button" onClick={handleSubmit}>Comparer</button>

        {responseMessage && <p>{responseMessage}</p>}
      </div>
    </div>
  )};
