import httpUtils from '../utils/httpUtils';

const authService = {
  signIn: (username, password) => httpUtils.post('auth/sign-in', { username, password }),
  refreshToken: (refreshToken) => httpUtils.post('auth/refresh-token', { refreshToken }),
};

export default authService;
