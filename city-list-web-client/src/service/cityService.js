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

  getById: ({
    token, id,
  }) => httpUtils.get({
    uri: `city/${id}`,
    token,
  }),

  update: ({
    token, city,
  }) => httpUtils.put({
    uri: 'city',
    token,
    body: { ...city },
  }),
};

export default cityService;
