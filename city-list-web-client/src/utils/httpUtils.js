const API_BASE_URL = 'http://localhost:8080/api';

const HEADERS = {
  mode: 'cors',
  cache: 'no-cache',
  credentials: 'same-origin',
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
};

function http(method, uri = '', body = {}) {
  return fetch(`${API_BASE_URL}/${uri}`, {
    ...HEADERS,
    method,
    body: JSON.stringify(body),
  })
    .then((response) => response.json())
    .then(({ data, message, code }) => {
        if (code) {
          console.log(`API Call error with code ${code} and message ${message}`);
          throw new Error(code);
        }
        return data;
    });
}

const httpUtils = {
  post(uri = '', body = {}) {
    return http('POST', uri, body);
  },

  put(uri = '', body = {}) {
    return http('PUT', uri, body);
  },

  get(uri = '') {
    return http('GET', uri);
  },
};

export default httpUtils;
