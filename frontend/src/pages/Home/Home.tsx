import '../styles/globals.css';
import '../styles/users.css';
import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
  return (
    <>
      <Link to="/users">Go to Users</Link>
    </>
  );
};

export default Home;