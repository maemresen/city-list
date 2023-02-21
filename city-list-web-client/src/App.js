import * as React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './pages/Layout';
import NoPage from './pages/NoPage';
import ROUTES from './utils/constants/routes';

function App() {
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
