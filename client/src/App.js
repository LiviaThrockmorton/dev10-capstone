import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
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
import { useContext } from 'react';

function App() {

  const {user} = useContext(AuthContext);

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
            <Route path="/dress-up-duck/delete/:id" element={user && user.hasAnyAuthority("ADMIN") ? <Confirm /> : <Navigate to="/login" />} />
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
