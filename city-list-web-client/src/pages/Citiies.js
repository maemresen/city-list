import { Button } from '@mui/material';
import Authorized from '../component/PrivatePage';
import useAuth from '../hook/useAuth';

export default function Cities() {
  const { signOut } = useAuth();
  return (
    <Authorized>
      <div>
        cities
        <Button onClick={() => signOut()}>sginout</Button>
      </div>
    </Authorized>
  );
}
