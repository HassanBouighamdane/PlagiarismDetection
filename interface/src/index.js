import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
//mport App from './App';
import MainForm from './components/MainForm';
import Header from './components/Header';
import reportWebVitals from './reportWebVitals';
import Test from './components/Test';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
     
    <div className='container home'>
    <Header />
    <MainForm />
   
    </div>
    
  </React.StrictMode>


);

reportWebVitals();
