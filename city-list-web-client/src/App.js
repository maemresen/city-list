import * as React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import Layout from './pages/Layout';
import NoPage from './pages/NoPage';
import ROUTES from './utils/constants/routes';
import AuthProvider from './provider/AuthProvider';
import Authorized from './component/Authorized';

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            {Object.keys(ROUTES).map((key) => {
              const {
                path, component, index, isPublic, requiredRoles,
              } = ROUTES[key];
              const element = isPublic
                ? component
                : <Authorized requiredRoles={requiredRoles}>{component}</Authorized>;
              return <Route index={index} path={path} element={element} />;
            })}
            <Route path="*" element={<NoPage />} />
          </Route>
        </Routes>
      </AuthProvider>
      <ToastContainer theme="colored" position="bottom-left" />
    </BrowserRouter>
  );
}

export default App;
