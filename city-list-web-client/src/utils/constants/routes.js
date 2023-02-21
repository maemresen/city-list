import Home from '../../pages/Home';
import Login from '../../pages/Login';
import Cities from '../../pages/Citiies';

const ROUTES = Object.freeze({
  HOME: {
    key: 'home',
    name: 'Home',
    path: '/',
    component: <Home />,
    index: true,
  },
  LOGIN: {
    key: 'login',
    name: 'Login',
    path: '/login',
    component: <Login />,
  },
  CITIES: {
    key: 'cities',
    name: 'Cities',
    path: '/cities',
    component: <Cities />,
  },
});

export default ROUTES;
