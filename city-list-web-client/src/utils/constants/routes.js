import Home from '../../pages/Home';
import SignIn from '../../pages/SignIn';
import Cities from '../../pages/Citiies';

const ROUTES = Object.freeze({
  HOME: {
    key: 'home',
    name: 'Home',
    path: '/',
    component: <Home />,
    index: true,
  },
  SIGN_IN: {
    key: 'sign-in',
    name: 'SignIn',
    path: '/sign-in',
    component: <SignIn />,
  },
  CITIES: {
    key: 'cities',
    name: 'Cities',
    path: '/cities',
    component: <Cities />,
  },
});

export default ROUTES;
