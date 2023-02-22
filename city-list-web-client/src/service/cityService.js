import httpUtils from '../utils/httpUtils';

const cityService = {

  getCities: ({
    token, page, size, name,
  }) => httpUtils.get({
    uri: 'city',
    token,
    queryParams: {
      page, size, name,
    },
  }),
};

export default cityService;
