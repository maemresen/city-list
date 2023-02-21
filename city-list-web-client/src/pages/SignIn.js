import { Button, Container, TextField } from '@mui/material';
import styled from '@emotion/styled';
import { useState } from 'react';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import authService from '../service/authService';
import COOKIE_KEY from '../utils/constants/cookieKeys';
import ROUTES from '../utils/constants/routes';

const StyledContainer = styled(Container)`
  padding: 1rem;
`;

const StyledTextField = styled(TextField)`
  margin-bottom: 2rem;
`;

export default function SignIn() {
  const navigate = useNavigate();

  const [username, setUserName] = useState(null);
  const [password, setPassword] = useState(null);

  const signIn = () => {
    authService.signIn('admin', 'admin').then(({ accessToken, refreshToken }) => {
      Cookies.set(COOKIE_KEY.ACCESS_TOKEN, accessToken);
      Cookies.set(COOKIE_KEY.REFRESH_TOKEN, refreshToken);
    }).then(() => navigate(ROUTES.CITIES.path));
  };

  return (
    <StyledContainer maxWidth="xs" mar>
      <StyledTextField id="username" label="Username" variant="outlined" fullWidth value={username} />
      <StyledTextField id="password" label="Password" variant="outlined" fullWidth value={password} />
      <Button variant="contained" onClick={signIn}>Sign In</Button>
    </StyledContainer>
  );
}
