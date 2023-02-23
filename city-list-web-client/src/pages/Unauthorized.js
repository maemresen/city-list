import { Link } from 'react-router-dom';
import ROUTE_PATHS from '../utils/constants/routePaths';

export default function Unauthorized() {
  return (
    <div>
      <ul>
        <li>
          Unauthorized access. You were tried to access page that your user not allowed to open
        </li>
        <li>
          You could see your role at the right top of the page, at appbar.
        </li>
        <li>
          You could navigate to the the following pages
          <ul>
            <li>
              <Link to={ROUTE_PATHS.HOME}>Home</Link>
            </li>
            <li>
              <Link to={ROUTE_PATHS.CITIES}>Cities</Link>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}
