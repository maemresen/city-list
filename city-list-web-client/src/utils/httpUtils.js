const API_BASE_URL = 'http://localhost:8080/api';

async function http(method, uri = '', body = {}) {
  const response = await fetch(`${API_BASE_URL}/${uri}`, {
    method,
    mode: 'cors',
    cache: 'no-cache',
    credentials: 'same-origin',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    },
    body: JSON.stringify(body),
  });

  const { data, message, code } = await response.json();

  if (code) {
    console.error(`Error ${code}, ${message}`);
  }

  return data;
}

export async function post(uri = '', body = {}) {
  return http('POST', uri, body);
}

export async function put(uri = '', body = {}) {
  return http('PUT', uri, body);
}

export async function get(uri = '') {
  return http('GET', uri);
}
