import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import jwtDecode from "jwt-decode";
import AuthContext from "./contexts/AuthContext";
import NavBar from './components/NavBar';
import Home from './components/Home';
import Login from './components/Login';
import CreateAccount from './components/CreateAccount';
import Success from './components/Success';
import Confirm from './components/Confirm';
import DressUpDuck from './components/DressUpDuck';
import Forum from './components/Forum';
import ForumPost from './components/ForumPost';
import Profile from './components/Profile';
import NotFound from './components/NotFound';
import { useState, useEffect } from 'react';

const LOCAL_STORAGE_TOKEN_KEY = "dressUpDuckToken";

function App() {

  const [user, setUser] = useState(null);
  const [restoreLoginAttemptCompleted, setRestoreLoginAttemptCompleted] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    if (token) {
      login(token);
    }
    setRestoreLoginAttemptCompleted(true);
  }, []);

  const login = (token) => {
    localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);
    const { sub: username, authorities: authoritiesString } = jwtDecode(token);
    const authorities = authoritiesString.split(',');

    const user = {
      username,
      authorities,
      token,
      hasAuthority(authority) {
        return this.authorities.includes(authority);
      }
    };

    setUser(user);
    return user;
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
  };

  const auth = { user: user ? { ...user } : null, login, logout };

  if (!restoreLoginAttemptCompleted) { return null; };

  return (

      <Router>
        <div className='container'>
          <NavBar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={!user ? <Login /> : <Navigate to="/" />} />
            <Route path="/create-account" element={!user ? <CreateAccount /> : <Navigate to="/" />} />
            <Route path="/success" element={<Success />} />
            <Route path="/confirm" element={<Confirm />} />
            <Route path="/dress-up-duck" element={<DressUpDuck />} />
            <Route path="/dress-up-duck/edit/:id" element={user ? <DressUpDuck /> : <Navigate to="/login" />} />
            <Route path="/dress-up-duck/delete/:id" element={user && user.Authorities.includes("ADMIN") ? <Confirm /> : <Navigate to="/login" />} />
            <Route path="/forum" element={<Forum />} />
            <Route path="/forum/:outfitId" element={<ForumPost />} />
            <Route path="/profile" element={user ? <Profile /> : <Navigate to="/login" />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </div>
      </Router>

  );
}

export default App;
