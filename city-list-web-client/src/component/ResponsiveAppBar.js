import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import AdbIcon from '@mui/icons-material/Adb';
import PropTypes from 'prop-types';
import styled from '@emotion/styled';
import { Link, useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { IconButton, Menu, MenuItem } from '@mui/material';
import { AccountCircle } from '@mui/icons-material';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';
import roleUtils from '../utils/roleUtils';

const AppBarLink = styled(Link)`
  text-decoration: none;
  color: inherit;
`;

const StyledAccountCircle = styled(AccountCircle)`
  margin-left: 1rem;
`;

function ResponsiveAppBar({ leftItems }) {
  const { isAuthenticated, self, signOut } = useContext(AuthContext);
  const { firstName, lastName, role } = self;
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
    signOut();
  };

  return (
    <AppBar position="static">

      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
          <Typography
            variant="h6"
            noWrap
            component="a"
            href="/"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          />

          <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              mr: 2,
              display: { xs: 'flex', md: 'none' },
              flexGrow: 1,
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            LOGO
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {leftItems.filter(({ isPublic, requiredRoles }) => {
              if (isPublic) {
                return true;
              }

              if (!isAuthenticated) {
                return false;
              }

              return roleUtils.hasAnyRole({ self, requiredRoles });
            })
              .map(({ name, path }) => (
                <Button key={name} sx={{ my: 2, color: 'white', display: 'block' }}>
                  <AppBarLink to={path}>
                    {name}
                  </AppBarLink>
                </Button>
              ))}

          </Box>

          {isAuthenticated ? (
            <div>
              <Button
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleMenu}
                color="inherit"
              >
                {`Hello, ${firstName} ${lastName}  - (${role})`}
                <StyledAccountCircle />
              </Button>
              <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                open={Boolean(anchorEl)}
                onClose={handleClose}
              >
                <MenuItem onClick={handleClose}>Sign Out</MenuItem>
                {roleUtils.isAdmin({ self })
                      && <MenuItem onClick={handleClose}>Users</MenuItem>}
              </Menu>
            </div>
          ) : (
            <Box sx={{ flexGrow: 0 }}>
              <Tooltip title="Open settings">
                <Button
                  sx={{ my: 2, color: 'white', display: 'block' }}
                  onClick={() => navigate(ROUTE_PATHS.SIGN_IN)}
                >
                  <AppBarLink>
                    Sign In
                  </AppBarLink>
                </Button>
              </Tooltip>
            </Box>
          )}

        </Toolbar>
      </Container>
    </AppBar>
  );
}

ResponsiveAppBar.propTypes = {
  leftItems: PropTypes.arrayOf(PropTypes.shape({
    key: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    path: PropTypes.string.isRequired,
  })),
};

ResponsiveAppBar.defaultProps = {
  leftItems: [],
};

export default ResponsiveAppBar;
