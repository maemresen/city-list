import ROLE from './constants/roles';

const roleUtils = {
  hasRole({ self = {}, expectedRole }) {
    const { role } = self;
    return role === expectedRole;
  },

  hasAnyRole({ self = {}, expectedRoles = [] }) {
    return expectedRoles.some((expectedRole) => this.hasRole({ self, expectedRole }));
  },

  isAdmin({ self = {} }) {
    return this.hasRole({ self, expectedRole: ROLE.ROLE_ADMIN });
  },

  isAllowEdit({ self = {} }) {
    return this.hasRole({ self, expectedRole: ROLE.ROLE_ALLOW_EDIT });
  },
  isReadyOnly({ self = {} }) {
    return this.hasRole({ self, expectedRole: ROLE.ROLE_READ_ONLY });
  },
};

export default roleUtils;
