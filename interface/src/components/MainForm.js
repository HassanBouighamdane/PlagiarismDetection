import React, { useState } from 'react';

import { Form, Button, Card } from 'react-bootstrap';
import './form.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import { useEffect } from 'react';

import axios from 'axios';

export default function MainForm() {
  const [taux, setTaux] = useState('');
  const [files,setFiles] = useState([]);
  const [colorR ,setColorR] = useState('white');
  const [num,setNum] =useState(0);
  const [message, setMessage]= useState('');
  const [it,setIt] =useState(1);
  
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
      setNum(responseData[0]);
      setIt(2)
   
 
      
    } catch (error) {
      console.error(error);
      // Handle error if necessary
    }
  };
  
  useEffect(() => {
    // Run whenever the `taux` value changes
    const hue = 120 - (num * 120) / 100; // Calculate hue value based on num, with 0 as green and 100 as red
    const colorValue = `hsl(${hue}, 100%, 50%)`; // Set the color value using the calculated hue
if(it==2)
    setColorR(colorValue);
    if(num == 0) setMessage('Le texte est original.');
    if(num == 100) setMessage('Le texte est entièrement plagié !');
    if(num!=0 && num!=100) setMessage('');
  
  }, [num]);
 
    // Define the color range based on the taux value
   
   
  
  
  
  return (
    <>
   <div className="container" >
      <div >
        <Form style={{ width: '97%', margin : '1%', textAlign: 'center'}}>
          <Form.Group controlId="text">
            <Form.Label style={{  fontSize : '20px' }}>Saisir le texte :</Form.Label>
            <Form.Control as="textarea" rows={7} />
          </Form.Group>

          <Form.Group controlId="file">
            <Form.Label style={{  fontSize : '20px' }}>Choisir les fichiers de comparaison :</Form.Label>
            <Form.Control type="file" multiple  />
          </Form.Group>

          <Button variant="primary" style={{ width: '20%'}}  type="button" onClick={handleSubmit}>
            Comparer
          </Button>
        </Form>
      </div>
    </div>
    {taux && (
        
            <Card style={{ width: '80%', margin : '1%' , backgroundColor : '#fffbff' , textAlign: 'center', border : '2px' , borderStyle : 'solid'}}>
               <Card.Body>
              <Card.Title style={{  backgroundColor : colorR, fontSize : '40px' }} >Résultats</Card.Title>
             
              {taux && (
                <div className="mb-3" style={{  fontSize : '30px' ,}} >
                 
                  <div
                    id="taux"
                    
                    style={{ color: colorR }}
                  >
                    {taux} %
                  </div>
                  {message && <div>{message}</div>}
                </div>
              )}
              <hr></hr>
              {files.length > 0 && (
                <div style={{  fontSize : '20px' , }}>
                  <Form.Label style={{  fontSize : '20px' , fontStyle : 'bold'}}>
                    Le taux de similitude entre le texte et chaque document :
                  </Form.Label >
                  <div>
                  {files.map((file, index) => (
                    <Card.Text key={index} >
                      {file}
                    </Card.Text>
                  ))}
                  </div>
                </div>
              )}
            
            </Card.Body>
       </Card>
      
      )}
  
</>
  )};
  
