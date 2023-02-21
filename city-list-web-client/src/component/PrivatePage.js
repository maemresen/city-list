import { Navigate } from 'react-router-dom';
import ROUTES from '../utils/constants/routes';
import useAuth from '../hook/useAuth';

function Authorized({ children }) {
  // const { isAuthenticated } = useAuth();
  // if (isAuthenticated()) {
    return children;
  // }
  // return <Navigate to={ROUTES.SIGN_IN.path} replace />;
}

export default Authorized;
