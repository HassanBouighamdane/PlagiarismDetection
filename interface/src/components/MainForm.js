import React, { useState } from 'react';

import { Form, Button, Card } from 'react-bootstrap';


import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';

export default function MainForm() {
  const [taux, setTaux] = useState('');
  const [files,setFiles] = useState([]);
  
   
  const handleSubmit = async () => {
    const textInput = document.getElementById('text');
    const text = textInput.value;
    const fileInput = document.getElementById('file');
    const file = fileInput.files;
  
    try {
      const formData = new FormData();
      formData.append('text', text);
      for (let i = 0; i < fileInput.files.length; i++) {
        formData.append('files', fileInput.files[i]);
      }
  
      const response1 = await axios.post('http://localhost:8080/get', formData);
  
      const responseData = response1.data;
      const filesData = responseData.slice(1); // Extract values from index 1 to the end of the array
  
      setTaux("Le pourcentage de plagiat est : " + responseData[0]);
      setFiles(filesData);
    } catch (error) {
      console.error(error);
      // Handle error if necessary
    }
  };
  const getTauxColor = () => {
    // Define the color range based on the taux value
    const red = Math.round((1 - taux) * 255);
    const green = Math.round(taux * 255);
    const blue = 0;
    return `rgb(${red}, ${green}, ${blue})`;
  };
  

  return (
    <div className="container text-center">
    <div className="col-md-9 mx-auto">
      <Form>
        <Form.Group controlId="text">
          <Form.Label>Saisir le texte :</Form.Label>
          <Form.Control as="textarea" rows={7} columns={9} />
        </Form.Group>

        <Form.Group controlId="file">
          <Form.Label>Choisir un fichier :</Form.Label>
          <Form.Control type="file" multiple />
        </Form.Group>

        <Button variant="primary" type="button" onClick={handleSubmit}>
          Comparer
        </Button>
      </Form>

      <Card className="mt-4">
        <Card.Body>
          <Card.Title>Résultats</Card.Title>
          {taux && (
            <div className="mb-3">
              <Form.Label htmlFor="taux" className="form-label result-label">
                Taux de plagiat :
              </Form.Label>
              <Card.Text id="taux" className="result-value">
              <div
                  id="taux"
                  className="result-value"
                  style={{ color: getTauxColor() }}
                >
                  {taux}
                </div>
              </Card.Text>
            </div>
          )}
          {files.length > 0 && (
            <div>
              <Form.Label className="form-label result-label">
                Fichiers similaires :
              </Form.Label>
              {files.map((file, index) => (
                <Card.Text key={index} className="result-value">
                  {file}
                </Card.Text>
              ))}
            </div>
          )}
        </Card.Body>
      </Card>
    </div>
    
  </div>  )};
  
