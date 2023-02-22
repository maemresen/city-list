import httpUtils from '../utils/httpUtils';

const userService = {
  getSelf: (token) => httpUtils.get({
    uri: 'user/self',
    token,
  }),
};

export default userService;
