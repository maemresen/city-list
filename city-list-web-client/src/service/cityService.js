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

  updatePhoto: ({
    token, cityId, photo = null,
  }) => {
    const formData = new FormData();
    formData.append('file', photo);
    return httpUtils.post({
      uri: `city/photo/${cityId}`,
      token,
      body: formData,
      extraHeaders: {
        'Content-Type': 'multipart/form-data',
        'Content-Length': photo.size
        ,
      },
    });
  },

  deletePhoto: ({
    token, cityId,
  }) => httpUtils.delete({
    uri: `city/photo/${cityId}`,
    token,
  }),
};

export default cityService;
