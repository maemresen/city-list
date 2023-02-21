import { useEffect, useState } from 'react';
import AuthContext from '../context/AuthContext';
import useToken from '../hook/useToken';

function AuthProvider({ children }) {
  const [token, setToken] = useState({});
  const { accessToken, refreshToken } = useToken();
  useEffect(() => {
    setToken({ accessToken, refreshToken });
  }, [accessToken, refreshToken]);
  return (
    <AuthContext.Provider value={{ token }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;
