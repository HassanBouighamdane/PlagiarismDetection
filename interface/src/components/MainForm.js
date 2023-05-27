import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import FileInput from './FilesInput';
import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';

export default function MainForm() {
  const [responseMessage, setResponseMessage] = useState('');
  
   
  const handleSubmit = async () => {
    const textInput = document.getElementById('text');
    const text = textInput.value;
    const fileInput = document.getElementById('file');
    const file = fileInput.files;
    
   
  
    try {
      const formData = new FormData();
      formData.append('text',text);
      // Iterate over each file in the fileInput.files array
      for (let i = 0; i < fileInput.files.length; i++) {
        formData.append('files', fileInput.files[i]);
      }
  
      const response1 = await axios.post('http://localhost:8080/get', formData);
    
      setResponseMessage(response1.data[1])
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
        <input type='file' multiple={true} id='file' ></input>
        
        <button type="button" onClick={handleSubmit}>Comparer</button>

        {responseMessage && <p>{responseMessage}</p>}
      </div>
    </div>
  )};
