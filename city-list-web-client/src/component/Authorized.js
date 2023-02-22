import { Navigate } from 'react-router-dom';
import { useContext } from 'react';
import PropTypes from 'prop-types';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';
import roleUtils from '../utils/roleUtils';

function Authorized({ children, requiredRoles }) {
  const { isAuthenticated, self } = useContext(AuthContext);

  if (!isAuthenticated) {
    return <Navigate to={ROUTE_PATHS.SIGN_IN} replace />;
  }

  const hasAnyRequiredRole = roleUtils.hasAnyRole({ self, requiredRoles });
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
