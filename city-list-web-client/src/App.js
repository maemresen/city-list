import * as React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { post } from './utils/http-utils';
import Layout from './pages/Layout';
import Home from './pages/Home';
import './App.css';
import Login from './pages/Login';
import NoPage from './pages/NoPage';

function App() {
  post('http://localhost:8080/api/auth/login', {
    username: 'admin',
    password: 'admin',
  })
    .then((data) => console.log('login response', data));

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="login" element={<Login />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
