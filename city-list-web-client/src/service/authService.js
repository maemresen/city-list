import httpUtils from '../utils/httpUtils';

const authService = {
  signIn: (username, password) => httpUtils.post({
    uri: 'auth/sign-in',
    body: {
      username, password,
    },
  }),
  refreshToken: (refreshToken) => httpUtils.post('auth/refresh-token', { refreshToken }),
};

export default authService;
