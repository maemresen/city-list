import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import ROUTE_PATHS from '../utils/constants/routePaths';

function Sitemap({ message }) {
  return (
    <div>
      <h1>
        {message}
      </h1>
      <h2>Navigation</h2>
      <ul>
        <li>
          You could navigate to the the following pages
          <ul>
            <li>
              <Link to={ROUTE_PATHS.HOME}>Home</Link>
            </li>
            <li>
              <Link to={ROUTE_PATHS.CITIES}>Cities</Link>
              <ul>
                <li>
                  You need to login with ROLE_ALLOW_EDIT or ROLE_READ_ONLY roles.
                </li>
                <li>
                  For ROLE_ALLOW_EDIT =>  username: edit  password: admin
                </li>
                <li>
                  For ROLE_READ_ONLY =>  username: read  password: admin
                </li>
              </ul>
            </li>
            <li>
              {ROUTE_PATHS.CITY}
              <ul>
                <li>
                  Use this to open edit page of the city
                </li>
                <li>
                  You need to login with ROLE_ALLOW_EDIT or ROLE_READ_ONLY roles.
                </li>
                <li>
                  For ROLE_ALLOW_EDIT =>  username: edit  password: admin
                </li>
                <li>
                  :id will be replaced with the id of the city that you would like to edit.
                </li>
                <li>
                  Example: /cities/1
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}

Sitemap.propType = {
  message: PropTypes.string.isRequired,
};

export default Sitemap;
