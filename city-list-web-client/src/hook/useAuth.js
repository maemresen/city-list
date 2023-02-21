import Cookies from 'js-cookie';
import { useState } from 'react';
import COOKIE_KEY from '../utils/constants/cookieKeys';
import authService from '../service/authService';

const useAuth = () => {
  const [accessToken, setAccessToken] = useState(Cookies.get(COOKIE_KEY.ACCESS_TOKEN));
  const [refreshToken, setRefreshToken] = useState(Cookies.get(COOKIE_KEY.REFRESH_TOKEN));
  const [isAuthenticated, setIsAuthenticated] = useState(!!Cookies.get(COOKIE_KEY.ACCESS_TOKEN));

  const signIn = (username, password) => authService.signIn(username, password)
    .then(({ accessToken: newAccessToken, refreshToken: newRefreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, newAccessToken);
      setAccessToken(newAccessToken);

      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, newRefreshToken);
      setRefreshToken(newRefreshToken);

      setIsAuthenticated(true);
    });

  const signOut = () => {
    Cookies.remove(COOKIE_KEY.ACCESS_TOKEN);
    Cookies.remove(COOKIE_KEY.REFRESH_TOKEN);

    setIsAuthenticated(false);
  };

  return {
    accessToken, refreshToken, isAuthenticated, signIn, signOut,
  };
};

export default useAuth;
