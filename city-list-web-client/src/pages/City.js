import { useNavigate, useParams } from 'react-router-dom';
import styled from '@emotion/styled';
import { Button, Container, TextField } from '@mui/material';
import { useContext, useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';
import cityService from '../service/cityService';

const StyledContainer = styled(Container)`
  padding: 1rem;
`;

const StyledTextField = styled(TextField)`
  margin-bottom: 2rem;
`;

function City() {
  const { id } = useParams();
  const { accessToken, refreshToken } = useContext(AuthContext);

  const [hasError, setHasError] = useState(null);
  const [formValues, setFormValues] = useState({});
  const [formErrors, setFormErrors] = useState({});

  useState(() => {
    cityService.getById({
      token: { accessToken, refreshToken },
      id,
    }).then(({ name, photoFileUuid }) => {
      setFormValues({
        name,
        photoFileUuid,
      });
      setFormErrors({});
    });
  });

  const handleInputChange = (e) => {
    const { name, value, required } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });

    const error = required && !value;
    setFormErrors({
      ...formErrors,
      [name]: error,
    });
    setHasError(error || Object.values(formErrors).some((x) => x));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    cityService.update({
      token: { accessToken, refreshToken },
      city: { id, name: formValues.name },
    }).then(() => toast.success(`City ${id} updated successfully`));
  };

  return (
    <StyledContainer maxWidth="xs" mar>
      <StyledTextField
        id="name"
        name="name"
        value={formValues.name || ''}
        error={formErrors.name}
        required
        onChange={handleInputChange}
        label="Name"
        variant="outlined"
        fullWidth
      />
      <Button variant="contained" onClick={handleSubmit} disabled={hasError}>Sign In</Button>
    </StyledContainer>
  );
}

export default City;
