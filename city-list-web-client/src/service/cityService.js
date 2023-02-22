import queryString from 'query-string';
import { toast } from 'react-toastify';
import httpUtils from '../utils/httpUtils';
import API_CONSTANTS from '../utils/constants/apiConstants';

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
    token, cityId, photo,
  }) => {
    const formData = new FormData();
    formData.append('file', photo);
    return fetch(`${API_CONSTANTS.BASE_URL}/city/photo/${cityId}`, {
      method: 'POST',
      headers: {
        Authorization: token ? `Bearer: ${token.accessToken}` : null,
      },
      body: formData,
    })
      .then((response) => response.json())
      .then(({ data, message, errorCode }) => {
        if (errorCode) {
          console.log(`Failed to fetched data with '${errorCode}' message. Error Code: ${errorCode} for ${uri}`);
          throw new Error(errorCode);
        } else {
          console.log(`Data successfully fetched with '${message}' message from service for ${uri}`);
        }
        return data;
      }).catch((error) => {
        toast.error(`Service Error - ${error}`);
        throw error;
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
