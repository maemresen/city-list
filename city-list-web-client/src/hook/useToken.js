import { useState } from 'react';
import Cookies from 'js-cookie';
import COOKIE_KEY from '../utils/constants/cookieKeys';

const useToken = () => {
  const [accessToken, setAccessToken] = useState(Cookies.get(COOKIE_KEY.ACCESS_TOKEN));
  const [refreshToken, setRefreshToken] = useState(Cookies.get(COOKIE_KEY.REFRESH_TOKEN));

  return {
    accessToken, refreshToken, setAccessToken, setRefreshToken,
  };
};

export default useToken;
