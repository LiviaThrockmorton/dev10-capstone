import './App.css';
import { useLayoutEffect, useRef } from 'react';
import { gsap } from 'gsap';

function App() {

  const comp = useRef();

  useLayoutEffect(() => {
    let ctx = gsap.context(() => {

      gsap.to(".logo", {
        duration: 2, x: 300, borderRadius: "20%", border: "5px solid white",
        ease: "bounce"
      }, comp);

      gsap.set(".logo, .rectangle", {transformOrigin: "50% 50%"});
      gsap.to(".logo, .rectangle", {duration: 2, rotation: 360});
      return () => ctx.revert();
    });
  });

  return (
    <>
      <div>
        <img className="logo" src="/duck_life.jpg" alt="Two cartoon ducks facing each other with competitive spirit" />
      </div>
      <div>
        <svg width="225" height="180" >
          <rect x="50" y="20" rx="20" ry="20" width="150" height="150" className='rectangle'/>
        </svg>
      </div>
      <div>
        <img src="New Project.svg" alt="black oval" />
      </div>
    </>
  );
}

export default App;
