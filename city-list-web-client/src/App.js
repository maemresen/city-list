import * as React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './pages/Layout';
import './App.css';
import NoPage from './pages/NoPage';
import * as loginService from './service/authService';
import ROUTES from './utils/constants/routes';

function App() {
  loginService.login('admin', 'admin');
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          {Object.keys(ROUTES).map((key) => {
            const { path, component, index } = ROUTES[key];
            return <Route index={index} path={path} element={component} />;
          })}
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
