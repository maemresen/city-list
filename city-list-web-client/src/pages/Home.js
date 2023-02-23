import { Link } from 'react-router-dom';
import ROUTE_PATHS from '../utils/constants/routePaths';

export default function Home() {
  return (
    <div>
      <ul>
        <li>
          This is home page application.
        </li>
        <li>
          Paths
          <ul>
            <li>
              <Link to={ROUTE_PATHS.CITIES}>/cities</Link>
              <ul>
                <li>
                  To list all the cities
                </li>
                <li>
                  You should select, and edit cities from the table
                </li>
              </ul>
            </li>
            <li>
              /city/:cityId
              <ul>
                <li>
                  Use this to open edit page of the city
                </li>
                <li>
                  :cityId will be replaced with the id of the city that you would like to edit.
                </li>
                <li>
                  Example: /city/1
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}
