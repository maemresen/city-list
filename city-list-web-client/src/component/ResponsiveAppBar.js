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
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';

const AppBarLink = styled(Link)`
  text-decoration: none;
  color: inherit;
`;

function ResponsiveAppBar({ leftItems }) {
  const { isAuthenticated, self, signOut } = useContext(AuthContext);
  console.log('self is ', self);
  const navigate = useNavigate();
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
            {leftItems.filter(({ isPublic }) => isPublic || isAuthenticated)
              .map(({ name, path }) => (
                <Button key={name} sx={{ my: 2, color: 'white', display: 'block' }}>
                  <AppBarLink to={path}>
                    {name}
                  </AppBarLink>
                </Button>
              ))}

          </Box>

          <Box sx={{ flexGrow: 0 }}>
            <AppBarLink>
              {isAuthenticated && 'hellö'}
            </AppBarLink>
          </Box>
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <Button
                sx={{ my: 2, color: 'white', display: 'block' }}
                onClick={() => (isAuthenticated ? signOut() : navigate(ROUTE_PATHS.SIGN_IN))}
              >
                <AppBarLink>
                  {isAuthenticated ? 'Sign Out' : 'Sign In'}
                </AppBarLink>
              </Button>
            </Tooltip>
          </Box>
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
