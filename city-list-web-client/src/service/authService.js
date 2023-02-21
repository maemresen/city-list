import Cookies from 'js-cookie';
import httpUtils from '../utils/httpUtils';
import COOKIE_KEY from '../utils/constants/cookieKeys';

const authService = {
  signIn: (username, password) => httpUtils.post('auth/sign-in', { username, password })
    .then(({ accessToken, refreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, accessToken);
      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, refreshToken);
    }),
  refreshToken: (refreshToken) => httpUtils.post('auth/refresh-token', { refreshToken }),
  signOut: () => {
    Cookies.delete(COOKIE_KEY.ACCESS_TOKEN);
    Cookies.delete(COOKIE_KEY.REFRESH_TOKEN);
  },
};

export default authService;
