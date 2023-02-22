import {
  useCallback, useEffect, useMemo, useState,
} from 'react';
import Cookies from 'js-cookie';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import COOKIE_KEY from '../utils/constants/cookieKeys';
import authService from '../service/authService';
import AuthContext from '../context/AuthContext';
import userService from '../service/userService';
import ROUTE_PATHS from '../utils/constants/routePaths';

function AuthProvider({ children }) {
  const navigate = useNavigate();

  const [accessToken, setAccessToken] = useState(Cookies.get(COOKIE_KEY.ACCESS_TOKEN));
  const [refreshToken, setRefreshToken] = useState(Cookies.get(COOKIE_KEY.REFRESH_TOKEN));
  const [self, setSelf] = useState({});
  const isAuthenticated = useMemo(() => !!accessToken, [accessToken]);

  useEffect(() => {
    if (isAuthenticated) {
      userService.self({ accessToken, refreshToken }).then(setSelf);
    } else {
      setSelf({});
    }
  }, [accessToken, isAuthenticated]);

  const signIn = ({ username, password }) => authService.signIn(username, password)
    .then(({ accessToken: newAccessToken, refreshToken: newRefreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, newAccessToken);
      setAccessToken(newAccessToken);

      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, newRefreshToken);
      setRefreshToken(newRefreshToken);
      navigate(ROUTE_PATHS.HOME);
    }).then(() => toast.success('Sign In Successfully'));

  const signOut = () => {
    Cookies.remove(COOKIE_KEY.ACCESS_TOKEN);
    Cookies.remove(COOKIE_KEY.REFRESH_TOKEN);
    setAccessToken(null);
    setRefreshToken(null);
    navigate(ROUTE_PATHS.SIGN_IN);
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
