import React from 'react'

import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import FileInput from './FilesInput';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
export default function MainForm() {
  return (
    <div className="container text-center">
    <div className="col-md-9 mx-auto">
      <InputGroup className='form-control'>
        <InputGroup.Text className='form-check-label'>Saisir le text :</InputGroup.Text>
        <Form.Control as="textarea" aria-label="Saisir le text" rows='7' />
        <FileInput ></FileInput>   
      </InputGroup>
      
        <Button>Comparer</Button>
      
    </div>
  </div>
  
  
  )
}
