import { Grid } from '@mui/material';
import { Outlet, Link } from 'react-router-dom';
import * as React from 'react';

export default function Layout() {
  return (
    <Grid container justifyContent="center" className="app">
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
        </ul>
      </nav>
      <Outlet />
    </Grid>
  );
}
