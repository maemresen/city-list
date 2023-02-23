import { useParams } from 'react-router-dom';
import styled from '@emotion/styled';
import {
  Box, Button, Container, Grid, TextField,
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

  const [selectedPhoto, setSelectedPhoto] = useState();
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

  const handlePhotoPickerChange = (event) => {
    setSelectedPhoto(event.target.files[0]);
  };

  const handlePhotoSubmission = () => {
    cityService.updatePhoto({
      token: { accessToken, refreshToken },
      cityId: id,
      photo: selectedPhoto,
    }).then(() => {
      fetchData();
      return toast.success(`City ${id} updated successfully`);
    });
  };

  return (
    <StyledContainer maxWidth="xs" mar>
      <Grid container spacing={2} alignItems="stretch">
        <Grid item xs={8}>
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
        </Grid>
        <Grid item xs={4} c>
          <Button variant="contained" onClick={handleUpdate} disabled={hasError}>Save</Button>
        </Grid>
      </Grid>

      {formValues.photoFileUuid ? (
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
        <>
          <input
            type="file"
            name="file"
            id="file"
            placeholder="Upload your file"
            onChange={handlePhotoPickerChange}
          />
          <Button variant="contained" onClick={handlePhotoSubmission} disabled={!selectedPhoto}>Upload Photo</Button>
        </>
      )}
      <hr />
    </StyledContainer>
  );
}

export default City;
