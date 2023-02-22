import {
  useCallback, useEffect, useMemo, useState,
} from 'react';
import Cookies from 'js-cookie';
import { toast } from 'react-toastify';
import COOKIE_KEY from '../utils/constants/cookieKeys';
import authService from '../service/authService';
import AuthContext from '../context/AuthContext';
import userService from '../service/userService';

function AuthProvider({ children }) {
  const [accessToken, setAccessToken] = useState(Cookies.get(COOKIE_KEY.ACCESS_TOKEN));
  const [refreshToken, setRefreshToken] = useState(Cookies.get(COOKIE_KEY.REFRESH_TOKEN));
  const [self, setSelf] = useState(Cookies.get(COOKIE_KEY.REFRESH_TOKEN));

  const isAuthenticated = useMemo(() => !!accessToken, [accessToken]);

  useEffect(() => {
    if (isAuthenticated) {
      userService.self({ accessToken, refreshToken }).then(setSelf);
    } else {
      setSelf(null);
    }
  }, [accessToken, isAuthenticated]);

  const signIn = ({ username, password }) => authService.signIn(username, password)
    .then(({ accessToken: newAccessToken, refreshToken: newRefreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, newAccessToken);
      setAccessToken(newAccessToken);

      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, newRefreshToken);
      setRefreshToken(newRefreshToken);
    }).then(() => toast.success('Sign In Successfully'));

  const signOut = () => {
    Cookies.remove(COOKIE_KEY.ACCESS_TOKEN);
    Cookies.remove(COOKIE_KEY.REFRESH_TOKEN);
    setAccessToken(null);
    setRefreshToken(null);
  };

  return (
    <AuthContext.Provider value={{
      accessToken, refreshToken, isAuthenticated, self, signIn, signOut,
    }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;
