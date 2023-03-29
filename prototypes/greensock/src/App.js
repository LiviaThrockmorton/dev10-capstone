import './App.css';
import { useLayoutEffect } from 'react';
import { gsap } from 'gsap';

function App() {

  useLayoutEffect(() => {
    gsap.to(".logo", {duration: 2, x: 300});
  })

  return (
    <>
      <img className="logo" src="/duck_life.jpg" alt="Two cartoon ducks facing each other with competitive spirit"/>
    </>
  );
}

export default App;
