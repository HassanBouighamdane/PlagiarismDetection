import React from 'react'
import { useState } from 'react';
import Form from 'react-bootstrap/Form';

export default function FileInput() {
    
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [filePreviews, setFilePreviews] = useState([]);

  const handleFileChange = (e) => {
    const files = e.target.files;
    setSelectedFiles([...selectedFiles, ...files]);
  
 
  const previews = [];
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const reader = new FileReader();
      reader.onload = () => {
        previews.push(reader.result);
        if (previews.length === files.length) {
          setFilePreviews(previews);
        }
      };
      reader.readAsDataURL(file);
    }
};

  return (
    <Form >
        <Form.Group controlId="formFile">
          <Form.Label>Choisir les documents de comparaison :</Form.Label>
          <Form.Control type="file" onChange={handleFileChange} multiple />
        </Form.Group>
       
      {filePreviews.length > 0 &&
          filePreviews.map((preview, index) => (
            <object key={index} data={preview} type={preview.type} width="20%" height="60%">
                <p>Preview could not be loaded.</p>
            </object>          ))
       }
                 </Form>
   
  );
}
