import { HashRouter, Routes, Route } from 'react-router-dom';
import DashboardLayout from './components/DashboardLayout';
import Users from './pages/Users/Users';
import Loans from './pages/Loans/Loans';
import Books from './pages/Books/Books';
import Authors from './pages/Authors/Authors';
import Overview from './pages/Overview/Overview';

function App() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<DashboardLayout />} >
          <Route index element={<Overview />} />
          <Route path="/overview" element={<Overview />} />
          <Route path="/loans" element={<Loans />} />
          <Route path="/books" element={<Books />} />
          <Route path="/authors" element={<Authors />} />
          <Route path="/users" element={<Users />} />
        </Route>
      </Routes>
    </HashRouter>
  );
}

export default App;
