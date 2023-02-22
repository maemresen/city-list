import { toast } from 'react-toastify';
import queryString from 'query-string';
import API_CONSTANTS from './constants/apiConstants';

const OPTIONS = {
  mode: 'cors',
  cache: 'no-cache',
  credentials: 'same-origin',
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
};

function http({
  method, uri = '', body = null, token, queryParams = {},
}) {
  const headers = {
    ...OPTIONS.headers,
    Authorization: token ? `Bearer: ${token.accessToken}` : null,
  };
  return fetch(`${API_CONSTANTS.BASE_URL}/${uri}?${queryString.stringify(queryParams)}`, {
    ...OPTIONS,
    headers,
    method,
    body: body ? JSON.stringify(body) : null,
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
}

const httpUtils = {
  post({
    uri = '', body = null, token, queryParams = {},
  }) {
    return http({
      method: 'POST',
      uri,
      body,
      token,
      queryParams,
    });
  },

  put({
    uri = '', body = null, token, queryParams = {},
  }) {
    return http({
      method: 'PUT',
      uri,
      body,
      token,
      queryParams,
    });
  },

  get({
    uri = '', token, queryParams = {},
  }) {
    return http({
      method: 'GET',
      uri,
      token,
      queryParams,
    });
  },

  delete({
    uri = '', token, queryParams = {},
  }) {
    return http({
      method: 'DELETE',
      uri,
      token,
      queryParams,
    });
  },
};

export default httpUtils;
