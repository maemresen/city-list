import { Grid } from '@mui/material';
import { Outlet } from 'react-router-dom';
import * as React from 'react';
import ResponsiveAppBar from '../component/ResponsiveAppBar/ResponsiveAppBar';
import ROUTES from '../utils/constants/routes';

export default function Layout() {
  return (
    <Grid container justifyContcent="center" className="app">
      <ResponsiveAppBar leftItems={[
        { ...ROUTES.HOME },
      ]}
      />
      <Outlet />
    </Grid>
  );
}
