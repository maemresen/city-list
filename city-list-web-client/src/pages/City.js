import { useNavigate, useParams } from 'react-router-dom';
import styled from '@emotion/styled';
import {
  Box, Button, Container, TextField,
} from '@mui/material';
import { useCallback, useContext, useState } from 'react';
import { toast } from 'react-toastify';
import * as React from 'react';
import AuthContext from '../context/AuthContext';
import cityService from '../service/cityService';
import fileUtils from '../utils/fileUtils';

const StyledContainer = styled(Container)`
  padding: 1rem;
`;

const StyledTextField = styled(TextField)`
  margin-bottom: 2rem;
`;

const StyledImage = styled.img`
  width: 100%;
  margin-bottom: 2rem;
`;

function City() {
  const { id } = useParams();
  const { accessToken, refreshToken } = useContext(AuthContext);

  const [hasError, setHasError] = useState(null);
  const [formValues, setFormValues] = useState({});
  const [formErrors, setFormErrors] = useState({});

  const fetchData = useCallback(() => {
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
  }, [id, accessToken, refreshToken]);

  useState(() => {
    fetchData();
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

  const handleDeletePhoto = (event) => {
    event.preventDefault();
    cityService.deletePhoto({
      token: { accessToken, refreshToken },
      cityId: id,
    }).then(() => {
      fetchData();
      return toast.success(`City ${id} updated successfully`);
    });
  };

  const handlePhotoUpload = (event) => {
    const photo = event.target.files[0];
    cityService.updatePhoto({
      token: { accessToken, refreshToken },
      photo,
    }).then(() => {
      fetchData();
      return toast.success(`City ${id} photo updated successfully`);
    });
  };

  const handleUpdate = (event) => {
    event.preventDefault();
    cityService.update({
      token: { accessToken, refreshToken },
      city: { id, name: formValues.name },
    }).then(() => {
      fetchData();
      return toast.success(`City ${id} updated successfully`);
    });
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

      {formValues.uuid ? (
        <>
          <Box>
            <StyledImage
              src={fileUtils.getFullPath({ uuid: formValues.photoFileUuid })}
              alt="Image"
              loading="lazy"
            />
          </Box>
          <Button type="submit" variant="contained" onClick={handleDeletePhoto} disabled={hasError}>Delete Photo</Button>
        </>
      ) : (
        <input
          type="file"
          name="file"
          id="file"
          placeholder="Upload your file"
          onChange={handlePhotoUpload}
        />
      )}
      <hr />
      <Button variant="contained" onClick={handleUpdate} disabled={hasError}>Sign In</Button>
    </StyledContainer>
  );
}

export default City;
