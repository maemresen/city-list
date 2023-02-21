import { Grid } from '@mui/material';
import { Outlet } from 'react-router-dom';
import * as React from 'react';
import Container from '@mui/material/Container';
import NAVBAR_ITEMS from '../utils/constants/navbarItems';
import ResponsiveAppBar from '../component/ResponsiveAppBar';
import AuthProvider from '../provider/AuthProvider';

export default function Layout() {
  return (
    <Grid container justifyContcent="center" className="app">
      <ResponsiveAppBar leftItems={NAVBAR_ITEMS.LEFT_ITEMS} />
      <Container maxWidth="xl">
        <Outlet />
      </Container>
    </Grid>
  );
}
