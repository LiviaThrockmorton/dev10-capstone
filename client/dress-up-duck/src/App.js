import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import jwtDecode from "jwt-decode";
import AuthContext from "./context/AuthContext";
import NavBar from './components/NavBar';
import Home from './components/Home';
import Login from './components/Login';
import CreateAccount from './components/CreateAccount';
import Success from './components/Success';
import Confirm from './components/Confirm';
import DressUpDuck from './components/DressUpDuck';
import Forum from './components/Forum';
import ForumMain from './components/ForumMain';
import Outfit from './components/Outfit';
import Profile from './components/Profile';
import NotFound from './components/NotFound';
import ForumPost from './components/ForumPost';
import ToggleSwitch from './components/toggleSwitch';
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
    const roles = authoritiesString.split(',');

    const user = {
      username,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
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
    <AuthContext.Provider value={auth}>
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
            <Route path="/dress-up-duck/delete/:id" element={user && user.roles.includes("ADMIN") ? <Confirm /> : <Navigate to="/login" />} />
            <Route path="/forum" element={<Forum />} />
            <Route path="/forum/:outfitId" element={<Outfit />} />
            <Route path="/profile" element={user ? <Profile /> : <Navigate to="/login" />} />
            <Route path="/forum-post" element={<ForumPost />} />
            <Route path="/forum-main" element={<ForumMain />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </div>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;
