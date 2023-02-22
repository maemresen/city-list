import ROLE from './constants/roles';

const roleUtils = {
  hasRole({ self = {}, requiredRole }) {
    const { role } = self;
    return role === requiredRole;
  },

  hasAnyRole({ self = {}, requiredRoles = [] }) {
    if (requiredRoles.length === 0) {
      return true;
    }
    return requiredRoles.some((requiredRole) => this.hasRole({ self, requiredRole }));
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
