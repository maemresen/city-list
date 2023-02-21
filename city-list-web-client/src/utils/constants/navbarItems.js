import ROUTES from './routes';

const NAVBAR_ITEMS = Object.freeze({
  LEFT_ITEMS: [
    { ...ROUTES.HOME },
    { ...ROUTES.CITIES },
  ],
  RIGHT_ITEMS: [

  ],
});

export default NAVBAR_ITEMS;
