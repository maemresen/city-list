import Home from '../../pages/Home';
import Login from '../../pages/Login';

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
});

export default ROUTES;
