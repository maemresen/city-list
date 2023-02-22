import httpUtils from '../utils/httpUtils';

const authService = {
  self: (token) => httpUtils.get({
    uri: 'user/self',
    token,
  }),
};

export default authService;
