import { Button, Container, TextField } from '@mui/material';
import styled from '@emotion/styled';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ROUTE_PATHS from '../utils/constants/routePaths';

const StyledContainer = styled(Container)`
  padding: 1rem;
`;

const StyledTextField = styled(TextField)`
  margin-bottom: 2rem;
`;

export default function SignIn() {
  const navigate = useNavigate();

  const [formValues, setFormValues] = useState({});
  const [formErrors, setFormErrors] = useState({});

  const handleInputChange = (e) => {
    const { name, value, required } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });

    setFormErrors({
      ...formErrors,
      [name]: required && !value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
  };

  return (
    <StyledContainer maxWidth="xs" mar>
      <StyledTextField
        id="username"
        name="username"
        value={formValues.username}
        error={formErrors.username}
        required
        onChange={handleInputChange}
        label="Username"
        variant="outlined"
        fullWidth
      />
      <StyledTextField
        id="password"
        name="password"
        value={formValues.password}
        error={formErrors.password}
        required
        onChange={handleInputChange}
        label="Password"
        variant="outlined"
        fullWidth
      />
      <Button variant="contained" onClick={handleSubmit}>Sign In</Button>
    </StyledContainer>
  );
}
