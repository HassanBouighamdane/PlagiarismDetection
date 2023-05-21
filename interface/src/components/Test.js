import React, { useState } from 'react';

function MainForm() {
  const [name, setName] = useState('');
  const [age, setAge] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an object with the form data
    const formData = {
      name: name,
      age: age
    };

    // Send a POST request to the Spring Boot backend
    fetch('http://localhost:8080/api/form', {
      method: 'POST',
      headers: {
        'Content-Type': 'text'
      },
      body: 'JSON.stringify(formData)'
    })
      .then(response => response.json())
      .then(data => {
        // Handle the response from the backend
        console.log(name);
      })
      .catch(error => {
        // Handle errors
        console.error(error);
      });
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </label>
        <br />
        <label>
          Age:
          <input type="number" value={age} onChange={(e) => setAge(e.target.value)} />
        </label>
        <br />
        <button type="submit" onClick={handleSubmit}>Submit</button>
      </form>
    </div>
  );
}

export default MainForm;
