import jwtDecode from 'jwt-decode';

const jwtUtils = {
  getClaim({ jwt, claimKey }) {
    const { [claimKey]: claim } = jwtDecode(jwt);
    return claim;
  },
};

export default jwtUtils;
