import { useParams } from 'react-router-dom';

function City() {
  const { id } = useParams();
  return (
    <div>
      div
      {' '}
      {id}
    </div>
  );
}

export default City;
