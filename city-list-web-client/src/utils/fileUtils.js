import API_CONSTANTS from './constants/apiConstants';

const fileUtils = {

  getFullPath({ uuid }) {
    return `${API_CONSTANTS.BASE_URL}/file/${uuid}`;
  },
};

export default fileUtils;
