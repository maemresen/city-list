import { Navigate } from 'react-router-dom';
import { useContext } from 'react';
import PropTypes from 'prop-types';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';
import JWT_CLAIM_KEY from '../utils/constants/jwtClaimKeys';
import jwtUtils from '../utils/jwtUtils';

function Authorized({ children, requiredRoles }) {
  const { accessToken, isAuthenticated } = useContext(AuthContext);

  if (!isAuthenticated) {
    return <Navigate to={ROUTE_PATHS.SIGN_IN} replace />;
  }

  const role = jwtUtils.getClaim({ jwt: accessToken, claimKey: JWT_CLAIM_KEY.USER_ROLE });
  const hasAnyRequiredRole = requiredRoles.some((requireRole) => requireRole === role);
  if (!hasAnyRequiredRole) {
    return <Navigate to={ROUTE_PATHS.UNAUTHORIZED} replace />;
  }

  return children;
}

Authorized.propTypes = {
  requiredRoles: PropTypes.arrayOf(PropTypes.string),
};

Authorized.defaultProps = {
  requiredRoles: [],
};

export default Authorized;
