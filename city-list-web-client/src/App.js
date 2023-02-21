import * as React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Cookies from 'js-cookie'
import Layout from './pages/Layout';
import NoPage from './pages/NoPage';
import ROUTES from './utils/constants/routes';
import COOKIE_KEY from './utils/constants/cookieKeys';
import authService from "./service/authService";

function App() {
  authService.signIn('admin', 'admin').then(({ accessToken, refreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, accessToken);
      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, refreshToken);
  });
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
