import ROUTE_PATHS from './routePaths';
import Home from '../../pages/Home';
import SignIn from '../../pages/SignIn';
import Cities from '../../pages/Cities';

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
  },
});

export default ROUTES;
