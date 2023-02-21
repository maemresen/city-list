import Cookies from 'js-cookie'
import COOKIE_KEY from "../utils/constants/cookieKeys";
import ROUTES from "../utils/constants/routes";
import {Navigate} from "react-router-dom";

function Authorized({children}) {
    const accessToken = Cookies.get(COOKIE_KEY.ACCESS_TOKEN);
    if (accessToken) {
        return children
    } else {
        return <Navigate to={ROUTES.SIGN_IN.path} replace />
    }
}

export default Authorized;
