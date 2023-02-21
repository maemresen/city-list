import Cookies from 'js-cookie';
import { Navigate } from 'react-router-dom';
import COOKIE_KEY from '../utils/constants/cookieKeys';
import ROUTES from '../utils/constants/routes';

function Authorized({ children }) {
  const accessToken = Cookies.get(COOKIE_KEY.ACCESS_TOKEN);
  if (accessToken) {
    return children;
  }
  return <Navigate to={ROUTES.SIGN_IN.path} replace />;
}

export default Authorized;
