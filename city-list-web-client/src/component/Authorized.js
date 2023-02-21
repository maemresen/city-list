import { Navigate } from 'react-router-dom';
import { useContext } from 'react';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';

function Authorized({ children }) {
  const { isAuthenticated } = useContext(AuthContext);
  if (isAuthenticated) {
    return children;
  }
  return <Navigate to={ROUTE_PATHS.SIGN_IN} replace />;
}

export default Authorized;
