import ROUTE_PATHS from './routePaths';
import Home from '../../pages/Home';
import SignIn from '../../pages/SignIn';
import Cities from '../../pages/Cities';
import Unauthorized from '../../pages/Unauthorized';
import ROLE from './roles';

const ROUTES = Object.freeze({
  HOME: {
    key: 'home',
    name: 'Home',
    path: ROUTE_PATHS.HOME,
    component: <Home />,
    index: true,
    isPublic: true,
  },
  SIGN_IN: {
    key: 'sign-in',
    name: 'SignIn',
    path: ROUTE_PATHS.SIGN_IN,
    component: <SignIn />,
    isPublic: true,
  },
  CITIES: {
    key: 'cities',
    name: 'Cities',
    path: ROUTE_PATHS.CITIES,
    component: <Cities />,
    requiredRoles: [ROLE.ROLE_ALLOW_EDIT, ROLE.ROLE_READ_ONLY],
  },
  UNAUTHORIZED: {
    key: 'unauthorized',
    name: 'Unauthorized',
    path: ROUTE_PATHS.UNAUTHORIZED,
    component: <Unauthorized />,
    isPublic: true,
  },
});

export default ROUTES;
