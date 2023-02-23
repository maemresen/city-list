import Sitemap from '../component/Sitemap';

export default function Unauthorized() {
  return (
    <Sitemap message="Unauthorized access. You were tried to access page that your user not allowed to open" />
  );
}
