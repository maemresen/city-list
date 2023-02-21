import { Button, Container, TextField } from '@mui/material';
import styled from '@emotion/styled';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import useAuth from '../hook/useAuth';
import ROUTES from '../utils/constants/routes';

const StyledContainer = styled(Container)`
  padding: 1rem;
`;

const StyledTextField = styled(TextField)`
  margin-bottom: 2rem;
`;

export default function SignIn() {
  const { signIn, isAuthenticated } = useAuth();
  const navigate = useNavigate();

  if (isAuthenticated) {
    navigate(ROUTES.HOME.path);
  }

  const [username, setUserName] = useState(null);
  const [password, setPassword] = useState(null);
  return (
    <StyledContainer maxWidth="xs" mar>
      <StyledTextField id="username" label="Username" variant="outlined" fullWidth value={username} />
      <StyledTextField id="password" label="Password" variant="outlined" fullWidth value={password} />
      <Button variant="contained" onClick={() => signIn(username, password)}>Sign In</Button>
    </StyledContainer>
  );
}
