import React, { useState, useEffect } from 'react';
import './styles.css';
import { Button } from '../../components/Button';
import { Icon, Icons } from '../../components/Icon';
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import SearchIcon from '@mui/icons-material/Search';
import EditIcon from "@mui/icons-material/EditOutlined";
import DeleteIcon from "@mui/icons-material/DeleteOutline";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { Dayjs } from 'dayjs';
import { Dialog, DialogTitle, DialogContent, DialogActions, Skeleton } from '@mui/material';
import type { UserPreview } from '../../models/UserPreview';
import { userService } from '../../services/UserService';
import { DashboardModuleTopBar } from '../../components/DashboardModuleTopBar/DashboardModuleTopBar';

const loanOptions = Array.from({ length: 20 }, (_, i) => i + 1);

type UsersState =
  | { status: 'idle' }
  | { status: 'loading' }
  | { status: 'error'; error: string }
  | { status: 'success'; users: UserPreview[] };

const Users: React.FC = () => {
  const [filters, setFilters] = useState({
    search: '',
    role: '',
    membershipMinDate: null as Dayjs | null,
    membershipMaxDate: null as Dayjs | null,
    minLoans: '',
    maxLoans: ''
  });

  const [usersState, setUsersState] = useState<UsersState>({ status: 'idle' });
  const [errorOpen, setErrorOpen] = useState(false);

  useEffect(() => {
    const fetchUsers = async () => {
      setUsersState({ status: 'loading' });
      try {
        const users = await userService.getUsersPreviews();
        setUsersState({ status: 'success', users });
      } catch (error: any) {
        setUsersState({ status: 'error', error: error.message || 'Unknown error' });
        setErrorOpen(true);
      }
    };

    fetchUsers();
  }, [filters]);

  const handleFilterChange = (field: keyof typeof filters, value: any) => {
    setFilters(prev => ({ ...prev, [field]: value }));
  };

  const closeError = (_: any) => setErrorOpen(false);

  return (
    <div className='parent-container'>
      <DashboardModuleTopBar
        title="Usuarios"
        onExportClick={() => { }}
        onNewClick={() => { }}
      />

      {/* Filters */}
      <div className='filters'>
        {/* Buscar */}
        <div className='filter-item'>
          <TextField
            value={filters.search}
            onChange={(e) => handleFilterChange('search', e.target.value)}
            label="Buscar usuarios"
            placeholder='Nombre, email, teléfono...'
            variant="outlined"
            fullWidth
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
        </div>

        {/* Rol */}
        <div className='filter-item'>
          <FormControl fullWidth variant="outlined">
            <InputLabel id="role-label">Rol</InputLabel>
            <Select
              labelId="role-label"
              value={filters.role}
              onChange={(e) => handleFilterChange('role', e.target.value)}
              label="Rol"
            >
              <MenuItem value="">Cualquiera</MenuItem>
              <MenuItem value="opcion1">Administrador</MenuItem>
              <MenuItem value="opcion2">Usuario</MenuItem>
              <MenuItem value="opcion3">Personal</MenuItem>
            </Select>
          </FormControl>
        </div>

        {/* Fechas membresía */}
        <div className='filter-item'>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="Fecha membresía (mín)"
              value={filters.membershipMinDate}
              onChange={(v) => handleFilterChange('membershipMinDate', v)}
              slotProps={{ textField: { fullWidth: true } }}
            />
          </LocalizationProvider>
        </div>
        <div className='filter-item'>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="Fecha membresía (max)"
              value={filters.membershipMaxDate}
              onChange={(v) => handleFilterChange('membershipMaxDate', v)}
              slotProps={{ textField: { fullWidth: true } }}
            />
          </LocalizationProvider>
        </div>

        {/* Préstamos min-max */}
        <div className='filter-item'>
          <FormControl fullWidth variant="outlined">
            <InputLabel id="min-loans-label">Préstamos activos (mín)</InputLabel>
            <Select
              labelId="min-loans-label"
              value={filters.minLoans}
              onChange={(e) => handleFilterChange('minLoans', e.target.value)}
              label="Préstamos activos (mín)"
            >
              <MenuItem value="">Cualquiera</MenuItem>
              {loanOptions.map((num) => (
                <MenuItem key={num} value={num}>{num}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </div>

        <div className='filter-item'>
          <FormControl fullWidth variant="outlined">
            <InputLabel id="max-loans-label">Préstamos activos (max)</InputLabel>
            <Select
              labelId="max-loans-label"
              value={filters.maxLoans}
              onChange={(e) => handleFilterChange('maxLoans', e.target.value)}
              label="Préstamos activos (max)"
            >
              <MenuItem value="">Cualquiera</MenuItem>
              {loanOptions.map((num) => (
                <MenuItem key={num} value={num}>{num}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </div>
      </div>

      {/* Table / Spinner */}
      <div className='table-container' style={{ display: 'flex', justifyContent: 'center', padding: '2rem' }}>

        {(
          <table className='table'>
            <thead>
              <tr>
                <th>Usuario</th>
                <th>Contacto</th>
                <th>Rol</th>
                <th>Miembro desde</th>
                <th>Préstamos activos</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {usersState.status === 'loading' && (
                Array.from({ length: 15 }).map((_, i) => (
                  <tr key={i}>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                    <td><Skeleton variant="rectangular" height={40} /></td>
                  </tr>
                ))
              )}
              {usersState.status === 'success' && usersState.users.map(user => (
                <tr key={user.id}>
                  <td className='user-info-cell'>
                    <div className='user-avatar-container'>
                      <Icon name={Icons.user_avatar} />
                    </div>
                    <div className='user-name-and-id'>
                      <span className='user-name'>{user.name}</span>
                      <span className='user-id'>{user.id}</span>
                    </div>
                  </td>
                  <td className='user-contact-cell'>
                    <div className='user-contact-info'>
                      <span className='user-email'>{user.email}</span>
                      <span className='user-phone'>{user.phone}</span>
                    </div>
                  </td>
                  <td>
                    <span className={`user-role-badge ${user.role.toLowerCase()}`}>{user.role}</span>
                  </td>
                  <td>{user.memberSince}</td>
                  <td>{user.activeLoans}</td>
                  <td>
                    <div className='actions'>
                      <button className='action-button edit-button'>
                        <EditIcon className='edit-icon' />
                      </button>
                      <button className='action-button delete-button'>
                        <DeleteIcon className='delete-icon' />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {/* Error Modal */}
      <Dialog open={errorOpen} onClose={closeError}>
        <DialogTitle>Error</DialogTitle>
        <DialogContent>
          {usersState.status === 'error' && usersState.error}
        </DialogContent>
        <DialogActions>
          <Button type='primary' onClick={(e: any) => closeError(e)}>Cerrar</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default Users;
