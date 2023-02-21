import Cookies from 'js-cookie';
import httpUtils from '../utils/httpUtils';
import COOKIE_KEY from '../utils/constants/cookieKeys';

const authService = {
  signIn: (username, password) => httpUtils.post('auth/sign-in', { username, password }),
  refreshToken: (refreshToken) => httpUtils.post('auth/refresh-token', { refreshToken }),
};

export default authService;
