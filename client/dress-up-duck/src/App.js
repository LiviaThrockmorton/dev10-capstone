import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavBar from './components/NavBar';
import Home from './components/Home';
import Login from './components/Login';
import CreateAccount from './components/CreateAccount';
import Success from './components/Success';
import Confirm from './components/Confirm';
import DressUpDuck from './components/DressUpDuck';
import Forum from './components/Forum';
import Outfit from './components/Outfit';
import Profile from './components/Profile';
import NotFound from './components/NotFound';


function App() {
  return (
    <Router>
      <div className='container'>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/create-account" element={<CreateAccount/>} />
          <Route path="/success" element={<Success/>} />
          <Route path="/confirm" element={<Confirm/>} />
          <Route path="/dress-up-duck" element={<DressUpDuck/>} />
          <Route path="/forum" element={<Forum/>} />
          <Route path="/forum/:outfitId" element={<Outfit/>} />
          <Route path="/profile" element={<Profile/>} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
