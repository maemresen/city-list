import { post } from '../utils/httpUtils';
import { removeItem, setItem } from '../utils/localStorageUtils';
import LOCAL_STORAGE_KEYS from '../utils/constants/localStorageConstants';

export function login(username, password) {
  post('auth/login', { username, password }).then((data) => setItem(LOCAL_STORAGE_KEYS.JWT, JSON.stringify(data)));
}

export function logout() {
  removeItem(LOCAL_STORAGE_KEYS.JWT);
}
