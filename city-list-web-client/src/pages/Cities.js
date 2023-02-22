import * as React from 'react';
import { useContext, useEffect, useState } from 'react';
import styled from '@emotion/styled';
import {
  Container, Skeleton, TextField,
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import { Link } from 'react-router-dom';
import cityService from '../service/cityService';
import AuthContext from '../context/AuthContext';
import ROUTE_PATHS from '../utils/constants/routePaths';

const StyledImage = styled.img`
  width: auto;
  height: 60px;
`;

const StyledTextField = styled(TextField)`
  margin-top: 1rem;
  margin-bottom: 1rem;
`;

const StyledLink = styled(Link)`
  text-decoration: inherit;
`;

const columns = [
  {
    field: 'id',
    headerName: 'ID',
    width: 50,
    sortable: false,
  },
  {
    field: 'photo',
    headerName: 'Photo',
    width: 200,
    align: 'center',
    headerAlign: 'center',
    renderCell: (params) => {
      const value = params.row.photoFileUuid;
      if (!value) {
        return (
          <Skeleton variant="rectangular" animation={false} width={100} height={60} />
        );
      }
      return (
        <StyledImage
          src={`http://localhost:8080/api/file/${value}`}
          alt="Image"
          loading="lazy"
        />
      );
    },
  },
  {
    field: 'name',
    headerName: 'Name',
    flex: 1,
    sortable: false,
  },
  {
    field: 'edit',
    headerName: 'Name',
    sortable: false,
    renderCell: (params) => {
      const cityId = params.row.id;
      return (
        <StyledLink to={ROUTE_PATHS.CITY.replace(':id', cityId)}>
          Edit
        </StyledLink>
      );
    },
  },
  {
    field: 'photoFileUuid',
    headerName: 'UUID',
    hide: true,
  }];

export default function Cities() {
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(10);

  const [filterValues, setFilterValues] = useState({});
  const [filterErrors, setFilterErrors] = useState({});

  const { accessToken, refreshToken } = useContext(AuthContext);

  const [cities, setCities] = useState({
    content: [],
  });

  useEffect(() => {
    cityService.getCities({
      token: { accessToken, refreshToken },
      page,
      size: pageSize,
      ...filterValues,
    }).then((data) => setCities(data));
  }, [page, pageSize, filterValues]);

  const handleChangePage = (newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (newRowsPerPage) => {
    setPageSize(newRowsPerPage);
    setPage(0);
  };

  const handleInputChange = (e) => {
    const { name, value, minLength } = e.target;
    setFilterValues({
      ...filterValues,
      [name]: value,
    });

    console.log(minLength);
    if (minLength) {
      setFilterErrors({
        ...filterErrors,
        [name]: value && value.length < minLength,
      });
    }
    setPage(0);
  };

  return (
    <Container maxWidth="md" mar>
      <StyledTextField
        id="name"
        name="name"
        value={filterValues.name}
        onChange={handleInputChange}
        label="name"
        variant="outlined"
        minLength={3}
      />
      <div style={{ height: 950, width: '100%' }}>
        <DataGrid
          columns={columns}
          rows={cities.content}
          rowHeight={80}
          rowCount={cities.totalElements}
          rowsPerPageOptions={[10]}
          page={page}
          pageSize={pageSize}
          paginationMode="server"
          onPageChange={handleChangePage}
          onPageSizeChange={handleChangeRowsPerPage}
          disableColumnMenu
        />
      </div>
    </Container>

  );
}
