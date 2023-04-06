import sadDuck from "./images/sad-duck.svg";
import tear from "./images/tear.svg";
import { gsap } from "gsap";
import { useLayoutEffect } from "react";
import { useNavigate } from "react-router-dom";

function NotFound() {
  const navigate = useNavigate();

  //ANIMATION
  useLayoutEffect(() => {
    let ctx = gsap.context(() => {
      gsap.fromTo(".tear", { y: -50, opacity: 0}, { duration: 2, y: 0, opacity: 1, ease: "power4" });
      return () => ctx.revert();
    });
  }, [navigate]);

  return (
    <div className="container">
      <img src={sadDuck} alt="Not Found" width="60%" position="absolute" style={{ position: "absolute" }} />
      <img src={tear} alt="tear drop" width="60%" className="tear" style={{ position: "absolute" }} />
    </div>
  );
}

export default NotFound;
