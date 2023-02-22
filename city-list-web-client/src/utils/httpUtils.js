import { toast } from 'react-toastify';
import queryString from 'query-string';

const API_BASE_URL = 'http://localhost:8080/api';

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
  return fetch(`${API_BASE_URL}/${uri}?${queryString.stringify(queryParams)}`, {
    ...OPTIONS,
    headers: {
      ...OPTIONS.headers,
      Authorization: token ? `Bearer: ${token.accessToken}` : null,
    },
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

  get({ uri = '', token, queryParams = {} }) {
    return http({
      method: 'GET',
      uri,
      token,
      queryParams,
    });
  },
};

export default httpUtils;
