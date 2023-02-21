import * as React from 'react';
import Button from '@mui/material/Button';
import { post } from './utils/http-utils';
import './App.css';

function App() {
  post('http://localhost:8080/api/auth/login', {
    username: 'admin',
    password: 'admin',
  })
    .then((data) => console.log('login response', data));

  return (
    <div>
      <Button variant="contained">Hello Worlssd</Button>
    </div>
  );
}

export default App;
