import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// why is this not working?
import AuthContextProvider from './components/AuthContextProvider';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <AuthContextProvider>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </AuthContextProvider>
);

//TODO 